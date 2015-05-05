/**
 * 
 */
package edu.sjsu.quizme.models;

import java.util.List;

/**
 * @author Varun
 *
 */
public class QuizModel {
	
	/**
	 * quizName
	 */
	private String quizName;
	
	/**
	 * category
	 */
	private int category;
	
	/**
	 * difficultyLevel
	 */
	private int difficultyLevel;
	
	/**
	 * questionsList
	 */
	private List<QuestionModel> questionsList;

	/**
	 * @return the quizName
	 */
	public String getQuizName() {
		return quizName;
	}

	/**
	 * @param quizName the quizName to set
	 */
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	/**
	 * @return the category
	 */
	public int getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(int category) {
		this.category = category;
	}

	/**
	 * @return the difficultyLevel
	 */
	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	/**
	 * @param difficultyLevel the difficultyLevel to set
	 */
	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	/**
	 * @return the questionsList
	 */
	public List<QuestionModel> getQuestionsList() {
		return questionsList;
	}

	/**
	 * @param questionsList the questionsList to set
	 */
	public void setQuestionsList(List<QuestionModel> questionsList) {
		this.questionsList = questionsList;
	}
	
}
