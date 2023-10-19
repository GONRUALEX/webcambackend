package com.chat.websocket.webcam.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chat.websocket.webcam.entity.User;
import com.chat.websocket.webcam.entity.UserPrincipal;
import com.chat.websocket.webcam.repository.UserRepository;

/*
 * La clase implementa la interfaz UserDetailsService. Esta interfaz es parte de Spring Security 
 * y define un método loadUserByUsername que debe implementarse para cargar los detalles de un usuario.
 * */

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	/*
	 * loadUserByUsername(String nombreOrEmail): Este método se implementa para cargar los 
	 * detalles de usuario basados en un nombre de usuario o correo electrónico proporcionado como argumento.

		Primero, se utiliza el repositorio de usuarios para buscar un usuario con el nombre de usuario o 
		correo electrónico especificado. Esto se hace llamando al método findByNombreUsuarioOrEmail en usuarioRepository.
		 La llamada a orElse(null) devuelve un objeto Optional, y si no se encuentra ningún usuario, se asigna null a la
		 variable usuario.
		Luego, se verifica si el usuario es nulo. Si no se encuentra un usuario con el nombre de usuario o
		 correo electrónico proporcionado, se lanza una excepción de UsernameNotFoundException. 
		 Esto es típico en el proceso de autenticación, ya que indica que el nombre de usuario o 
		 contraseña proporcionados son incorrectos.
		Si se encuentra el usuario, se llama al método UsuarioPrincipal.build(usuario). Este método 
		está destinado a construir un objeto UsuarioPrincipal, que es una implementación de la 
		interfaz UserDetails. Un objeto UsuarioPrincipal contiene los detalles de autenticación
		 del usuario, como su nombre de usuario y contraseña (normalmente cifrada), roles, 
		 autoridades y otros detalles necesarios para la autenticación y la autorización en Spring Security.
		
		Finalmente, el método loadUserByUsername devuelve el objeto UsuarioPrincipal construido, 
		que se utilizará en el proceso de autenticación de Spring Security.
	 */
	@Override
	public UserDetails loadUserByUsername(String nameOrEmail) {
		User user = userRepository.findByNameUserOrEmail(nameOrEmail, nameOrEmail).orElse(null);
		if (null==user) {
			throw new UsernameNotFoundException("Name o password incorrect");
		}
		return UserPrincipal.build(user);

	}
}