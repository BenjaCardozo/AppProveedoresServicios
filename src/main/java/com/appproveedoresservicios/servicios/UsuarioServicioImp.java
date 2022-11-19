package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.AuthenticationRequest;
import com.appproveedoresservicios.dto.AuthenticationResponse;
import com.appproveedoresservicios.entidades.Usuario;
import com.appproveedoresservicios.excepciones.InvalidCredentialsException;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.repositorios.UsuarioRepositorio;
import com.appproveedoresservicios.seguridad.JwtTokenProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//, UserDetailsService
@Service
public class UsuarioServicioImp implements UsuarioServicio, UserDetailsService{

    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    FotoServicioImp fotoServicioImp;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest)
        throws InvalidCredentialsException {
        Usuario usuario = usuarioRepositorio.findByCorreo(authenticationRequest.getCorreo());
        if (usuario == null) {
            throw new InvalidCredentialsException("Correo o clave invalida..");
        }
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getCorreo(),
                authenticationRequest.getClave()));

        UserDetails userDetails = loadUserByUsername(usuario.getCorreo());
        
        return new AuthenticationResponse(userDetails.getUsername(), jwtTokenProvider.generarToken(userDetails));
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByCorreo(username);
        
        if(usuario != null){
            
            List<GrantedAuthority> permisos = new ArrayList();
            
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString());
            
            permisos.add(p);
            
            /*ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            
            HttpSession sesion = attr.getRequest().getSession(true);
            
            sesion.setAttribute("usuariosession", usuario);*/
            
            return  new User(usuario.getCorreo(), usuario.getClave(), permisos);
        }else{
            return null;
        }
    }
    
    /*
    @Override
    public void eliminarUsuario(Long id) throws Exception {
        findById(id);
        fotoServicioImp.eliminarFoto(findById(id).getFoto().getId());
        usuarioRepositorio.deleteById(id);
    }

    @Override
    public void darBajaUsuario(Long id) throws Exception {
        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setAlta(Boolean.FALSE);

            usuarioRepositorio.save(usuario);
        }
    }

    @Override
    public void darAltaUsuario(Long id) throws Exception {
        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setAlta(Boolean.FALSE);

            usuarioRepositorio.save(usuario);
        }
    }
*/
    @Override
    public Usuario findById(Long id) throws ResourceNotFoundException {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new ResourceNotFoundException("Ese usuario no existe");
        }
    }

    @Override
    public boolean buscaPorCorreo(String correo) {
        Usuario usuario = usuarioRepositorio.findByCorreo(correo);
        
        return usuario != null;
    }
}
