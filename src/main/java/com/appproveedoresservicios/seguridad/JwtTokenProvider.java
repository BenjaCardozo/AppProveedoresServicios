package com.appproveedoresservicios.seguridad;

import com.appproveedoresservicios.excepciones.ServicioAppException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY = "appproveedoresservicios";

    public String generarToken(String username) {
        return crearToken(username);
    }

    public String crearToken(String username) {

        Date fechaActual = new Date(System.currentTimeMillis());
        Date fechaExpiracion = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(fechaActual)
                .setExpiration(fechaExpiracion)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
        return token;
    }

    public String obtenerUsernameDelJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public boolean validarToken(String token) {

        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new ServicioAppException("Firma JWT no valida");
        } catch (MalformedJwtException ex) {
            throw new ServicioAppException("Token JWT no valida");
        } catch (ExpiredJwtException ex) {
            throw new ServicioAppException("Token JWT caducado");
        } catch (UnsupportedJwtException ex) {
            throw new ServicioAppException("Token JWT no compatible");
        } catch (IllegalArgumentException ex) {
            throw new ServicioAppException("La cadena claims JWT esta vacia");
        }
    }
}
