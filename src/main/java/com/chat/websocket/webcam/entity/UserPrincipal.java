package com.chat.websocket.webcam.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/*Métodos de UserDetails:

	La clase implementa los métodos definidos por la interfaz UserDetails. 
	Estos métodos son necesarios para la integración de Spring Security y se
	 utilizan para proporcionar detalles sobre la autenticación y autorización 
	 del usuario. Los métodos incluyen getAuthorities, getPassword, getUsername, 
	 isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, y isEnabled.
	  En este caso, la mayoría de estos métodos devuelven true o los campos correspondientes.
*/
public class UserPrincipal implements UserDetails {

	private String name;
	private String nameUser;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(String name, String nameUser, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.name = name;
		this.nameUser = nameUser;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	/*
	 * Este método estático se utiliza para construir un objeto UsuarioPrincipal a
	 * partir de un objeto Usuario. Recibe un objeto Usuario, que es probablemente
	 * una entidad que representa a un usuario en la base de datos. Dentro de este
	 * método, se toman las autoridades (roles) del usuario y se transforman en una
	 * colección de objetos SimpleGrantedAuthority. Esto es común en Spring
	 * Security, donde las autoridades se representan como objetos que implementan
	 * GrantedAuthority. Luego, se crea una instancia de UsuarioPrincipal con los
	 * campos del usuario y la colección de autoridades obtenida anteriormente.
	 */
	public static UserPrincipal build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getCode().getCode())).collect(Collectors.toList());
		return new UserPrincipal(user.getName(), user.getNameUser(), user.getEmail(),
				user.getPassword(), authorities);
	}

	/*
	 * Este método devuelve una colección de autoridades (roles) que tiene el
	 * usuario. Las autoridades son representadas como objetos que implementan la
	 * interfaz GrantedAuthority. i un usuario tiene roles como "ROLE_USER" y
	 * "ROLE_ADMIN", entonces la implementación de getAuthorities devolvería una
	 * colección que contiene objetos SimpleGrantedAuthority para cada uno de esos
	 * roles.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	/*
	 * Este método devuelve la contraseña del usuario. Generalmente, se recomienda
	 * que la contraseña sea cifrada o hasheada para la seguridad.
	 */
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	/*
	 * Este método devuelve el nombre de usuario del usuario. Es el identificador
	 * único que se utiliza para iniciar sesión.
	 * 
	 */
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.nameUser;
	}

	/*
	 * Este método verifica si la cuenta del usuario no ha caducado. Devuelve true
	 * si la cuenta está activa y no ha caducado, y false en caso contrario.
	 * Devolvería true si la cuenta del usuario no ha caducado y puede seguir siendo
	 * utilizada para la autenticación.
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	/*
	 * Este método verifica si la cuenta del usuario no está bloqueada. Devuelve
	 * true si la cuenta no está bloqueada y false si está bloqueada. java
	 * Devolvería true si la cuenta del usuario no está bloqueada y se puede
	 * utilizar para la autenticación.
	 */

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * Este método verifica si las credenciales (generalmente la contraseña) del
	 * usuario no han caducado. Devuelve true si las credenciales no han caducado y
	 * false si han caducado.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * Este método verifica si la cuenta del usuario está habilitada. Devuelve true
	 * si la cuenta está habilitada y false si está deshabilitada. ejm:
	 * usuarioRepository.isAccountEnabled(this.nombreUsuario);
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}