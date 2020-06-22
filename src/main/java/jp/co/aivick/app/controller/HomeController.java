package jp.co.aivick.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jp.co.aivick.app.entity.Recipe;
import jp.co.aivick.app.service.RecipeService;
import jp.co.aivick.app.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private RecipeService recipeService;

	@Autowired
	private UserService userService;

	@RequestMapping
	public String home(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("name", user.getUsername());

		int userId = userService.findId(user.getUsername()).getId();
		List<Recipe> recipes = recipeService.findByUser(userId);

		model.addAttribute("recipes", recipes);
		return "home.html";
	}

	@RequestMapping("/json")
	@ResponseBody
	@JsonSerialize
	public Recipe showModal(String id) {
		return recipeService.findBy(id);
	}
}