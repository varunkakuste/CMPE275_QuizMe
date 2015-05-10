package edu.sjsu.quizme;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.sjsu.quizme.models.CategoryModel;
import edu.sjsu.quizme.models.DifficultyLevelModel;
import edu.sjsu.quizme.models.LoginModel;
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
	private static final String AUTHENTICATION_URL = "http://quizmeauthentication.elasticbeanstalk.com";
	
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
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,@ModelAttribute("information") String infoMessage) {
		model.addAttribute("loginForm", new LoginModel());
		model.addAttribute("information", infoMessage);
		return "login";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/loginuser", method = RequestMethod.GET)
	public String loginUser(Model model, @ModelAttribute("loginForm") @Valid LoginModel loginModel, BindingResult bindingResult, HttpServletRequest request) {
		String redirection = "login";
		UserModel user = null;
		List<CategoryModel> categoryList = null;
		List<DifficultyLevelModel> difficultyList = null;
		RestTemplate restTemplate = null;
		try {
			if (!bindingResult.hasErrors()) {
				restTemplate = new RestTemplate();
				user = restTemplate.getForObject(AUTHENTICATION_URL+"/loginAuthentication?userName="+loginModel.getUserName()+"&password="+loginModel.getPassword(), UserModel.class);
				if(user != null) {
					categoryList = quizMeService.getCategories();
					difficultyList = quizMeService.getDifficultyLevels();
					
					session = request.getSession();
					session.setAttribute("userId", Integer.valueOf(user.getUserId()));
					session.setAttribute("userName", user.getUserName());
					session.setAttribute("userDetails", user);
					session.setAttribute("categoryList", categoryList);
					session.setAttribute("difficultyList", difficultyList);
					redirection = "redirect:/getTaken";
				} else {
					model.addAttribute("loginError", "Error Logging in");
				}
			}
		} catch (Exception exception) {
			model.addAttribute("loginError", "Error Logging in");
		}
		return redirection;
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
	public String signUp(@ModelAttribute("signUpForm") @Valid UserModel userModel, BindingResult bindingResult, Model model, 
			final RedirectAttributes redirectAttributes) { 
		boolean isSignedUp = false;
		String redirection = "signUp";
		RestTemplate restTemplate = null;
		try {
			if (!bindingResult.hasErrors()) {
				if(!userModel.getPassword().equals(userModel.getConfirmPassword())) {
					model.addAttribute("signingUpError", "passwords mismatch");
				} else {
					restTemplate = new RestTemplate();
					isSignedUp = restTemplate.postForObject(AUTHENTICATION_URL+"/signup", userModel, Boolean.class);
					if(isSignedUp) {
						redirectAttributes.addFlashAttribute("information", "User signedup successfully");
						redirection = "redirect:/login";
					} else {
						model.addAttribute("signingUpError", "Error Signing up");
					}
				}
			} 
		} catch (Exception exception) {
			model.addAttribute("signingUpError", "Error Signing up");
		}
		return redirection;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/changeSettings", method = RequestMethod.GET)
	public String changeSettings(Model model, HttpServletRequest request) {
		String redirection = "signUp";
		session = request.getSession();
		model.addAttribute("changeSettings", "Enable Change Settings button");
		UserModel user = (UserModel) session.getAttribute("userDetails");
		if(user != null) {
			model.addAttribute("signUpForm", user);
		} else {
			model.addAttribute("signUpForm", new UserModel());
		}
		return redirection;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/savechanges", method = RequestMethod.POST)
	public String saveChanges(@ModelAttribute("signUpForm") @Valid UserModel userModel, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) { 
		String redirection = "signUp";
		model.addAttribute("changeSettings", "Enable Change Settings button");
		RestTemplate restTemplate = null;
		try {
			if (!bindingResult.hasErrors()) {
				if(!userModel.getPassword().equals(userModel.getConfirmPassword())) {
					model.addAttribute("signingUpError", "passwords mismatch");
				} else {
					userModel.setUserId(((Integer)session.getAttribute("userId")).intValue());
					restTemplate = new RestTemplate();
					restTemplate.put(AUTHENTICATION_URL+"/savechanges", userModel);
					redirectAttributes.addFlashAttribute("information", "User signedup successfully");
					redirection = "redirect:/login";
				}
			} 
		} catch (Exception exception) {
			model.addAttribute("signingUpError", "Error Signing up");
		}
		return redirection;
	}
	
	/**
	 * logoutPage()
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logoutPage(HttpServletRequest request) {
		logger.info("HomeController class --->>> logoutPage() Method Start");
		request.getSession().invalidate();
		logger.info("HomeController class --->>> logoutPage() Method End");
        return "redirect:/login";
    }
	
	/**
	 * help()
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help(HttpServletRequest request) {
		logger.info("HomeController class --->>> help() Method Start/End");
        return "help";
    }
}
