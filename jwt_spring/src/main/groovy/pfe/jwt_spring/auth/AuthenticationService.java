package pfe.jwt_spring.auth;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pfe.jwt_spring.Role.RoleRepsitory;
import pfe.jwt_spring.User.Token;
import pfe.jwt_spring.User.TokenRepository;
import pfe.jwt_spring.User.User;
import pfe.jwt_spring.User.UserRepsitory;
import pfe.jwt_spring.email.EmailService;
import pfe.jwt_spring.email.EmailTemplateName;
import pfe.jwt_spring.secutity.JwtService;

import java.net.PasswordAuthentication;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepsitory roleRepsitory;
    private final PasswordEncoder passwordEncoder;
    private final UserRepsitory userRepsitory;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Value("${application.mailing.frontend.activation-url}")
    private String activateUrl;
    private final RestTemplate restTemplate ;
    private static final String AUTH_API = "URL_DU_SERVICE_AUTH";


    public void register(RegistrationRequest request) throws MessagingException {
        var userRole = roleRepsitory.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Role User was not intialized"));
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepsitory.save(user);
        sendValidationEmail(user);

    }
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName(); // Retourne le nom d'utilisateur de l'utilisateur actuellement authentifié
        }
        return null;
    }
    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activateUrl,
                newToken,
                "Account activation"


        );
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateAndSaveActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;

    }

    private String generateAndSaveActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codebuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codebuilder.append(characters.charAt(randomIndex));
        }
        return codebuilder.toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fulName", user.fullName());
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder().
                token(jwtToken).build();
    }

    @Transactional
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been sent to the same email address.");
        }

        var user = userRepsitory.findByEmail(savedToken.getUser().getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setEnabled(true);
        userRepsitory.save(user);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    public List<User> getAllUsers() {
        return userRepsitory.findAll();
    }

    public User registerUser(User user) throws MessagingException {
        // Vérifiez si l'utilisateur existe déjà dans la base de données
        if (userRepsitory.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }

        // Encodez le mot de passe de l'utilisateur
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Définissez d'autres propriétés de l'utilisateur si nécessaire, comme les rôles, etc.
        user.setAccountLocked(false);
        user.setEnabled(false);

        // Enregistrez l'utilisateur dans la base de données
        userRepsitory.save(user);

        // Envoyez un e-mail de validation si nécessaire
        sendValidationEmail(user);

        // Retournez l'utilisateur enregistré
        return user;
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }

    public String resetPassword(String email) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);

        ResponseEntity<String> response = restTemplate.postForEntity(AUTH_API + "resetPassword", requestBody, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return "Password reset failed";
        }
    }
}
