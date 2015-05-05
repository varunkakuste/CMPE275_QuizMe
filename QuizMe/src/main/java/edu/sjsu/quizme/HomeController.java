package edu.sjsu.quizme;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.sjsu.quizme.models.UserModel;
import edu.sjsu.quizme.service.layer.IQuizMeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private IQuizMeService quizMeService;
	private HttpSession session = null;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/usersignup", method = RequestMethod.GET)
	public String userSignUp(Model model) {
		model.addAttribute("signUpForm", new UserModel());
		return "signUp";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(@ModelAttribute("signUpForm") @Valid UserModel userModel, BindingResult bindingResult, Model model) { 
		boolean isSignedUp = false;
		try {
			if (!bindingResult.hasErrors()) {
				if(!userModel.getPassword().equals(userModel.getConfirmPassword())) {
					model.addAttribute("signingUpError", "passwords mismatch");
				} else {
					isSignedUp = quizMeService.signUp(userModel);
					if(isSignedUp) {
						model.addAttribute("signedUpInfo", "User signedup successfully");
					} else {
						model.addAttribute("signingUpError", "Error Signing up");
					}
				}
			}
		} catch (Exception exception) {
			System.out.println("Some Exception...");
		}
		return "signUp";
	}
	
}
