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

import com.dojo.logReg.models.Idea;
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
//	This is for all ideas and user who created them
	@GetMapping("/success")
	public String success(Model model,HttpSession session) {
		Long id=(Long)session.getAttribute("userId");
		model.addAttribute("user",userServ.findUserById(id));
//		model.addAttribute("thisUser",session.getAttribute("user"));//after for success page name
		model.addAttribute("allIdeas",userServ.getAllIdeas());
		return "success.jsp";
	}
	
//	for create new Idea
   @GetMapping("/newIdea")
   public String newIdea(@ModelAttribute("idea") Idea idea) {
	   return "newIdea.jsp";
   }
   
   @RequestMapping(value="/create",method=RequestMethod.POST)
   public String create(@Valid @ModelAttribute("idea")Idea idea,BindingResult result,Model model,HttpSession session,RedirectAttributes redirectAttributes) {
//	   redirectAttributes.addAttribute("error", "testErrors");
	   if(result.hasErrors()) {
		   System.out.println("inside create...............");
		   return "newIdea.jsp";
	   }else {
		   
		   Long userId=(Long)session.getAttribute("userId");
		   User user =userServ.findUserById(userId);
		   idea.setUser(user);
		   userServ.create(idea);
		   System.out.println(idea);
		   return "redirect:/success";
	   }
   }
   
   
   @GetMapping("/oneIdea/{id}")
   public String oneIdea(@PathVariable("id")Long id ,Model model,HttpSession session) {
	   Long ids=(Long)session.getAttribute("userId");
	   model.addAttribute("user",userServ.findUserById(ids));
	   Idea idea =userServ.viewOneIdea(id);
	   model.addAttribute("thisIdea",idea);
	   return "oneIdea.jsp";
   }
   
   
   
   @GetMapping("editIdea/{id}")
   public String editIdea(@PathVariable("id") Long id,@ModelAttribute("idea") Idea idea,Model model) {
	   Idea ideaToshow = userServ.getIdea(id);
	   model.addAttribute("thisIdea",ideaToshow);
	   return "editIdea.jsp";
   }
   
   
   @RequestMapping(value="/updateIdea/{id}", method=RequestMethod.POST)
   public String update(@PathVariable("id")Long id ,@Valid @ModelAttribute("idea") Idea idea,BindingResult result,Model model, HttpSession session, RedirectAttributes redirectAttributes) {
//	   redirectAttributes.addAttribute("error", "testErrors");
       if (result.hasErrors()) {
    	   Idea ideaToshow = userServ.getIdea(id);
    	   model.addAttribute("thisIdea",ideaToshow);
           return "editIdea.jsp";
       } else {
    	   Long ids=(Long)session.getAttribute("userId");
    	   model.addAttribute("user",userServ.findUserById(ids));//Attension
    	   idea.setUser(userServ.findUserById(ids));
           userServ.updateIdea(idea);
           return "redirect:/success";
       }
   }
   
   
   @RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		userServ.deleteIdea(id);
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
