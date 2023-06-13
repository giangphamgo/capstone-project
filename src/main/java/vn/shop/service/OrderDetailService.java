package vn.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.shop.entities.Order;
import vn.shop.repository.OrderRepository;

import java.util.List;

/**
 * @author DongTHD
 *
 */
@Service
public class OrderDetailService {

	@Autowired
	OrderRepository repo;

	public List<Order> listAll() {
		return (List<Order>) repo.findAll();
	}


}
