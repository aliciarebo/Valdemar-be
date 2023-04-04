package com.apirest.valdemarbe.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.service.InvalidatedTokenService;
import com.apirest.valdemarbe.service.JwtUserDetailsService;
import com.apirest.valdemarbe.service.UserService;
import com.apirest.valdemarbe.service.WishlistService;
import com.apirest.valdemarbe.config.JwtTokenUtil;
import com.apirest.valdemarbe.JwtRequest;
import com.apirest.valdemarbe.JwtResponse;
import com.apirest.valdemarbe.model.entitybean.Rol;
import com.apirest.valdemarbe.model.entitybean.User;
import com.apirest.valdemarbe.model.entitybean.Wishlist;

@RestController
@RequestMapping("/rest")
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private InvalidatedTokenService invalidatedTokenService;

	@Autowired
	WishlistService wishlistService;

	@GetMapping("/rol/{id}")
	public ResponseEntity<?> findByRol(@PathVariable("id") int id) {
		return new ResponseEntity<List<User>>(userService.findByRol(id), HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());

		String token = jwtTokenUtil.generateToken(userDetails);

		String email = jwtTokenUtil.getEmailFromToken(token);

		User user = userService.findByEmail(email);

		return ResponseEntity.ok(new JwtResponse(token, user));
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
		String password = user.getPassword();
		user.setPassword(bcryptEncoder.encode(password));
		user.setEnable(1);
		user.setRol(new Rol(2, "user"));

		int result = userService.saveUser(user);

		if (result == 1) {
			Wishlist wishlist = new Wishlist();
			wishlist.setUser(user);
			wishlistService.saveWishlist(wishlist);
			return ResponseEntity.ok(user);
		}

		return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request) {
		// invalidar el token de usuario y redirigirlo al login
		String authHeader = request.getHeader("Authorization");
		String token = null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
		}

		if (token != null) {
			invalidatedTokenService.addInvalidatedToken(token);

			if (invalidatedTokenService.isTokenInvalidated(token)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El token ya ha sido invalidado.");
			}
		}
		return ResponseEntity.ok("Logout exitoso");
	}

	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}