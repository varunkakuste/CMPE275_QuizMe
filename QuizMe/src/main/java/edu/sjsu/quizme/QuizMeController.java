/**
 * 
 */
package edu.sjsu.quizme;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.sjsu.quizme.models.CategoryModel;
import edu.sjsu.quizme.models.DifficultyLevelModel;
import edu.sjsu.quizme.models.QuestionModel;
import edu.sjsu.quizme.models.QuizModel;
import edu.sjsu.quizme.models.UserModel;
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
	
	@Autowired
	private IQuizMeService quizMeService;
	private HttpSession session = null;
	private List<CategoryModel> categoryList = null;
	private List<DifficultyLevelModel> difficultyList = null;
	
//	/**
//	 * @param quizMeService
//	 */
//	@Autowired
//	public QuizMeController(IQuizMeService quizMeService) {
//		this.quizMeService = quizMeService;
//	}

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
	@RequestMapping(value = "/createNewQuiz", method = RequestMethod.GET)
	public String createQuiz(Model model, @ModelAttribute("noQuizNameError") String noQuizNameError, HttpServletRequest request) {
		logger.info("Class: QuizMeController <-> Method: createQuiz() Start");
		QuizModel quizModel = null;
		try {
			session = request.getSession();
			quizModel = (QuizModel) session.getAttribute("quizForm");
			if(quizModel == null) {
				categoryList = (List<CategoryModel>) session.getAttribute("categoryList");
				difficultyList = (List<DifficultyLevelModel>) session.getAttribute("difficultyList");
				
				quizModel = new QuizModel();
				quizModel.setCategoryModelList(categoryList);
				quizModel.setDifficultyLevelModelList(difficultyList);
				
				session.setAttribute("quizForm", quizModel);
			}
			
			if(quizModel != null && quizModel.getQuestionsList() != null && quizModel.getQuestionsList().size() >= 5) {
				model.addAttribute("enableCreateQuizButton", "enable Create Quiz Button");
			}
			
			model.addAttribute("quizForm", quizModel);
			model.addAttribute("noQuizNameError", noQuizNameError);
		} catch (Exception exception) {
			System.out.println("Some Exception...");
		}
		logger.info("Class: QuizMeController <-> Method: createQuiz() End");
		return "createQuiz";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/proceedToQuestion", method = RequestMethod.GET)
	public String proceedToQuestion(Model model, @ModelAttribute("quizForm") QuizModel quizModelAttribute, 
			@ModelAttribute("questionForm") QuestionModel questionModel, HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {
		logger.info("Class: QuizMeController <-> Method: proceedToQuestion() Start");
		String redirection = "addQuestions";
		session = request.getSession();
		QuizModel quizModel = (QuizModel) session.getAttribute("quizForm");
		if(quizModel != null && quizModelAttribute.getQuizName() != null && !"".equalsIgnoreCase(quizModelAttribute.getQuizName())) {
			if(quizModel.getCategory() == 0 && quizModel.getDifficultyLevel() == 0 
				&& (quizModel.getQuestionsList() == null || quizModel.getQuestionsList().isEmpty())) {
				quizModelAttribute.setCategoryModelList(categoryList);
				quizModelAttribute.setDifficultyLevelModelList(difficultyList);
				session.setAttribute("quizForm", quizModelAttribute);
			}
		} else {
			redirection = "redirect:/createNewQuiz";
			redirectAttributes.addFlashAttribute("noQuizNameError", "enter quiz name");
		}
		model.addAttribute("questionForm", questionModel);
		logger.info("Class: QuizMeController <-> Method: proceedToQuestion() End");
		return redirection;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	public String addQuestion(Model model, @ModelAttribute("questionForm") @Valid QuestionModel questionModel, BindingResult bindingResult, HttpServletRequest request) {
		logger.info("Class: QuizMeController <-> Method: addQuestion() Start");
		String redirection = "addQuestions";
		try {
			if (!bindingResult.hasErrors()) {
				session = request.getSession();
				QuizModel quizModel = (QuizModel) session.getAttribute("quizForm");
				
				if(chkIsNull(questionModel.getOptionA()) || chkIsNull(questionModel.getOptionB()) 
					|| chkIsNull(questionModel.getOptionC()) || chkIsNull(questionModel.getOptionD())) {
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
					redirection = "redirect:/createNewQuiz";
				} else {
					model.addAttribute("optionsError", "Please Give atleast one option");
				}
				session.setAttribute("quizForm", quizModel);
				model.addAttribute("quizForm", quizModel);
			}
		} catch (Exception exception) {
			model.addAttribute("addQuestionError", "Error adding question");
		}
		logger.info("Class: QuizMeController <-> Method: addQuestion() End");
		return redirection;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/createQuiz", method = RequestMethod.POST)
	public String createNewQuiz(Model model, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		logger.info("Class: QuizMeController <-> Method: addQuestion() Start");
		String redirection = "redirect:/getTaken";
		try {
			session = request.getSession();
			QuizModel quizModel = (QuizModel) session.getAttribute("quizForm");
			quizMeService.createQuiz(quizModel);
			redirectAttributes.addFlashAttribute("quizCreatedMessage", "User signedup successfully");
		} catch (Exception exception) {
			model.addAttribute("createQuizCatchError", "Error Logging in");
			redirection = "createQuiz";
		}
		logger.info("Class: QuizMeController <-> Method: addQuestion() End");
		return redirection;
	}
	
//	@RequestMapping(value = "/getQuiz", method = RequestMethod.GET)
//	public  String getQuiz(HttpServletRequest request, Model model){
//		QuizModel quizModel = null;
//		try {
//			session = request.getSession();
//			quizModel = (QuizModel) session.getAttribute("quizForm");
//			if(quizModel == null) {
////				categoryList = quizMeService.getCategories();
////				difficultyList = quizMeService.getDifficultyLevels();
//				categoryList = (List<CategoryModel>) session.getAttribute("categoryList");
//				difficultyList = (List<DifficultyLevelModel>) session.getAttribute("difficultyList");
//				
//				quizModel = new QuizModel();
//				quizModel.setCategoryModelList(categoryList);
//				quizModel.setDifficultyLevelModelList(difficultyList);
//				
//				session.setAttribute("quizForm", quizModel);
//			}
//			model.addAttribute("quizForm", quizModel);
//		} catch (Exception exception) {
//			System.out.println("Some Exception...");
//		}
//		return "quiz";
//	}
	
	@RequestMapping(value = "/getQuiz", method = RequestMethod.GET)
	public  String getQuiz(Model model, @ModelAttribute("quizMap") HashMap<Integer, String> quizMap, @ModelAttribute("getQuizCatchError") String error, HttpServletRequest request){
		QuizModel quizModel = null;
		String redirection = "quiz";
		try {
			session = request.getSession();
			quizModel = (QuizModel) session.getAttribute("quizForm");
			if(quizModel == null) {
				categoryList = (List<CategoryModel>) session.getAttribute("categoryList");
				difficultyList = (List<DifficultyLevelModel>) session.getAttribute("difficultyList");
				
				quizModel = new QuizModel();
				quizModel.setCategoryModelList(categoryList);
				quizModel.setDifficultyLevelModelList(difficultyList);
				
				session.setAttribute("quizForm", quizModel);
			}
			model.addAttribute("quizForm", quizModel);
			model.addAttribute("quizList", quizMap);
			model.addAttribute("getQuizCatchError", error);
		} catch (Exception exception) {
			model.addAttribute("getQuizCatchError", "Error Logging in");
			redirection = "quiz";
		}
		return redirection;
	}
	
//	@RequestMapping(value = "/getQuizList", method = RequestMethod.POST)
//	public  String getQuizList(HttpServletRequest request, Model model, @ModelAttribute("quizForm") QuizModel quizModelAttribute) {
//		int userId = 0;
//		QuizModel quiz = null; 
//		ArrayList<String> quizList = null;
//		try {
//			session = request.getSession();
//			UserModel user = (UserModel) session.getAttribute("userDetails");
//			if(user != null) {
//				userId = user.getUserId();
//			}
//			quiz = new QuizModel();
//			quiz.setCategory(quizModelAttribute.getCategory());
//			quiz.setDifficultyLevel(quizModelAttribute.getDifficultyLevel());
//			if(quizModelAttribute.getQuizName() == null || quizModelAttribute.getQuizName().isEmpty()) {
//				quiz.setQuizName("");
//			} else {
//				quiz.setQuizName(quizModelAttribute.getQuizName());
//			}
//			quizList = quizMeService.getQuiz(quiz, userId);
//			model.addAttribute("quizList", quizList);
//		} catch (Exception exception) {
//			System.out.println("Some Exception...");
//		}
//		return "quizList";
//	}
	
	/**
	 * Method to get searched Quizzes from Database
	 */
	@RequestMapping(value = "/getQuizList", method = RequestMethod.POST)
	public  String getQuizList(Model model, @ModelAttribute("quizForm") QuizModel quizModelAttribute, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		int userId = 0;
		QuizModel quiz = null; 
		HashMap<Integer, String> quizMap = null;
		String redirection = "redirect:/getQuiz";
		try {
			session = request.getSession();
			UserModel user = (UserModel) session.getAttribute("userDetails");
			if(user != null) {
				userId = user.getUserId();
			}
			quiz = new QuizModel();
			quiz.setCategory(quizModelAttribute.getCategory());
			quiz.setDifficultyLevel(quizModelAttribute.getDifficultyLevel());
			if(quizModelAttribute.getQuizName() == null || quizModelAttribute.getQuizName().isEmpty()) {
				quiz.setQuizName("");
			} else {
				quiz.setQuizName(quizModelAttribute.getQuizName());
			}
			quizMap = quizMeService.getQuiz(quiz, userId);
			redirectAttributes.addFlashAttribute("quizMap", quizMap);
			redirectAttributes.addFlashAttribute("quizForm", quizModelAttribute);
//			model.addAttribute("quizMap", quizMap);
//			model.addAttribute("quizForm", quizModelAttribute);
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("getQuizCatchError", "Error Logging in");
		}
		return redirection;
	}
	
	/**
	 * Method to get user's dashboard Quiz in Database
	 */
	@RequestMapping(value = "/getTaken", method = RequestMethod.GET)
	public  String getTaken(HttpServletRequest request, Model model, @ModelAttribute("quizCreatedMessage") String infoMessage) {
		int userId = 0;
		ArrayList<QuizModel> statTaken = null;
		try {
			System.out.println("before session get");
			
			session = request.getSession();
			System.out.println("before session user details");
			
			UserModel user = (UserModel) session.getAttribute("userDetails");
			if(user != null) {
				userId = user.getUserId();
			}
			System.out.println("before calling get taken");
			statTaken = quizMeService.getTakenQuiz(userId);
			model.addAttribute("quizTaken", statTaken);
			model.addAttribute("quizCreatedMessage", infoMessage);
		} catch (Exception exception) {
			System.out.println("Some Exception...");
		}
		return "takenList";
	}
	
	/**
	 * Method to get questions for a Quiz
	 */
	@RequestMapping(value = "/takeQuiz", method = RequestMethod.POST)
	public String takeQuiz(Model model, @ModelAttribute("quizForm") QuizModel quizModelAttribute, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
//		int userId = 0;
//		QuizModel quiz = null; 
		ArrayList<QuestionModel> questionsList = null;
		String redirection = "takeQuiz";
		try {
			questionsList = quizMeService.getQuestionsForQuiz(quizModelAttribute.getSelectedQuizId());
			model.addAttribute("questionsList", questionsList);
			session = request.getSession();
			session.setAttribute("questionsList", questionsList);
		} catch (Exception exception) {
//			redirectAttributes.addFlashAttribute("getQuizCatchError", "Error Logging in");
		}
		return redirection;
	}
	
	/**
	 * Method is to check if the String is empty or NULL
	 * 
	 * @param str
	 * @return boolean
	 */
	public boolean chkIsNull(String str) {
		boolean result = false;
		if (null != str && !"".equalsIgnoreCase(str)) {
			result = true;
		}
		return result;
	}
}
