/**
 * 
 */
package edu.sjsu.quizme.service.layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.quizme.dao.layer.IQuizMeDao;
import edu.sjsu.quizme.models.CategoryModel;
import edu.sjsu.quizme.models.DifficultyLevelModel;
import edu.sjsu.quizme.models.QuestionModel;
import edu.sjsu.quizme.models.QuizModel;

/**
 * @author Varun
 *
 */
@Component
public class QuizMeServiceImpl implements IQuizMeService {
	
	/**
	 * Logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(QuizMeServiceImpl.class);
	
	/**
	 * quizMeDao instance
	 */
	@Autowired
	private IQuizMeDao quizMeDao;
	
	/**
	 * Default Constructor
	 */
	public QuizMeServiceImpl(){}

	/**
	 * get home
	 */
	@Override
	public String getHome() {
		logger.info("Class: QuizMeServiceImpl <-> Method: getHome() Start");
		String result = quizMeDao.getHome();
		logger.info("Class: QuizMeServiceImpl <-> Method: getHome() End");
		return result;
	}

	/**
	 * Method to get all the categories from Database
	 */
	@Override
	public List<CategoryModel> getCategories() throws Exception {
		return quizMeDao.getCategories();
	}

	/**
	 * Method to get all the difficulty levels from Database
	 */
	@Override
	public List<DifficultyLevelModel> getDifficultyLevels() throws Exception {
		return quizMeDao.getDifficultyLevels();
	}

	/**
	 * Method to create Quiz in Database
	 */
	@Override
	public void createQuiz(QuizModel quiz) throws Exception {
		quizMeDao.createQuiz(quiz);
		quizMeDao.createQuestions(quiz.getQuestionsList());
	}
	
//	/**
//	 * Method to search Quiz in Database
//	 */
//	@Override
//	public ArrayList<String> getQuiz(QuizModel quiz, int userId) throws Exception {
//		if(quiz != null){
//			return quizMeDao.getQuiz(quiz, userId);
//		}
//		return null;
//	}
	
	/**
	 * Method to search Quiz in Database
	 */
	@Override
	public HashMap<Integer, String> getQuiz(QuizModel quiz, int userId) throws Exception {
		if(quiz != null){
			return quizMeDao.getQuiz(quiz, userId);
		}
		return null;
	}

	/**
	 * Method to get user's dashboard Quiz in Database
	 */
	@Override
	public ArrayList<QuizModel> getTakenQuiz(int userId) throws Exception {
		return quizMeDao.getTakenQuiz(userId);
	}
	
	/**
	 * Method to get questions for a Quiz
	 */
	@Override
	public ArrayList<QuestionModel> getQuestionsForQuiz(int quizId) throws Exception {
		return quizMeDao.getQuestionsForQuiz(quizId);
	}
	
	@Override
	public void insertQuizTaken(int quizId, int userId, int score, String comment) throws Exception {
		quizMeDao.insertQuizTaken(quizId, userId, score, comment);		
	}
}
