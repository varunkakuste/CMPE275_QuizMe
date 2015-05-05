/**
 * 
 */
package edu.sjsu.quizme.service.layer;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.quizme.models.CategoryModel;
import edu.sjsu.quizme.models.DifficultyLevelModel;
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
}
