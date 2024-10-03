package com.bienes.raices.service;

import com.bienes.raices.model.Usuario;
import com.bienes.raices.model.Rol;
import com.bienes.raices.repository.UsuarioRepository;
import com.bienes.raices.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        // Encriptar la contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        
        // Asignar rol USER por defecto
        Rol userRol = rolRepository.findByNombre("USER")
                .orElseThrow(() -> new RuntimeException("Error: Rol de usuario no encontrado."));
        usuario.setRol(userRol);
        
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(usuario.getId()).orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setEmail(usuario.getEmail());
            // No actualizamos la contraseña aquí por seguridad
            // Si quieres permitir cambios de contraseña, crea un método separado con verificaciones adicionales
            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }
}
