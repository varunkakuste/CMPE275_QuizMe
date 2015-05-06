/**
 * 
 */
package edu.sjsu.quizme.dao.layer;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.quizme.models.CategoryModel;
import edu.sjsu.quizme.models.DifficultyLevelModel;
import edu.sjsu.quizme.models.LoginModel;
import edu.sjsu.quizme.models.QuestionModel;
import edu.sjsu.quizme.models.QuizModel;
import edu.sjsu.quizme.models.UserModel;

/**
 * @author Varun
 *
 */
public interface IQuizMeDao {
	public String getHome();
	public List<CategoryModel> getCategories() throws Exception;
	public List<DifficultyLevelModel> getDifficultyLevels() throws Exception;
	public int getMaxQuizId() throws Exception;
	public void createQuiz(QuizModel quiz) throws Exception;
	public void createQuestions(List<QuestionModel> questionsList) throws Exception;
	public ArrayList<String> getQuiz(QuizModel quiz, int userId) throws Exception;
	public boolean signUp(UserModel user) throws Exception;
	public UserModel getUserDetails(LoginModel login) throws Exception;
	public boolean updateUserDetails(UserModel user) throws Exception;
}
