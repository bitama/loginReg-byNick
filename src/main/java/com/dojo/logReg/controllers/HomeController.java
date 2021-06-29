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

import com.dojo.logReg.models.Task;
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

	public String success(Model model,HttpSession session) {
		Long id=(Long)session.getAttribute("userId");
		model.addAttribute("user",userServ.findUserById(id));
//		model.addAttribute("thisUser",session.getAttribute("user"));//after for success page name
		model.addAttribute("allTasks",userServ.getAllTasks());
		return "success.jsp";
	}
	

   @GetMapping("/newTask")
   public String newTask(@ModelAttribute("task") Task task) {
	   return "newTask.jsp";
   }
   
   @RequestMapping(value="/create",method=RequestMethod.POST)
   public String create(@Valid @ModelAttribute("task")Task task,BindingResult result,Model model,HttpSession session) {
        if(result.hasErrors()) {
		   System.out.println("inside create...............");
		   return "newTask.jsp";
	   }else {
		   
		   Long userId=(Long)session.getAttribute("userId");
		   User user =userServ.findUserById(userId);
		   task.setUser(user);
		   userServ.create(task);
		   System.out.println(task);
		   return "redirect:/success";
	   }
   }
   
   
   @GetMapping("/oneTask/{id}")
   public String oneTask(@PathVariable("id")Long id ,Model model,HttpSession session) {
	   Long tsk=(Long)session.getAttribute("userId");
	   model.addAttribute("user",userServ.findUserById(tsk));
	   Task task =userServ.viewOneTask(id);
	   model.addAttribute("thisTask",task);
	   return "oneTask.jsp";
   }
   
   
   
   @GetMapping("editTask/{id}")
   public String editTask(@PathVariable("id") Long id,@ModelAttribute("task") Task task,Model model) {
	   Task taskToshow = userServ.getTask(id);
	   model.addAttribute("thisTask",taskToshow);
	   return "editTask.jsp";
   }
   
   
   
   @RequestMapping(value="/updateTask/{id}", method=RequestMethod.POST)
   public String updateTask(@PathVariable("id")Long id ,@Valid @ModelAttribute("task") Task task,BindingResult result,Model model, HttpSession session) {

       if (result.hasErrors()) {
    	   Task taskToshow = userServ.getTask(id);
    	   model.addAttribute("thisTask",taskToshow);
           return "editTask.jsp";
       } else {
    	   Long ids=(Long)session.getAttribute("userId");
    	   model.addAttribute("user",userServ.findUserById(ids));
    	   task.setUser(userServ.findUserById(ids));
           userServ.updateTask(task);
           return "redirect:/success";
       }
   }
   
   
   @RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		userServ.deleteTask(id);
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
	
   @GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
}
}
