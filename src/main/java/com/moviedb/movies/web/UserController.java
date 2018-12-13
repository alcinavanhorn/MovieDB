package com.moviedb.movies.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moviedb.movies.domain.SignUpForm;
import com.moviedb.movies.domain.User;
import com.moviedb.movies.domain.UserRepository;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
    private UserRepository urepository; 
	
	//Sets up sign up form
    @RequestMapping(value = "signup")
    public String addStudent(Model model){
    	model.addAttribute("signupform", new SignUpForm());
        return "signup";
    }	

    //Saves sign up form information, verifies and checks for matches
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult) { //Calls the sign up form
    	if (!bindingResult.hasErrors()) {
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { //Checks if the passwords match in the sign up form	
	    		String pwd = signupForm.getPassword(); 
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User(); //Initializes a new user and their information
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	if (urepository.findByUsername(signupForm.getUsername()) == null) { //Checks if the username exists already
		    		urepository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");  //Rejects username if it exists in the service	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match"); //Rejects password if it does not match the previous password input
    			return "signup";
    		}
    	}
    	else { //If all is verified and matched, the user is signed up for the service
    		return "signup";
    	}
    	return "redirect:/login";    	
    }    
    
	//RESTful services to get all users
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public @ResponseBody List<User> userListRest() {
    	return (List<User>) urepository.findAll();
    }
    
    //RESTful services to get user by id
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<User> findUserRest(@PathVariable("id") Long userId) {
		return urepository.findById(userId);
	}

	// RESTful service to delete a user
	@RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody String userDeleteRest(@PathVariable("id") Long userId) {
		urepository.deleteById(userId);
		return "User deleted";
	}
}
