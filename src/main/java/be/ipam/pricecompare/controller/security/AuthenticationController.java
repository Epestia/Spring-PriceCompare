package be.ipam.pricecompare.controller.security;


import java.util.Objects;

import be.ipam.pricecompare.config.security.AuthenticationException;
import be.ipam.pricecompare.config.security.JwtTokenRequest;
import be.ipam.pricecompare.config.security.JwtTokenResponse;
import be.ipam.student.config.security.userdetails.MyUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthenticationController {

  @Value("Authorization")
  private String tokenHeader;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private be.ipam.pricecompare.config.security.JwtTokenUtil jwtTokenUtil;
  @Autowired
  private UserDetailsService userDetailsService;

  //Methode du controller pour recevoir un token (Adresse jwt.get.token.uri dans application properties)
  //@RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
  @PostMapping(value = "/api/authenticate")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
      throws AuthenticationException {
    Authentication auth = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    final MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal(); //userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String token = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok(new JwtTokenResponse(token));
  }

  //Methode du controller pour rafraichir un token (Adresse jwt.refresh.token.uri dans application properties)
  //@RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
  @RequestMapping(value = "/api/refresh", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    MyUserDetails user = (MyUserDetails) userDetailsService.loadUserByUsername(username);
    if (jwtTokenUtil.canTokenBeRefreshed(token)) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  //Exception
  @ExceptionHandler({ AuthenticationException.class })
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  //Methode d'authentification appelée dans createAuthenticationToken
  private Authentication authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);
    try {
      return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new AuthenticationException("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("INVALID_CREDENTIALS", e);
    }
  }

}

