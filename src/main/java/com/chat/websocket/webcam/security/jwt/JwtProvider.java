package com.chat.websocket.webcam.security.jwt;

import java.security.Key;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.chat.websocket.webcam.bean.JwtDto;
import com.chat.websocket.webcam.bean.MainTableDto;
import com.chat.websocket.webcam.entity.User;
import com.chat.websocket.webcam.entity.UserPrincipal;
import com.chat.websocket.webcam.mapper.MainTableMapper;
import com.chat.websocket.webcam.repository.UserRepository;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

	private static final Logger logger = LogManager.getLogger(JwtProvider.class);

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private int expiration;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MainTableMapper mainTableMapper;

	/*
	 * Este método genera un token JWT a partir de la información del usuario que se
	 * encuentra en el objeto Authentication
	 */
	public String generateToken(Authentication authentication) {
		/*
		 * Se obtiene el nombre de usuario y las roles del usuario a partir del objeto
		 * Authentication.
		 */
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		List<String> roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		/*
		 * Jwts.builder(): Esta es la primera parte del proceso. Crea un generador de
		 * tokens JWT (JwtBuilder) que te permite construir un nuevo token.
		 * setSubject(usuarioPrincipal.getUsername()): Con este método, se establece el
		 * "sujeto" (subject) del token. El sujeto se refiere a la identidad del usuario
		 * o entidad a la que se refiere el token. En este caso,
		 * usuarioPrincipal.getUsername() proporciona el nombre de usuario del usuario
		 * autenticado como sujeto del token. claim("roles", roles): Aquí, se agrega una
		 * reclamación personalizada al token. En este caso, la reclamación se llama
		 * "roles" y contiene una lista de roles asociados al usuario. Esta información
		 * adicional se incluye en el token para que pueda ser utilizada en la
		 * autorización. setIssuedAt(new Date()): Establece la fecha y hora de emisión
		 * del token (Issued At - iat). La marca de tiempo actual se obtiene con new
		 * Date() y se utiliza como momento de emisión del token. setExpiration(new
		 * Date(new Date().getTime() + expiration)): Establece la fecha y hora de
		 * expiración del token. expiration es el período de tiempo en segundos durante
		 * el cual el token es válido. Se le suma al tiempo actual (new
		 * Date().getTime()) para calcular la fecha de expiración. Cuando el tiempo
		 * actual más el valor de expiration se alcanza, el token se considerará
		 * inválido. signWith(this.getSecret(secret)): Este método se utiliza para
		 * firmar el token con una clave secreta. this.getSecret(secret) obtiene la
		 * clave secreta que se utilizará para la firma. La firma es una parte
		 * importante de la seguridad del token, ya que permite verificar la integridad
		 * del token y que no ha sido modificado por terceros. compact(): Finalmente,
		 * este método compacta todo el contenido del token y devuelve una cadena que
		 * representa el token JWT válido y firmado.
		 */
		return Jwts.builder().setSubject(userPrincipal.getUsername()).claim("roles", roles).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration)).signWith(this.getSecret(secret)).compact();
	}

	/*
	 * Jwts.parserBuilder(): Esto crea un generador de parser (analizador) para
	 * tokens JWT. El parser se utiliza para verificar y decodificar un token JWT.
	 * setSigningKey(getSecret(secret)): Con este método, se establece la clave
	 * secreta utilizada para verificar la firma del token. La función
	 * getSecret(secret) se utiliza para obtener la clave secreta a partir de la
	 * configuración de la aplicación. La clave secreta debe coincidir con la clave
	 * utilizada para firmar el token originalmente. build(): Este método construye
	 * el parser JWT con la clave secreta especificada. El parser estará listo para
	 * verificar y decodificar tokens JWT. parseClaimsJws(token): El método
	 * parseClaimsJws(token) toma el token JWT como argumento y lo analiza,
	 * verificando su firma y estructura. Si el token es válido, devuelve un objeto
	 * Jws<Claims>, que contiene las "reclamaciones" (claims) del token.
	 * getBody().getSubject(): El objeto Jws<Claims> tiene un método getBody() que
	 * proporciona acceso a las reclamaciones del token. Luego, getSubject() se
	 * utiliza para obtener el sujeto del token, que generalmente es el nombre de
	 * usuario del usuario al que se refiere el token.
	 */
	public String getNameUserFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token).getBody()
				.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("token mal formado");
		} catch (UnsupportedJwtException e) {
			logger.error("token no soportado");
		} catch (ExpiredJwtException e) {
			logger.error("token expirado");
		} catch (IllegalArgumentException e) {
			logger.error("token vacío");
		} catch (SignatureException e) {
			logger.error("fail en la firma");
		}

		return false;
	}

	/*
	 * byte[] secretBytes = Decoders.BASE64URL.decode(secret): En esta línea, la
	 * cadena secret, que se asume que contiene la clave secreta en formato
	 * Base64URL, se decodifica a un arreglo de bytes (byte[]) utilizando la clase
	 * Decoders.BASE64URL. El formato Base64URL es una variante segura para URL del
	 * formato Base64 estándar y se utiliza comúnmente para representar datos
	 * binarios en una forma que es segura para las URL. La decodificación convierte
	 * la cadena en su representación binaria. Keys.hmacShaKeyFor(secretBytes):
	 * Después de decodificar la cadena secreta, se utiliza la clase Keys para crear
	 * una instancia de clave (Key) de HMAC (Hash-based Message Authentication Code)
	 * utilizando los bytes de la cadena secreta. La clave de HMAC es una clave
	 * simétrica que se utiliza para firmar y verificar tokens JWT. Los tokens JWT
	 * son firmados con la clave secreta y se verifican utilizando la misma clave.
	 * Keys.hmacShaKeyFor(secretBytes) crea una clave HMAC basada en SHA (Secure
	 * Hash Algorithm) utilizando los bytes de la cadena secreta. return ...:
	 * Finalmente, el método getSecret devuelve la clave HMAC generada. Esta clave
	 * se utilizará en otros métodos, como signWith al generar tokens JWT y
	 * setSigningKey al validar tokens JWT, para asegurarse de que los tokens sean
	 * auténticos y no hayan sido modificados por terceros. En resumen, el método
	 * getSecret se encarga de tomar una cadena secreta, decodificarla desde
	 * Base64URL, y crear una clave HMAC a partir de los bytes de la cadena. Esta
	 * clave se utiliza en el proceso de generación y validación de tokens JWT para
	 * garantizar la seguridad y autenticidad de los tokens.
	 * 
	 */
	private Key getSecret(String secret) {
		byte[] secretBytes = Decoders.BASE64URL.decode(secret);
		return Keys.hmacShaKeyFor(secretBytes);
	}

	public String refreshToken(JwtDto jwtDto) throws ParseException {
		try {
			Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(jwtDto.getToken());
			
		} catch (ExpiredJwtException e) {
			JWT jwt = JWTParser.parse(jwtDto.getToken());
			JWTClaimsSet claims = jwt.getJWTClaimsSet();
			String nombreUsuario = claims.getSubject();
			List<String> roles = (List<String>) claims.getClaim("roles");
			logger.debug("Refresh token {} user {}", jwtDto.getToken(), nombreUsuario);
			return Jwts.builder().setSubject(nombreUsuario).claim("roles", roles).setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime() + expiration)).signWith(this.getSecret(secret))
					.compact();
		} 
		logger.error("Token refresh error, {}", jwtDto.getToken());
		return null;
	}
	
	public List<MainTableDto> getRoles(JwtDto jwtDto) {
		try {
			Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(jwtDto.getToken());
			JWT jwt = JWTParser.parse(jwtDto.getToken());
			JWTClaimsSet claims = jwt.getJWTClaimsSet();
			Optional<User> user = userRepository.findByNameUser(claims.getSubject());
			return user.get().getRoles().stream().map(role->mainTableMapper.toDto(role)).collect(Collectors.toList());
		}catch(Exception e){
			logger.error("Get roles error with token , {}", jwtDto.getToken());
		}
		return null;
	}

}