package com.dojo.logReg.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dojo.logReg.models.Menu;
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
//	This is for all menus and user who created them
	@GetMapping("/success")
	public String success(Model model,HttpSession session) {
		Long id=(Long)session.getAttribute("userId");
		model.addAttribute("user",userServ.findUserById(id));
//		model.addAttribute("thisUser",session.getAttribute("user"));//after for success page name
		model.addAttribute("allMenus",userServ.getAllMenus());
		return "success.jsp";
	}
	
//	for create new menu
   @GetMapping("/newMenu")
   public String newMenu(@ModelAttribute("menu") Menu menu) {
	   return "newMenu.jsp";
   }
   
   @RequestMapping(value="/create",method=RequestMethod.POST)
   public String create(@Valid @ModelAttribute("menu")Menu menu,BindingResult result,Model model,HttpSession session) {
	   if(result.hasErrors()) {
		   System.out.println("inside create...............");
		   return "newMenu.jsp";
	   }else {
		   
		   Long userId=(Long)session.getAttribute("userId");
		   User user =userServ.findUserById(userId);
		   menu.setUser(user);
		   userServ.create(menu);
		   System.out.println(menu);
		   return "redirect:/success";
	   }
   }
   
   
   @GetMapping("/oneMenu/{id}")
   public String oneMenu(@PathVariable("id")Long id ,Model model) {
	   Menu menu =userServ.viewOneMenu(id);
	   model.addAttribute("thisMenu",menu);
	   return "oneMenu.jsp";
   }
   
   
   
   @GetMapping("editMenu/{id}")
   public String editMenu(@PathVariable("id") Long id,@ModelAttribute("menu") Menu menu,Model model) {
	   Menu menuToshow = userServ.getMenu(id);
	   model.addAttribute("thisMenu",menuToshow);
	   return "editMenu.jsp";
   }
   
   
   @RequestMapping(value="/updateMenu/{id}", method=RequestMethod.POST)
   public String update(@Valid @ModelAttribute("language") Menu menu, BindingResult result) {
       if (result.hasErrors()) {
           return "editMenu.jsp";
       } else {
           userServ.updateMenu(menu);
           return "redirect:/success";
       }
   }
   
   
   @RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		userServ.deleteMenu(id);
		return "redirect:/success";
	}
   
	
	
	@PostMapping("/registration")
	public String register(@Valid @ModelAttribute("user")User newUser,BindingResult result,HttpSession session) {
		userValidator.validate(newUser,result);
		if(result.hasErrors()) {
			return "index.jsp";
		}
		else {
			userServ.registerUser(newUser);
			session.setAttribute("userId", newUser.getId());
//			session.setAttribute("user", newUser);//this goes after to diasplay name on sucsess page
			return "redirect:/success";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email")String email,@RequestParam("password")String password,Model model,HttpSession  session,RedirectAttributes redirectAttributes) {
	 boolean isAuth = userServ.authenticateUser(email, password);
	 if(isAuth) {
		 User u = userServ.findByEmail(email);
		 session.setAttribute("userId", u.getId());
		 return "redirect:/success";
	 }
	 else {
		 redirectAttributes.addFlashAttribute("error","Imvalid login attempt!!!");
		 return "redirect:/";
	}
	}
	
//	@PostMapping("/makeMenu")
//	public String makeMenu(@Valid @ModelAttribute("menu") Menu menu,BindingResult result ,Model model,HttpSession session){
//		if(result.hasErrors()) {
//          return"/newMenu.jsp";
//		}else {
//        Long userId=(Long)session.getAttribute("userId");
//			User user=userServ.findUserById(userId);
//			userServ.create(menu);
//			menu.setUser(user);
//		   return "redirect:/success";
//	}
//}
	
	

    @GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
}
}
