package com.bienes.raices.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Registro de detalles de autenticación exitosa
        logger.info("Inicio de sesión exitoso para el usuario: {}", authentication.getName());

        // Imprimir parámetros del formulario
        logger.info("Detalles del formulario de inicio de sesión:");
        logger.info("Email: {}", request.getParameter("email"));
        logger.info("Password: {}", request.getParameter("password")); // Ten cuidado con los passwords en los logs

        response.sendRedirect("/home"); // Redirige al usuario a la página de inicio después del éxito
    }
}
