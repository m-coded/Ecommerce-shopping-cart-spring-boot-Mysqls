package com.ecommerce2.ecommerce2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="LocalUser_Tb")
@Component
public class LocalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private  String email;
    private  String password;
    private  String firstName;
    private String lastName;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<VerificationToken> verificationTokens =new ArrayList<>();
    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified = false;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

   

	

	public LocalUser(Long id, String username, String email, String password, String firstName, String lastName) {
		
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
    public LocalUser() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	 public List<VerificationToken> getVerificationTokens() {
			return verificationTokens;
		}

		public void setVerificationTokens(List<VerificationToken> verificationTokens) {
			this.verificationTokens = verificationTokens;
		}
		public Boolean getEmailVerified() {
			return emailVerified;
		}

		public void setEmailVerified(Boolean emailVerified) {
			this.emailVerified = emailVerified;
		}
	
}
