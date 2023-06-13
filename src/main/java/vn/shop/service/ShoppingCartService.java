package vn.shop.service;

import org.springframework.stereotype.Service;
import vn.shop.entities.CartItem;
import vn.shop.entities.Product;

import java.util.Collection;

/**
 * @author DongTHD
 *
 */
@Service
public interface ShoppingCartService {

	int getCount();

	double getAmount();

	void clear();

	Collection<CartItem> getCartItems();

	void remove(CartItem item);

	void add(CartItem item);

	void remove(Product product);

}
