package com.ecommerce2.ecommerce2.Authmodel;

public class LoginResponse {
	 private String jwt;
	  /** Was the login process successful? */
	  private boolean success;
	  /** The reason for failure on login. */
	  private String failureReason;

	  public String getJwt() {
	    return jwt;
	  }

	  public void setJwt(String jwt) {
	    this.jwt = jwt;
	  }

	  public boolean isSuccess() {
	    return success;
	  }

	  public void setSuccess(boolean success) {
	    this.success = success;
	  }

	  public String getFailureReason() {
	    return failureReason;
	  }

	  public void setFailureReason(String failureReason) {
	    this.failureReason = failureReason;
	  }


}
