package com.appproveedoresservicios.seguridad;

import com.appproveedoresservicios.dto.request.AuthenticationRequest;
import com.appproveedoresservicios.dto.response.AuthenticationResponse;
import com.appproveedoresservicios.entidades.Usuario;
import com.appproveedoresservicios.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService/* implements UserDetailsService*/ {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest)
            throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByCorreo(authenticationRequest.getCorreo());
        
        if (usuario == null) {
            throw new UsernameNotFoundException("No existe una cuenta con ese nombre.");
        }
        
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getCorreo(),
                        authenticationRequest.getClave()));
        
        SecurityContextHolder.getContext().setAuthentication(auth);
        
        String token = jwtTokenProvider.generarToken(auth.getName());
        
        return new AuthenticationResponse(usuario.getId(), usuario.getRol().toString(), token);
    }
}
