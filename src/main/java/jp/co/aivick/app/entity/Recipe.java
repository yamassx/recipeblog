package jp.co.aivick.app.entity;

import java.nio.file.Path;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "recipe")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_id")
	private Integer recipe_id;

	@Column(name = "name")
	private String name;

	@Column(name = "detail")
	private String detail;

	@Column(name = "user_id")
	private Integer user_id;

	@Column(name = "image")
	private String image;

	public Integer getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(Integer recipe_id) {
		this.recipe_id = recipe_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(Path imageUrl) {
		this.image = imageUrl.toString();
	}
}
