package com.ecommerce2.ecommerce2.LocalRepository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.ecommerce2.ecommerce2.model.Address;

public interface AddressRepository  extends ListCrudRepository<Address,Long>{
	List<AddressRepository> findByUser_Id(Long id);
}
