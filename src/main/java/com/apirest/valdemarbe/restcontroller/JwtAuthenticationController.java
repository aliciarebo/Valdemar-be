package com.apirest.valdemarbe.restcontroller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.service.InvalidatedTokenService;
import com.apirest.valdemarbe.service.JwtUserDetailsService;
import com.apirest.valdemarbe.service.UserService;
import com.apirest.valdemarbe.config.JwtTokenUtil;
import com.apirest.valdemarbe.JwtRequest;
import com.apirest.valdemarbe.JwtResponse;
import com.apirest.valdemarbe.model.entitybean.User;

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

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());

		String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
		String password = user.getPassword();
		user.setPassword(bcryptEncoder.encode(password));
		user.setEnable(1);
		return ResponseEntity.ok(userService.saveUser(user));
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