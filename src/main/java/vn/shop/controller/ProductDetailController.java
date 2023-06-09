package vn.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.shop.commom.CommomDataService;
import vn.shop.entities.Product;
import vn.shop.entities.User;
import vn.shop.repository.ProductRepository;

import java.util.List;

/**
 * @author DongTHD
 *
 */
@Controller
public class ProductDetailController extends CommomController{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CommomDataService commomDataService;

	@GetMapping(value = "/productDetail")
	public String productDetail(@RequestParam("id") Long id, Model model, User user) {

		Product product = productRepository.findById(id).orElse(null);


		commomDataService.commonData(model, user);
		listProductByCategory10(model, product.getCategory().getCategoryId());

		model.addAttribute("product", product);

		return "web/productDetail";
	}
	
	// Gợi ý top 10 sản phẩm cùng loại
	public void listProductByCategory10(Model model, Long categoryId) {
		List<Product> products = productRepository.listProductByCategory10(categoryId);
		model.addAttribute("productByCategory", products);
	}
}
