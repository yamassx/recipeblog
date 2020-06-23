package jp.co.aivick.app.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jp.co.aivick.app.form.RecipeForm;
import jp.co.aivick.app.service.RecipeService;
import jp.co.aivick.app.entity.Recipe;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
	@Autowired
	RecipeService recipeService;

//fileUpload関連メソッド
	private String getExtension(String filename) {
		int dot = filename.lastIndexOf(".");
		if (dot > 0) {
			return filename.substring(dot).toLowerCase();
		}
		return "";
	}

	private String getUploadFileName(String fileName) {

		return fileName + "_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now())
				+ getExtension(fileName);
	}

	/*
	 * private void createDirectory() { Path path = Paths.get("<<apppath/image>>");
	 * if (!Files.exists(path)) { try { Files.createDirectory(path); } catch
	 * (Exception e) { //エラー処理は省略 } } }
	 */

	private void savefile(MultipartFile file) {
		String filename = getUploadFileName(file.getOriginalFilename());
		Path uploadfile = Paths.get("/uploads/" + filename);
		try (OutputStream os = Files.newOutputStream(uploadfile, StandardOpenOption.CREATE)) {
			byte[] bytes = file.getBytes();
			os.write(bytes);
		} catch (IOException e) {
			// エラー処理は省略
		}
	}
//ここまで

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

		if (recipeForm.getImage() != null) {
			// 画像関連処理
			if (recipeForm.getImage().isEmpty()) {
				// エラー処理は省略
				return "recipes/create.html";
			}
			savefile(recipeForm.getImage());
		}

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
