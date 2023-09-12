package com.ecommerce2.ecommerce2.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.ecommerce2.ecommerce2.LocalRepository.LocalUserRepository;
import com.ecommerce2.ecommerce2.model.LocalUser;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service

public class JWTRequestFilter extends OncePerRequestFilter {
	
	private JWTService jwtService;
	LocalUserRepository localUserRepository;
	
	public JWTRequestFilter(JWTService jwtService, LocalUserRepository localUserRepository) {
		super();
		this.jwtService = jwtService;
		this.localUserRepository = localUserRepository;
	}

	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 String tokenHeader = request.getHeader("Authorization");
		    UsernamePasswordAuthenticationToken token = checkToken(tokenHeader);
		    if (token != null) {
		      token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		    }
		    filterChain.doFilter(request, response);
		  }
		
	
	private UsernamePasswordAuthenticationToken checkToken(String token) {
	    if (token != null && token.startsWith("Bearer ")) {
	      token = token.substring(7);
	      try {
	        String username = jwtService.getUsername(token);
	        Optional<LocalUser> opUser = localUserRepository.findByUsernameIgnoreCase(username);
	        if (opUser.isPresent()) {
	          LocalUser user = opUser.get();
	         
	            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList());
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            return authentication;
	       
	        }
	      } catch (JWTDecodeException ex) {
	      }
	    }
	    SecurityContextHolder.getContext().setAuthentication(null);
	    return null;
	  }

}
