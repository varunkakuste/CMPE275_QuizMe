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
	 * categoryModel
	 */
	private List<CategoryModel> categoryModelList;
	
	/**
	 * difficultyLevelModelList
	 */
	private List<DifficultyLevelModel> difficultyLevelModelList;
	
	/**
	 * questionsList
	 */
	private List<QuestionModel> questionsList;
	
	/**
	 * quiz_id
	 */
	private int quiz_id;
	
	/**
	 * score
	 */
	private int score;
	
	/**
	 * comment
	 */
	private String comment;
	
	/**
	 * selectedQuizId
	 */
	private int selectedQuizId;

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
	 * @return the categoryModelList
	 */
	public List<CategoryModel> getCategoryModelList() {
		return categoryModelList;
	}

	/**
	 * @param categoryModelList the categoryModelList to set
	 */
	public void setCategoryModelList(List<CategoryModel> categoryModelList) {
		this.categoryModelList = categoryModelList;
	}

	/**
	 * @return the difficultyLevelModelList
	 */
	public List<DifficultyLevelModel> getDifficultyLevelModelList() {
		return difficultyLevelModelList;
	}

	/**
	 * @param difficultyLevelModelList the difficultyLevelModelList to set
	 */
	public void setDifficultyLevelModelList(
			List<DifficultyLevelModel> difficultyLevelModelList) {
		this.difficultyLevelModelList = difficultyLevelModelList;
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

	/**
	 * @return the quiz_id
	 */
	public int getQuiz_id() {
		return quiz_id;
	}

	/**
	 * @param quiz_id the quiz_id to set
	 */
	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the selectedQuizId
	 */
	public int getSelectedQuizId() {
		return selectedQuizId;
	}

	/**
	 * @param selectedQuizId the selectedQuizId to set
	 */
	public void setSelectedQuizId(int selectedQuizId) {
		this.selectedQuizId = selectedQuizId;
	}

}
