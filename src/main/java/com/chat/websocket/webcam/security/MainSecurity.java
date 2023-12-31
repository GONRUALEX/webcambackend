package com.chat.websocket.webcam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import com.chat.websocket.webcam.security.jwt.JwtEntryPoint;
import com.chat.websocket.webcam.security.jwt.JwtTokenFilter;
import com.chat.websocket.webcam.serviceimpl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MainSecurity {

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	JwtEntryPoint jwtEntryPoint;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenFilter jwtTokenFilter;

	AuthenticationManager authenticationManager;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		/*
		 * En esta línea, se obtiene una instancia de AuthenticationManagerBuilder a
		 * través del objeto http. AuthenticationManagerBuilder se utiliza para
		 * configurar cómo se autenticarán los usuarios en la aplicación.
		 */
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		/*
		 * Aquí, se configura el servicio de detalles de usuario (userDetailsService) y
		 * el codificador de contraseñas (passwordEncoder). Esto significa que se está
		 * definiendo cómo se recuperarán los detalles del usuario (como nombre de
		 * usuario y roles) y cómo se codificarán y verificarán las contraseñas de los
		 * usuarios.
		 */
		builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
		/*
		 * authenticationManager = builder.build();: Se crea un AuthenticationManager a
		 * partir del AuthenticationManagerBuilder configurado en los pasos anteriores.
		 * El AuthenticationManager se utiliza para autenticar a los usuarios.
		 */
		authenticationManager = builder.build();
		/*
		 * http.authenticationManager(authenticationManager);: Se configura el
		 * AuthenticationManager creado anteriormente en la instancia de http. Esto
		 * indica que Spring Security utilizará este AuthenticationManager para
		 * gestionar la autenticación de los usuarios.
		 */
		http.authenticationManager(authenticationManager);
		/**
		 * http.csrf().disable();: Se deshabilita la protección contra CSRF (Cross-Site
		 * Request Forgery). Esto significa que las solicitudes CSRF no se verificarán,
		 * lo que puede ser útil en algunas situaciones, pero debe hacerse con
		 * precaución. http.cors();: Se configura la política de Cross-Origin Resource
		 * Sharing (CORS) en la aplicación, lo que permite que los recursos se compartan
		 * con dominios de origen cruzado.
		 * 
		 * 
		 **/
		/*
		http.headers(headers -> 
	    
	       headers.xssProtection(
	    	        xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK)
	    	     ).contentSecurityPolicy(
	    	        cps -> cps.policyDirectives("default-src 'none'; img-src 'self'; font-src 'self'; connect-src 'self' http://localhost:4200; script-src 'self' http://localhost:4200; style-src 'self' 'unsafe-inline'; frame-ancestors 'none'")));
		*/
		http.csrf((csrf -> csrf.disable()));
		http.cors(Customizer.withDefaults());
		
		/**
		 * http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);:
		 * Se configura la gestión de sesiones para que se utilice una política
		 * "STATELESS", lo que significa que la aplicación no mantendrá información de
		 * sesión para los usuarios. Esto es común en aplicaciones RESTful y Stateless,
		 * donde la autenticación se basa en tokens JWT u otros mecanismos sin estado.
		 */

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		/*
		 * http.authorizeHttpRequests()...: Aquí se configuran las reglas de
		 * autorización para diferentes rutas o URLs. Se permite el acceso sin
		 * autenticación a las rutas especificadas, como "/auth/", "/email-password/", y
		 * las rutas relacionadas con Swagger. Para todas las demás rutas, se requiere
		 * autenticación.
		 *
		 */
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/users/**","/api/auth/**", "/swagger-ui.html/**", "/email-password/**",
				"/v3/api-docs/**", "/swagger-ui/index.html", "/api-docs").permitAll().anyRequest().authenticated());
		/**
		 * http.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);: Se
		 * configura un punto de entrada personalizado para manejar las excepciones de
		 * autenticación. Esto significa que si un usuario no autenticado intenta
		 * acceder a una ruta protegida, se redirigirá a este punto de entrada para
		 * gestionar la respuesta.
		 */
		http.exceptionHandling(exc -> exc.authenticationEntryPoint(jwtEntryPoint));
		/**
		 * http.addFilterBefore(jwtTokenFilter,
		 * UsernamePasswordAuthenticationFilter.class);: Se agrega un filtro
		 * personalizado llamado jwtTokenFilter antes del filtro
		 * UsernamePasswordAuthenticationFilter. Esto permite que la aplicación valide y
		 * procese los tokens JWT en las solicitudes antes de que se realice la
		 * autenticación estándar basada en nombre de usuario y contraseña.
		 */

		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();

	}

}
