package com.ecommerce2.ecommerce2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce2.ecommerce2.LocalRepository.WebOrderRepository;
import com.ecommerce2.ecommerce2.model.LocalUser;
import com.ecommerce2.ecommerce2.model.WebOrder;

@Service
public class OrderService {
	@Autowired
	WebOrderRepository repository;
	
	 public List<WebOrder> getOrders(LocalUser user) {
		    return repository.findByUser(user);
		  }

}
