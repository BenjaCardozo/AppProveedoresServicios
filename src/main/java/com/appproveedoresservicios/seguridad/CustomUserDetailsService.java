package com.appproveedoresservicios.seguridad;

import com.appproveedoresservicios.entidades.Usuario;
import com.appproveedoresservicios.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByCorreo(username);
        
        if(usuario != null){
            
            List<GrantedAuthority> permisos = new ArrayList();
            
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString());
            
            permisos.add(p);
            
            return  new User(usuario.getCorreo(), usuario.getClave(), permisos);
        }else{
            throw new UsernameNotFoundException("No se encontro un usuario con ese correo.");
        }
    }
}
