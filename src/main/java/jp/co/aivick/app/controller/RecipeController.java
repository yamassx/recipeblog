package jp.co.aivick.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jp.co.aivick.app.form.RecipeForm;
import jp.co.aivick.app.service.RecipeService;
import jp.co.aivick.app.entity.Recipe;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
	@Autowired
	RecipeService recipeService;

	@GetMapping("/create")
	public String showCreate(Model model) {
		model.addAttribute("recipeForm", new RecipeForm());
		return "recipes/create.html";
	}

	@PostMapping("/create")
	public String create(@Validated RecipeForm recipeForm, BindingResult bindingResult,
			@AuthenticationPrincipal UserDetails user) {
		if (bindingResult.hasErrors()) {
			return "recipes/create.html";
		}
		Recipe recipe = new Recipe();
		recipe.setName(recipeForm.getName());
		recipe.setDetail(recipeForm.getDetail());
		recipeService.create(recipe, user.getUsername());
		return "redirect:/recipes/create";
	}

	@RequestMapping("/json/{id}")
	@ResponseBody
	@JsonSerialize
	public Recipe showModal(@PathVariable String id) {
		return recipeService.findBy(id);
	}
}
