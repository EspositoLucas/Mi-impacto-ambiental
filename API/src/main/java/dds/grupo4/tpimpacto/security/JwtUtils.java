package dds.grupo4.tpimpacto.security;

import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtils implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 horas
    public static final String JWT_AUTHORITIES_KEY = "CLAIM_TOKEN";

    @Value("${jwt.secret}")
    private String secret;

    public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token, UserDetails userDetails) {
        Claims claims = getAllClaimsFromToken(token);
        List<String> claimAuthorities = Arrays.asList(claims.get(JWT_AUTHORITIES_KEY).toString().split(","));
        List<GrantedAuthority> authorities = claimAuthorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public String generateToken(Usuario usuario) {
        List<GrantedAuthority> authorities = getAuthorities(usuario);
        String strAuthorities = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", ((Long) usuario.getId()).toString());
        claims.put(JWT_AUTHORITIES_KEY, strAuthorities);
        return doGenerateToken(claims, usuario.getUsername());
    }

    public List<GrantedAuthority> getAuthorities(Usuario usuario) {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        if (usuario.isAdmin())
            roles.add("ROLE_ADMIN");
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private Claims getAllClaimsFromToken(String token) {
        SecretKey key = getSecretKey();
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
       // return expirationDate.before(new Date());
        return false ;
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        SecretKey key = getSecretKey();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key/*, SignatureAlgorithm.HS512*/)
                .compact();
    }

    private SecretKey getSecretKey() {
        // String base64EncodedSecretKey = Base64.getEncoder().encodeToString(secret.getBytes());
        // byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        // SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return key;
    }

}
