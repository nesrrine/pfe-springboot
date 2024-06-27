package pfe.jwt_spring.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;


@Configuration
public class BeansConfig {

    private final UserDetailsService userDetailsService;

    public BeansConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
       return  config.getAuthenticationManager();

}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // You can use any PasswordEncoder implementation here
    }
    @Bean
    public CorsFilter corsFilter() {
final UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
final CorsConfiguration config=new CorsConfiguration();
    config.setAllowCredentials(true);
    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200/"));
        config.setAllowedHeaders(Arrays.asList(
                "Origin",
                "Content-Type",
                "Accept",
                "Authentication" // Assurez-vous que cela correspond à l'en-tête réel utilisé
        ));
        config.setAllowedMethods(Arrays
                .asList(
                        "GET",
                        "POST",
                        "DELETE",
                        "PUT",
                        "PATCH"
                ));
        source.registerCorsConfiguration("/**", config);
            return new org.springframework.web.filter.CorsFilter(source);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}