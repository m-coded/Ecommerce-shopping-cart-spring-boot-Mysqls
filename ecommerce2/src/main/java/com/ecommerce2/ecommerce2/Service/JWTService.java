package com.ecommerce2.ecommerce2.Service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ecommerce2.ecommerce2.model.LocalUser;

import jakarta.annotation.PostConstruct;

@Service
public class JWTService {
	/** The secret key to encrypt the JWTs with. */
	  @Value("${jwt.algorithm.key}")
	  private String algorithmKey;
	  /** The issuer the JWT is signed with. */
	  @Value("${jwt.issuer}")
	  private String issuer;
	  /** How many seconds from generation should the JWT expire? */
	  @Value("${jwt.expiryInSeconds}")
	  private int expiryInSeconds;
	  /** The algorithm generated post construction. */
	  private Algorithm algorithm;
	  /** The JWT claim key for the username. */
	  private static final String USERNAME_KEY = "USERNAME";
	  private static final String VERIFICATION_EMAIL_KEY = "VERIFICATION_EMAIL";


	  
	  @PostConstruct
	  public void postConstruct() {
	    algorithm = Algorithm.HMAC256(algorithmKey);
	  }
	  
	  public String generateJWT(LocalUser user) {
		    return JWT.create()
		        .withClaim(USERNAME_KEY, user.getUsername())
		        .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
		        .withIssuer(issuer)
		        .sign(algorithm);
		  }
	  
	  public String generateVerificationJWT(LocalUser user) {
		    return JWT.create()
		        .withClaim(VERIFICATION_EMAIL_KEY, user.getEmail())
		        .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
		        .withIssuer(issuer)
		        .sign(algorithm);
		  }
	  
	  
	  public String getUsername(String token) {
		    DecodedJWT jwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
		    return jwt.getClaim(USERNAME_KEY).asString();
		  }
	  
	  
	  
	  
	  
	  
	  
}
