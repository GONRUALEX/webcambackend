package com.chat.websocket.webcam.security.jwt;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.chat.websocket.webcam.serviceimpl.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*Este filtro se encarga de procesar las solicitudes HTTP entrantes */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	/*
	 * El método doFilterInternal implementa la lógica principal del filtro de
	 * solicitud JwtTokenFilter. Su objetivo es procesar las solicitudes HTTP
	 * entrantes y realizar la autenticación basada en tokens JWT.
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		/*
		 * Se inicia el método tomando como entrada un objeto HttpServletRequest que
		 * representa la solicitud HTTP entrante y un objeto HttpServletResponse que
		 * representa la respuesta HTTP que se enviará. Luego, se llama al método
		 * getToken(request) para intentar obtener un token JWT de la solicitud.
		 */
		try {
			String token = getToken(request);
			if (token != null && jwtProvider.validateToken(token)) {// comprobamos token valido y que existe
				String nombreUsuario = jwtProvider.getNameUserFromToken(token);// obtemos el usuario a partir del
																					// token
				UserDetails userDetails = userDetailsService.loadUserByUsername(nombreUsuario); // creamos un
																								// userdetails
				/*
				 * UsernamePasswordAuthenticationToken es una implementación de la interfaz
				 * Authentication en Spring Security que se utiliza para representar una
				 * solicitud de autenticación. En este caso, se está creando un nuevo objeto
				 * auth. El primer argumento (userDetails) es el principal (principal) de la
				 * autenticación, que generalmente representa al usuario autenticado.
				 * userDetails es un objeto que contiene detalles del usuario, como su nombre de
				 * usuario, contraseñas (aunque en este caso es null), roles y otras
				 * informaciones relacionadas con la autenticación. El segundo argumento (null)
				 * se utiliza para las credenciales, que normalmente serían la contraseña del
				 * usuario. Sin embargo, en este caso, las credenciales se establecen como null
				 * porque no se están utilizando contraseñas en la autenticación basada en token
				 * JWT. El tercer argumento (userDetails.getAuthorities()) es una colección de
				 * autoridades (roles) que el usuario tiene. Estas autoridades se obtienen del
				 * objeto userDetails.
				 * SecurityContextHolder.getContext().setAuthentication(auth):
				 * 
				 * SecurityContextHolder es una clase que se utiliza para administrar el
				 * contexto de seguridad en una aplicación Spring Security. getContext() obtiene
				 * el contexto actual. setAuthentication(auth) se utiliza para establecer la
				 * autenticación actual en el contexto de seguridad. Esto significa que el
				 * usuario representado por userDetails está autenticado y se le permite acceder
				 * a recursos protegidos.
				 */
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());// autenticamos el userdetails que será el usuario que va a estar
														// autenticado
				SecurityContextHolder.getContext().setAuthentication(auth);// le pasamos el usuario al contexto de
																			// autenticación
			}

		} catch (Exception e) {
			logger.error("failen el método filter");
		}
		//response.setHeader("X-Content-Type-Options", "nosniff");
		filterChain.doFilter(request, response);// si todo va bien llamamos a doFilter para proseguir
	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");

		if (null != header && header.startsWith("Bearer ")) {
			return header.replace("Bearer ", "");
		}
		return null;
	}

}
