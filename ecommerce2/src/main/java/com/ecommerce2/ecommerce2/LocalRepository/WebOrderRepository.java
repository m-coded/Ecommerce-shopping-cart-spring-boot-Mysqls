package com.ecommerce2.ecommerce2.LocalRepository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.ecommerce2.ecommerce2.model.LocalUser;
import com.ecommerce2.ecommerce2.model.WebOrder;

public interface WebOrderRepository extends ListCrudRepository<WebOrder,Long> {
	List<WebOrder> findByUser(LocalUser user);

}
