package com.chat.websocket.webcam.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
//comprueba si hay o no token, si no lo hay manda un 404 
	private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

	/*
	 * Este método se implementa para manejar el inicio de una solicitud no
	 * autenticada. Recibe tres parámetros:
	 * 
	 * HttpServletRequest request: Representa la solicitud HTTP que desencadenó el
	 * error de autenticación. HttpServletResponse response: Representa la respuesta
	 * HTTP que se enviará al cliente. AuthenticationException authException:
	 * Representa la excepción de autenticación que ocurrió.
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
        String errorMessage = "No autorizado";

        if (authException instanceof BadCredentialsException) {
            errorMessage = "Credenciales incorrectas";
        } else if (authException instanceof LockedException) {
            errorMessage = "La cuenta está bloqueada";
        } else if (authException instanceof DisabledException) {
            errorMessage = "La cuenta está deshabilitada";
        } else if (authException instanceof AccountExpiredException) {
            errorMessage = "La cuenta ha expirado";
        } else if (authException instanceof UsernameNotFoundException) {
            errorMessage = "El usuario no es quién dice ser";
        } else if (authException instanceof InternalAuthenticationServiceException) {
            errorMessage = "El usuario no es quién dice ser";
        }  
        /* Finalmente, se envía una respuesta de error al cliente con el código de estado HTTP 401
         *  (No autorizado) y el mensaje de error correspondiente. Esto indica al cliente que la solicitud no se autoriza debido a un problema de autenticación.
         * */

        logger.error("Error en el método commence: " + authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
		
	}

}