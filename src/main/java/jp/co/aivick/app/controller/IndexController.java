package jp.co.aivick.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.aivick.app.entity.Recipe;
import jp.co.aivick.app.service.RecipeService;

@Controller
@RequestMapping("/")
public class IndexController{
	@Autowired
	private RecipeService recipeService;
	
	@RequestMapping
	public String index(Model model) {
		List<Recipe> recipes = recipeService.findAll();
		model.addAttribute("recipes", recipes);
		
		return "/index.html";
	}
}