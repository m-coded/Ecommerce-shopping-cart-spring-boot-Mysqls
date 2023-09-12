package com.ecommerce2.ecommerce2.Service;

import com.ecommerce2.ecommerce2.Authmodel.LoginBody;
import com.ecommerce2.ecommerce2.Authmodel.Registration;
import com.ecommerce2.ecommerce2.Eexcption.EmailFailureException;
import com.ecommerce2.ecommerce2.Eexcption.UserAlreadyExistsException;
import com.ecommerce2.ecommerce2.Eexcption.UserNotVerifiedException;
import com.ecommerce2.ecommerce2.LocalRepository.LocalUserRepository;
import com.ecommerce2.ecommerce2.LocalRepository.VerificationTokenRepository;
import com.ecommerce2.ecommerce2.Security.Encryption;
import com.ecommerce2.ecommerce2.model.LocalUser;
import com.ecommerce2.ecommerce2.model.VerificationToken;

import jakarta.transaction.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service	
public class UserService {
   @Autowired
    private LocalUser localUser;
    

	@Autowired
    private Registration registration;
    @Autowired
    private  Encryption encryption;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTService jwtService;

    private  LocalUserRepository localUserRepository;
    @Autowired
	private VerificationToken verificationToken;
    @Autowired
    private EmailService emailService;
    @Autowired
    private  VerificationTokenRepository verificationTokenRepository;

    
    
 
   
	  public UserService(LocalUser localUser, Registration registration, Encryption encryption,
				PasswordEncoder passwordEncoder, JWTService jwtService, LocalUserRepository localUserRepository,
				VerificationToken verificationToken) {
			super();
			this.localUser = localUser;
			this.registration = registration;
			this.encryption = encryption;
			this.passwordEncoder = passwordEncoder;
			this.jwtService = jwtService;
			this.localUserRepository = localUserRepository;
			this.verificationToken = verificationToken;
		}
	  
	  
    public  LocalUser register(Registration registration1) throws EmailFailureException {
     if (localUserRepository.findByUsernameIgnoreCase(registration1.getUsername()).isPresent()||
    		 localUserRepository.findByEmailIgnoreCase(registration1.getEmail()).isPresent()) {
	}

        LocalUser user =  new LocalUser();
        user.setUsername(registration1.getUsername());
        user.setEmail(registration1.getEmail());
        user.setPassword(passwordEncoder.encode(registration1.getPassword()));
        user.setFirstName(registration1.getFirstName());
        user.setLastName(registration1.getLastName());
        VerificationToken verificationToken = createVerificationToken(user);
        emailService.sendVerificationEmail(verificationToken);
        return localUserRepository.save(user);

    }
    private VerificationToken createVerificationToken(LocalUser user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationJWT(user));
        verificationToken.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
        verificationToken.setUser(user);
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
      }
    
    public String loginUser(LoginBody loginBody) throws UserNotVerifiedException, EmailFailureException {
        Optional<LocalUser> opUser = localUserRepository.findByUsernameIgnoreCase(loginBody.getUsername());
        if (opUser.isPresent()) {
          LocalUser user = opUser.get();
			
				if (encryption.verifyPassword(loginBody.getPassword(), user.getPassword())) {
				      if (user.getEmailVerified()) {
				        return jwtService.generateJWT(user);
				        } else {
				          java.util.List<VerificationToken> verificationTokens = user.getVerificationTokens();
				          boolean resend = verificationTokens.size() == 0 ||
				              verificationTokens.get(0).getCreatedTimestamp().before(new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000)));
				          if (resend) {
				            VerificationToken verificationToken = createVerificationToken(user);
				            verificationTokenRepository.save(verificationToken);
				            emailService.sendVerificationEmail(verificationToken);
				          }
				          throw new UserNotVerifiedException(resend);
				        }}}
		    
		    return null;
		  
				}
				    
				
				
		
    @Transactional
    public boolean verifyUser(String token) {
      Optional<VerificationToken> opToken = verificationTokenRepository.findByToken(token);
      if (opToken.isPresent()) {
        VerificationToken verificationToken = opToken.get();
        LocalUser user = verificationToken.getUser();
        if (!user.getEmailVerified()) {
          user.setEmailVerified(true);
          localUserRepository.save(user);
          verificationTokenRepository.deleteByUser(user);
          return true;
        }
      }
      return false;
    }


}
