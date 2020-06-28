package jp.co.aivick.app.controller;

import jp.co.aivick.app.entity.User;
import jp.co.aivick.app.form.UserForm;
import jp.co.aivick.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {
	@Autowired
	UserService userService;

	@GetMapping("/index")
	public String showSignup(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "signup/index.html";
	}

	@PostMapping("/index")
	public String register(@Validated UserForm userForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup/index.html";
		}

		User user = new User();
		user.setPassword(userForm.getPassword());
		user.setLoginId(userForm.getLoginId());
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());

		userService.create(user);

		return "redirect:/login";
	}
}