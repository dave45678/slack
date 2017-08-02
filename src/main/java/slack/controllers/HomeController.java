package slack.controllers;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import slack.models.Group;
import slack.models.User;
import slack.services.GroupService;
import slack.services.UserService;
import slack.validators.UserValidator;


@Controller
public class HomeController {
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping("/")
	   public String index(Model model){
	        return "homepage";
	  }
	
	@RequestMapping("/chat")
	public String chat() {
		return "chat";
	}
	
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    
    //go to new group page
    @RequestMapping("/newgroup")
    public String showNewGroup(Model model) {
    	//get the database for users
    	
    	ArrayList<User> users = userService.findAll();
    	model.addAttribute("userlist",users);
    	
    	model.addAttribute("group",new Group());
    	return "newgroup";
    }
    
    
    //change to variable name for which page you are coming from and send the post to homepage instead
    //do this for all form/new stuff
    //create new group - post
   
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("user", user);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "registration";
    }
}
