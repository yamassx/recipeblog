package jp.co.aivick.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String create(@Validated RecipeForm recipeForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            return "recipes/create.html";
        }
		Recipe recipe = new Recipe();
        recipe.setName(recipeForm.getName());
        recipe.setDetail(recipeForm.getDetail());
        recipeService.create(recipe);
        return "redirect:recipes/create.html";
	}
}
