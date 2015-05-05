package edu.sjsu.quizme.queries;

/**
 * @author Varun
 *
 */
public class QuizMeQueries {
	
	public static final String GET_CATEGORY_QUERY = "SELECT CATEGORY_ID, CATEGORY FROM CATEGORY";
	
	public static final String GET_DIFFICULTY_LEVEL_QUERY = "SELECT DIFFICULTY_ID, DIFFICULTY FROM DIFFICULTY_LEVEL";
	
	public static final String CREATE_QUIZ_QUERY = "INSERT INTO QUIZ_MASTER(QUIZ_NAME, CATEGORY_ID, DIFFICULTY_ID) VALUES (?, ?, ?)"; 
	
	public static final String GET_MAX_QUIZ_ID_QUERY = "SELECT MAX(QUIZ_ID) AS MAX_QUIZ_ID FROM QUIZ_MASTER";
	
	public static final String CREATE_QUESTIONS_FOR_QUIZ_QUERY = "INSERT INTO QUESTIONS_MASTER(QUIZ_ID, QUESTION, OPTION_A, OPTION_B, OPTION_C, OPTION_D, CORRECT_ANSWER) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
}
