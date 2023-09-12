package com.ecommerce2.ecommerce2.Controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce2.ecommerce2.Service.OrderService;
import com.ecommerce2.ecommerce2.model.LocalUser;
import com.ecommerce2.ecommerce2.model.WebOrder;


@RestController
@RequestMapping("/order")
public class OrderController {
	private OrderService orderService;

	 
	  public OrderController(OrderService orderService) {
	    this.orderService = orderService;
	  }

	  /**
	   * Endpoint to get all orders for a specific user.
	   * @param user The user provided by spring security context.
	   * @return The list of orders the user had made.
	   */
	  @GetMapping
	  public List<WebOrder> getOrders(@AuthenticationPrincipal LocalUser user) {
	    return orderService.getOrders(user);
	  }

}
