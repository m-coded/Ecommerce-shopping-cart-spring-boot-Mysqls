package com.ecommerce2.ecommerce2.LocalRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.ecommerce2.ecommerce2.model.LocalUser;
import com.ecommerce2.ecommerce2.model.VerificationToken;

public interface VerificationTokenRepository extends ListCrudRepository<VerificationToken,Long> {

	Optional<VerificationToken> findByToken(String token);

	  void deleteByUser(LocalUser user);

	  List<VerificationToken> findByUser_IdOrderByIdDesc(Long id);
}
