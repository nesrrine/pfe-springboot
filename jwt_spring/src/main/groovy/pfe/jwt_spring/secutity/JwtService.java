package pfe.jwt_spring.secutity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import javax.crypto.spec.SecretKeySpec;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${application.security.jwt.expiration}")

    private long jwtExpiration ;
    @Value("${application.security.jwt.secret-key}")
    private String secrectKey;
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token , Function<Claims,T>claimsResolver){
        final Claims claims=extractAllClaims(token);
         return  claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody();
    }

    public  String generateToken(UserDetails userDetails){
    return  generateToken( new HashMap<>(),userDetails);
}

    public   String generateToken(Map<String,Object> claims, UserDetails userDetails) {
        return  buildToken(claims, userDetails,jwtExpiration);
}

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long jwtExpiration) {
        var authorities=userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .claim("authorities",authorities)
                .signWith(SignatureAlgorithm.HS256, getSignInKey())
                .compact()

                ;

    }
public  boolean isTokenValid(String token ,  UserDetails userDetails){
        final  String username=extractUsername(token);
return  (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
   return extractExpiration(token).before(new Date());
   
    }

    private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
    }


    private Key getSignInKey() {
        byte[] keyBytes = secrectKey.getBytes(); // Convertir la clé en bytes
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

}
