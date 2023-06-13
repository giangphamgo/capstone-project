package vn.shop.controller.admin;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.shop.convert.ConvertImage;
import vn.shop.entities.Category;
import vn.shop.entities.Product;
import vn.shop.entities.User;
import vn.shop.repository.Base64Repository;
import vn.shop.repository.CategoryRepository;
import vn.shop.repository.ProductRepository;
import vn.shop.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author DongTHD
 *
 */
@Controller
@RequestMapping("/admin")
public class ProductController{

	@Value("${upload.path}")
	private String pathUploadImage;

	@Autowired
	ProductRepository productRepository;


	@Autowired
	ConvertImage convertImage;

	@Autowired
	Base64Repository base64Repository;



	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UserRepository userRepository;

	@ModelAttribute(value = "user")
	public User user(Model model, Principal principal, User user) {

		if (principal != null) {
			model.addAttribute("user", new User());
			user = userRepository.findByEmail(principal.getName());
			model.addAttribute("user", user);
		}

		return user;
	}

	public ProductController(CategoryRepository categoryRepository,
			ProductRepository productRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	// show list product - table list
	@ModelAttribute("/products")
	public List<Product> showProduct(Model model) {

		List<Product> products = productRepository.findAll();
		for (Product product: products) {
			int endDate=productRepository.EndDateProduct(product.getProductId());
			if(endDate>=0){
				product.setEndDay(endDate);
			}else {
				product.setEndDay(0);
				product.setDiscount(0);
			}
		}


		model.addAttribute("products", products);

		return products;
	}

	@GetMapping(value = "/products")
	public String products(Model model, Principal principal) {
		Product product = new Product();


		model.addAttribute("product", product);

		return "admin/products";
	}

	// add product
	@PostMapping(value = "/addProduct")
	public String addProduct(@ModelAttribute("product") Product product, ModelMap model,
			@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest) throws Exception {

		if (file==null||file.isEmpty()){
			Product productCheckId=  productRepository.getReferenceById(product.getProductId());
			product.setProductImage(productCheckId.getProductNoImage());
		} else {
			String imageConvert=convertImage.encodeImage(file,"jpeg");
			product.setProductImage(imageConvert);
		}

		product.setHide(false);
		product.setStatus(true);
		productRepository.save(product);

		return "redirect:/admin/products";
	}







	// show select option ở add product
	@ModelAttribute("categoryList")
	public List<Category> showCategory(Model model) {
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categoryList", categoryList);

		return categoryList;
	}

	// get Edit brand
	@GetMapping(value = "/editProduct/{id}")
	public String editCategory(@PathVariable("id") Long id, ModelMap model) {
		Product product = productRepository.findById(id).orElse(null);

		model.addAttribute("product", product);

		return "admin/editProduct";
	}

	@GetMapping(value = "/hideProduct/{id}")
	public String hideCategory(@PathVariable("id") Long id, ModelMap model) {
		Product product = productRepository.findById(id).orElse(null);
		if (product.getHide()==false){
			product.setHide(true);
		}else {
			product.setHide(false);
		}
		productRepository.save(product);
//		model.addAttribute("product", product);

		return "redirect:/admin/products";
	}

	@PostMapping(value = "/hideProduct")
	public String hideCategory2(@RequestParam("checkbox") Long id, ModelMap model) {
		Product product = productRepository.findById(id).orElse(null);
		if (product.getHide()==false){
			product.setHide(true);
		}else {
			product.setHide(false);
		}
		productRepository.save(product);
//		model.addAttribute("product", product);

		return "redirect:/admin/products";
	}

	@PostMapping(value = "/hideProductDate")
	public String hideCategoryDate(@RequestParam("checkbox") Long id, ModelMap model) {
		Product product = productRepository.findById(id).orElse(null);
		if (product.getHide()==false){
			product.setHide(true);
		}else {
			product.setHide(false);
		}
		productRepository.save(product);
//		model.addAttribute("product", product);

		return "redirect:/admin/productDates";
	}

	// delete category
	@GetMapping("/deleteProduct/{id}")
	public String delProduct(@PathVariable("id") Long id, Model model) {
//		productRepository.deleteById(id);

		Product product = productRepository.findById(id).orElse(null);
		product.setStatus(false);
		productRepository.save(product);

		model.addAttribute("message", "Delete successful!");

		return "redirect:/admin/products";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
}
