package com.dojo.logReg.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dojo.logReg.models.User;
import com.dojo.logReg.services.UserService;
import com.dojo.logReg.validations.UserValidator;

@Controller
public class HomeController {
	private final UserService userServ;
    private final UserValidator userValidator;
     
	public HomeController(UserService userServ, UserValidator userValidator) {
		super();
		this.userServ = userServ;
		this.userValidator = userValidator;
	}
	@GetMapping("/")
	public String index(@ModelAttribute("user")User user) {
		return "index.jsp";
	}
	@GetMapping("/success")
	public String success() {
		return "success.jsp";
	}
	
	@PostMapping("/registration")
	public String register(@Valid @ModelAttribute("user")User newUser,BindingResult result,HttpSession session) {
		userValidator.validate(newUser,result);
		if(result.hasErrors()) {
			return "index.jsp";
		}
		else {
			userServ.registerUser(newUser);
			session.setAttribute("userID", newUser.getId());
			return "redirect:/success";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email")String email,@RequestParam("password")String password,Model model,HttpSession session,RedirectAttributes redirectAttributes) {
	 boolean isAuth = userServ.authenticateUser(email, password);
	 if(isAuth) {
		 User u = userServ.findByEmail(email);
		 session.setAttribute("userID", u.getId());
		 return "redirect:/success";
	 }
	 else {
		 redirectAttributes.addFlashAttribute("error","Imvalid login attempt!!!");
		 return "redirect:/";
	}
	}
	


	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
}
}
