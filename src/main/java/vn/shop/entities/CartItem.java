package vn.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Giang Pham
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

	
	private Long id;
	private String name;
	private double unitPrice;
	private int quantity;
	private double totalPrice;
	private Product product;
}
