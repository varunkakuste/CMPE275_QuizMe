/**
 * 
 */
package edu.sjsu.quizme;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.sjsu.quizme.service.layer.IQuizMeService;

/**
 * @author Varun
 * 
 * Handles requests for the application home page.
 *
 */
@Controller
public class QuizMeController {
	
	/**
	 * Logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(QuizMeController.class);
	
	private IQuizMeService quizMeService;
	
	/**
	 * @param quizMeService
	 */
	@Autowired
	public QuizMeController(IQuizMeService quizMeService) {
		this.quizMeService = quizMeService;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Class: QuizMeController <-> Method: home() Start");
		model.addAttribute("dataFromDaoLayer", quizMeService.getHome());
		logger.info("Class: QuizMeController <-> Method: home() End");
		return "welcome";
	}
}
