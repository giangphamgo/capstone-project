package vn.shop.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.shop.convert.ConvertImage;
import vn.shop.entities.User;
import vn.shop.repository.Base64Repository;
import vn.shop.repository.CategoryRepository;
import vn.shop.repository.ProductRepository;
import vn.shop.repository.UserRepository;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class productStock {
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


}
