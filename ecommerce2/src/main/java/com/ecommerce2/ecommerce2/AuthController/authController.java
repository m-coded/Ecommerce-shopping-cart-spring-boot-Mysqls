package com.ecommerce2.ecommerce2.AuthController;

import com.ecommerce2.ecommerce2.Authmodel.LoginBody;
import com.ecommerce2.ecommerce2.Authmodel.LoginResponse;
import com.ecommerce2.ecommerce2.Authmodel.Registration;
import com.ecommerce2.ecommerce2.Eexcption.EmailFailureException;
import com.ecommerce2.ecommerce2.Eexcption.UserAlreadyExistsException;
import com.ecommerce2.ecommerce2.Eexcption.UserNotVerifiedException;
import com.ecommerce2.ecommerce2.Service.UserService;
import com.ecommerce2.ecommerce2.model.LocalUser;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Auth")
public class authController {
 @Autowired
    private UserService service;

   

  @PostMapping("/re")
    public ResponseEntity registers( @Valid @RequestBody Registration registration) {
	  try {
	     service.register(registration);
	      return ResponseEntity.ok().build();
	    } catch (EmailFailureException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }}

@PostMapping("/login")
public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
  String jwt = null;
  try {
    jwt = service.loginUser(loginBody);
  } catch (UserNotVerifiedException ex) {
    LoginResponse response = new LoginResponse();
    response.setSuccess(false);
    String reason = "USER_NOT_VERIFIED";
    if (ex.isNewEmailSent()) {
      reason += "_EMAIL_RESENT";
    }
    response.setFailureReason(reason);
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
  } catch (EmailFailureException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
  if (jwt == null) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  } else {
    LoginResponse response = new LoginResponse();
    response.setJwt(jwt);
    response.setSuccess(true);
    return ResponseEntity.ok(response);
  }
}


@PostMapping("/verify")
public ResponseEntity verifyEmail(@RequestParam String token) {
  if (service.verifyUser(token)) {
    return ResponseEntity.ok().build();
  } else {
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
  }
}

/**
 * Gets the profile of the currently logged-in user and returns it.
 * @param user The authentication principal object.
 * @return The user profile.
 */
@GetMapping("/me")
public LocalUser getLoggedInUserProfile(@AuthenticationPrincipal LocalUser user) {
  return user;
}}