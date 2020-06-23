package jp.co.aivick.app.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.aivick.app.entity.Recipe;

@ConfigAutowireable
@Dao
public interface RecipeDao {
	@Select
	Recipe find(int recipe_id);

	@Select
	List<Recipe> findAll();

	@Insert
	int insert(Recipe recipe);
	
	@Select
	List<Recipe> findByUser(int userId);
}
