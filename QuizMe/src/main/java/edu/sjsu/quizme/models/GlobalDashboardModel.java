/**
 * 
 */
package edu.sjsu.quizme.models;

/**
 * @author Varun
 *
 */
public class GlobalDashboardModel {
	
	private String userName;
	private String quizName;
	private int quizCount;
	private int maxScore;
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
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
	 * @return the quizCount
	 */
	public int getQuizCount() {
		return quizCount;
	}
	/**
	 * @param quizCount the quizCount to set
	 */
	public void setQuizCount(int quizCount) {
		this.quizCount = quizCount;
	}
	/**
	 * @return the maxScore
	 */
	public int getMaxScore() {
		return maxScore;
	}
	/**
	 * @param maxScore the maxScore to set
	 */
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	
}
