package vn.shop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.shop.entities.User;
import vn.shop.repository.CategoryRepository;
import vn.shop.repository.OrderDetailRepository;
import vn.shop.repository.ProductRepository;
import vn.shop.repository.UserRepository;
import vn.shop.service.OrderService;
import vn.shop.service.ProductService;
import vn.shop.service.UserService;

import java.security.Principal;
import java.util.List;

/**
 * @author DongTHD
 *
 */
@Controller
@RequestMapping("/admin")
public class IndexAdminController{
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Autowired
	UserService userService;
	
	@ModelAttribute(value = "user")
	public User user(Model model, Principal principal, User user) {

		if (principal != null) {
			model.addAttribute("user", new User());
			user = userRepository.findByEmail(principal.getName());
			model.addAttribute("user", user);
		}

		return user;
	}

	@GetMapping(value = "/home")
	public String index(Model model) {





		int countProduct=  (int)productService.countProductMonth();
		int countProductMonth= (int)productService.countProductMonthPT();
		model.addAttribute("countProduct", countProduct);
		model.addAttribute("countProductMonth", countProductMonth);


		int countPSale=productService.countProductSale();
		double countPPhantram=productService.countProduct();
		model.addAttribute("countp", countPSale);
		model.addAttribute("countppt", countPPhantram);


		double countOrder= orderService.countOder();
		int countOrderMonth=orderService.countOderMonth();
		model.addAttribute("countOrder", countOrder);
		model.addAttribute("countOrdermonth", countOrderMonth);

		double countOrderCXN= orderService.countOderCXN();
		int countOrderMonthCXN=orderService.countOderCXNMonth();
		model.addAttribute("countOrderCXN", countOrderCXN);
		model.addAttribute("countOrdermonthCXN", countOrderMonthCXN);

		double countOrderDGH= orderService.countOderDGH();
		int countOrderMonthDGH=orderService.countOderDGHMonth();
		model.addAttribute("countOrderDGH", countOrderDGH);
		model.addAttribute("countOrdermonthDGH", countOrderMonthDGH);

		double countOrderDTT= orderService.countOderDTT();
		int countOrderMonthDTT=orderService.countOderDTTMonth();
		model.addAttribute("countOrderDTT", countOrderDTT);
		model.addAttribute("countOrdermonthp", countOrderMonthDTT);

		int countCate= (int) categoryRepository.countCategoryAll();
		model.addAttribute("countCategory", countCate);


		List<Object[]> listReportCommonngay = orderDetailRepository.repoWhereDay();
		model.addAttribute("listReportCommonngay", listReportCommonngay);

		List<Object[]> listReportCommonthang = orderDetailRepository.repoWhereMonth();
		model.addAttribute("listReportCommonthang", listReportCommonthang);

		List<Object[]> listReportCommonnam = orderDetailRepository.repoWhereYear();
		model.addAttribute("listReportCommonnam", listReportCommonnam);

		List<Object[]> listReportCommonquy = orderDetailRepository.repoWhereQUARTER();
		model.addAttribute("listReportCommonnquy", listReportCommonquy);


		
		return "admin/index";
	}
}
