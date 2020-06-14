package jp.co.aivick.app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController
{
    @RequestMapping
    public String home(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("name", user.getUsername());
        return "home.html";
    }
}