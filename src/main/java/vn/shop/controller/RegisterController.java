package vn.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.shop.entities.User;
import vn.shop.repository.UserRepository;
import vn.shop.service.SendMailService;
import vn.shop.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author DongTHD
 *
 */
@Controller
public class RegisterController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	SendMailService sendMailService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	HttpSession session;




	@GetMapping("/register")
	public ModelAndView registerForm(ModelMap model) {
		model.addAttribute("user", new User());
		return new ModelAndView("web/register", model);
	}





	@PostMapping("/register")
	public String register(ModelMap model, @Validated @ModelAttribute("user") User dto, BindingResult result,
			@RequestParam("password") String password) throws MessagingException {
		if (result.hasErrors()) {
			return "web/register";
		}
		if (!checkEmail(dto.getEmail())) {
			model.addAttribute("error", "Email này đã được sử dụng!");
			return "web/register";
		}
		session.removeAttribute("otp");
		int random_otp = (int) Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);;
		session.setAttribute("otp", random_otp);
		String body = "<div>\r\n" + "<h3>Mã xác thực OTP của bạn là: <span style=\"color:#119744; font-weight: bold;\">"
				+ random_otp + "</span></h3>\r\n" + "</div>";


		sendMailService.sendNoAttachment(dto.getEmail(), "Đăng kí tài khoản", body);

		model.addAttribute("user", dto);
		model.addAttribute("message", "Mã xác thực OTP đã được gửi tới Email : " + dto.getEmail() + " , hãy kiểm tra Email của bạn!");

		return "/web/confirmOtpRegister";
	}

	// check email
	public boolean checkEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user!=null)
		{
			return false;
		}
		return true;
	}






	@PostMapping("/confirmOtpRegister")
	public ModelAndView confirmRegister(ModelMap model, @ModelAttribute("user") User dto,
			@RequestParam("password") String password, @RequestParam("otp") String otp) {
		if (otp.equals(String.valueOf(session.getAttribute("otp")))) {
			dto.setPassword(bCryptPasswordEncoder.encode(password));
			dto.setRegisterDate(new Date());
			dto.setStatus(true);
			dto.setAvatar(null);

			userRepository.save(dto);

			userService.addUserRole(dto.getUserId(), 1);

			session.removeAttribute("otp");
			model.addAttribute("message", "Đăng kí thành công");
			return new ModelAndView("web/login");
		}

		model.addAttribute("user", dto);
		model.addAttribute("error", "Mã xác thực OTP không chính xác, hãy thử lại!");
		return new ModelAndView("web/confirmOtpRegister", model);
	}



}
