package vn.fs.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import vn.fs.convert.ConvertImage;
import vn.fs.entities.*;
import vn.fs.repository.Base64Repository;
import vn.fs.repository.CategoryRepository;
import vn.fs.repository.ProductRepository;
import vn.fs.repository.UserRepository;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductDateController {
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

    public ProductDateController(CategoryRepository categoryRepository,
                             ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // show list product - table list
    @ModelAttribute("/productDates")
    public List<Product> showProduct(Model model) {

        List<Product> products = productRepository.findAllDate();


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

    @GetMapping(value = "/productDates")
    public String products(Model model, Principal principal) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "admin/productDates";
    }




    // show select option ở add product
    @ModelAttribute("categoryList")
    public List<Category> showCategory(Model model) {
        List<Category> categoryList = categoryRepository.findAll();

        model.addAttribute("categoryList", categoryList);

        return categoryList;
    }



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
