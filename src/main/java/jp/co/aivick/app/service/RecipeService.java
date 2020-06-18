package jp.co.aivick.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.aivick.app.dao.RecipeDao;
import jp.co.aivick.app.entity.Recipe;

@Service
public class RecipeService {
	@Autowired
	private RecipeDao recipeDao;
	
	 public Recipe findBy(String recipe_id) {
	        return recipeDao.find(recipe_id);
	    }

	    public List<Recipe> findAll() {
	        return this.recipeDao.findAll();
	    }

	    @Transactional
	    public Recipe create(Recipe recipe) {
	        Recipe newRecipe = new Recipe();
	        newRecipe.setRecipe_id(recipe.getRecipe_id());
	        newRecipe.setName(recipe.getName());
	        newRecipe.setDetail(recipe.getDetail());
	        
	        recipeDao.insert(newRecipe);
	        return recipe;
	    }	
}
