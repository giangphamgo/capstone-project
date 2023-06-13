package vn.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.shop.commom.CommomDataService;
import vn.shop.entities.User;

/**
 * @author DongTHD
 *
 */
@Controller
public class AboutController extends CommomController {

	@Autowired
	CommomDataService commomDataService;

	@GetMapping(value = "/aboutUs")
	public String about(Model model, User user) {

		commomDataService.commonData(model, user);
		return "web/about";
	}
}
