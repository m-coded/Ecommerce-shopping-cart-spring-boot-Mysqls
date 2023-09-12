package com.ecommerce2.ecommerce2.LocalRepository;

import java.util.Optional;


import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce2.ecommerce2.model.LocalUser;
@Repository

public interface LocalUserRepository  extends ListCrudRepository<LocalUser, Long> {
	
	Optional<LocalUser> findByUsernameIgnoreCase(String username);

	  Optional<LocalUser> findByEmailIgnoreCase(String email);
}
