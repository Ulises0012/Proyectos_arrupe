package com.bienes.raices.controller;

import com.bienes.raices.model.Usuario;
import com.bienes.raices.model.Rol;
import com.bienes.raices.repository.UsuarioRepository;
import com.bienes.raices.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/register")
    public String registrarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        }

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            result.rejectValue("email", "error.usuario", "Ya existe un usuario con este email.");
            return "register";
        }

        try {
            // Usa el logger en lugar de System.out.println
            String encodedPassword = passwordEncoder.encode(usuario.getPassword());
            logger.info("Registrando usuario: {}, Contraseña codificada: {}", usuario.getEmail(), encodedPassword);
            usuario.setPassword(encodedPassword);

            Rol userRol = rolRepository.findByNombre("USER")
                .orElseThrow(() -> new RuntimeException("Error: Rol de usuario no encontrado."));
            usuario.setRol(userRol);
            
            usuarioRepository.save(usuario);
            logger.info("Usuario registrado exitosamente: {}", usuario.getEmail());
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("registroError", "Ocurrió un error durante el registro. Por favor, inténtelo de nuevo.");
            return "redirect:/register";
        }

        redirectAttributes.addFlashAttribute("registroExito", "Registro exitoso. Puedes iniciar sesión ahora.");
        return "redirect:/login";
    }

    // Otros métodos para CRUD de propiedades si los necesitas
}
