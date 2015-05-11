/**
 * 
 */
package edu.sjsu.quizme.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Varun
 *
 */
public class QuestionModel {
	
	/**
	 * question
	 */
	@NotEmpty @NotNull @Size(min=2, max=100)
	private String question;
	
	/**
	 * optionA
	 */
	private String optionA;
	
	/**
	 * optionB
	 */
	private String optionB;
	
	/**
	 * optionC
	 */
	private String optionC;
	
	/**
	 * optionD
	 */
	private String optionD;
	
	/**
	 * correctAnswer
	 */
	@NotEmpty @NotNull @Size(min=1, max=1)
	private String correctAnswer;
	
	private int quizId;
	
	private String usersAnswer;
	
	/**
	 * questionId
	 */
	private int questionId;
	
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the optionA
	 */
	public String getOptionA() {
		return optionA;
	}
	/**
	 * @param optionA the optionA to set
	 */
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	/**
	 * @return the optionB
	 */
	public String getOptionB() {
		return optionB;
	}
	/**
	 * @param optionB the optionB to set
	 */
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	/**
	 * @return the optionC
	 */
	public String getOptionC() {
		return optionC;
	}
	/**
	 * @param optionC the optionC to set
	 */
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	/**
	 * @return the optionD
	 */
	public String getOptionD() {
		return optionD;
	}
	/**
	 * @param optionD the optionD to set
	 */
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	/**
	 * @return the correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	/**
	 * @return the questionId
	 */
	public int getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return the quizId
	 */
	public int getQuizId() {
		return quizId;
	}
	/**
	 * @param quizId the quizId to set
	 */
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	/**
	 * @return the usersAnswer
	 */
	public String getUsersAnswer() {
		return usersAnswer;
	}
	/**
	 * @param usersAnswer the usersAnswer to set
	 */
	public void setUsersAnswer(String usersAnswer) {
		this.usersAnswer = usersAnswer;
	}
	
}
