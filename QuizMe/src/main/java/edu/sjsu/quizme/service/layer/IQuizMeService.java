/**
 * 
 */
package edu.sjsu.quizme.service.layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.sjsu.quizme.models.CategoryModel;
import edu.sjsu.quizme.models.DifficultyLevelModel;
import edu.sjsu.quizme.models.GlobalDashboardModel;
import edu.sjsu.quizme.models.QuestionModel;
import edu.sjsu.quizme.models.QuizModel;

/**
 * @author Varun
 *
 */
public interface IQuizMeService {
	public String getHome();
	public List<CategoryModel> getCategories() throws Exception;
	public List<DifficultyLevelModel> getDifficultyLevels() throws Exception;
	public void createQuiz(QuizModel quiz) throws Exception;
//	public ArrayList<String> getQuiz(QuizModel quiz,int  userId) throws Exception;
	public HashMap<Integer, String> getQuiz(QuizModel quiz, int userId) throws Exception;
	public ArrayList<QuizModel> getTakenQuiz(int userId) throws Exception;
	public ArrayList<QuestionModel> getQuestionsForQuiz(int quizId) throws Exception;
	public void insertQuizTaken(int quizId, int userId, int score, String comment) throws Exception;
}
