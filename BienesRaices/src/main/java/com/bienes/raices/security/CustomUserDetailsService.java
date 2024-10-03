package com.bienes.raices.security;

import com.bienes.raices.model.Rol;
import com.bienes.raices.model.Usuario;
import com.bienes.raices.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));

        // Usa el logger en lugar de System.out.println
        logger.info("Cargando usuario: {}, Rol: {}", usuario.getEmail(), usuario.getRol());

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                mapRolesToAuthorities(usuario.getRol())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Rol rol) {
        if (rol != null) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rol.getNombre()));
        }
        return Collections.emptyList();
    }
}
