package jp.co.aivick.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.aivick.app.dao.RecipeDao;
import jp.co.aivick.app.dao.UserDao;
import jp.co.aivick.app.entity.Recipe;

@Service
public class RecipeService {
	@Autowired
	private RecipeDao recipeDao;

	@Autowired
	private UserDao userDao;

	public Recipe findBy(String recipe_id) {
		Integer id = Integer.parseInt(recipe_id);
		return recipeDao.find(id);
	}

	public List<Recipe> findAll() {
		return this.recipeDao.findAll();
	}

	public List<Recipe> findByUser(int userId) {
		return recipeDao.findByUser(userId);
	}

	@Transactional
	public Recipe create(Recipe recipe, String userName) {
		Recipe newRecipe = new Recipe();
		newRecipe.setRecipe_id(recipe.getRecipe_id());
		newRecipe.setName(recipe.getName());
		newRecipe.setDetail(recipe.getDetail());

		int user_id = userDao.findId(userName).getId();
		newRecipe.setUser_id(user_id);

		recipeDao.insert(newRecipe);
		return recipe;
	}
}
