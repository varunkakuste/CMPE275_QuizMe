/**
 * 
 */
package edu.sjsu.quizme;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.sjsu.quizme.models.QuestionModel;
import edu.sjsu.quizme.models.QuizModel;
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
	private HttpSession session = null;
	
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
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/createQuiz", method = RequestMethod.GET)
	public String createQuiz(Model model, HttpServletRequest request) {
		logger.info("Class: QuizMeController <-> Method: createQuiz() Start");
		session = request.getSession();
		QuizModel quizModel = (QuizModel) session.getAttribute("quizForm");
		if(quizModel == null) {
			quizModel = new QuizModel();
			session.setAttribute("quizForm", quizModel);
		}
		
		model.addAttribute("quizForm", quizModel);
		
		logger.info("Class: QuizMeController <-> Method: createQuiz() End");
		return "createQuiz";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/proceedToQuestion", method = RequestMethod.GET)
	public String proceedToQuestion(@ModelAttribute("quizForm") QuizModel quizModelAttribute, @ModelAttribute("questionForm") QuestionModel questionModel, Model model, HttpServletRequest request) {
		logger.info("Class: QuizMeController <-> Method: proceedToQuestion() Start");
		session = request.getSession();
		QuizModel quizModel = (QuizModel) session.getAttribute("quizForm");
		if(quizModel != null && quizModel.getCategory() == 0 && quizModel.getDifficultyLevel() == 0 
				&& (quizModel.getQuestionsList() == null || quizModel.getQuestionsList().isEmpty())) {
			session.setAttribute("quizForm", quizModelAttribute);
		}
		model.addAttribute("questionForm", questionModel);
		logger.info("Class: QuizMeController <-> Method: proceedToQuestion() End");
		return "addQuestions";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	public String addQuestion(@ModelAttribute("questionForm") QuestionModel questionModel, Model model, HttpServletRequest request) {
		logger.info("Class: QuizMeController <-> Method: addQuestion() Start");
		session = request.getSession();
		QuizModel quizModel = (QuizModel) session.getAttribute("quizForm");
		if(quizModel != null) {
			if(quizModel.getQuestionsList() != null && !quizModel.getQuestionsList().isEmpty()) {
				quizModel.getQuestionsList().add(questionModel);
			} else {
				List<QuestionModel> questionsList = new ArrayList<QuestionModel>();
				questionsList.add(questionModel);
				quizModel.setQuestionsList(questionsList);
			}
		} else {
			quizModel = new QuizModel();
		}
		session.setAttribute("quizForm", quizModel);
		model.addAttribute("quizForm", quizModel);
		logger.info("Class: QuizMeController <-> Method: addQuestion() End");
		return "redirect:/createQuiz";
	}
}
