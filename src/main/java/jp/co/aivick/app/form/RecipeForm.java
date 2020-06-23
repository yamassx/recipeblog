package jp.co.aivick.app.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class RecipeForm {

	private Integer recipe_id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String detail;

	private MultipartFile image;

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

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	

}
