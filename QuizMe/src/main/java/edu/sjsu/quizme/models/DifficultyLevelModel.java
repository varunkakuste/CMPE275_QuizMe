/**
 * 
 */
package edu.sjsu.quizme.models;

/**
 * @author Varun
 *
 */
public class DifficultyLevelModel {
	
	private int difficultyLevelId;
	private String difficultyLevel;
	
	/**
	 * @return the difficultyLevelId
	 */
	public int getDifficultyLevelId() {
		return difficultyLevelId;
	}
	/**
	 * @param difficultyLevelId the difficultyLevelId to set
	 */
	public void setDifficultyLevelId(int difficultyLevelId) {
		this.difficultyLevelId = difficultyLevelId;
	}
	/**
	 * @return the difficultyLevel
	 */
	public String getDifficultyLevel() {
		return difficultyLevel;
	}
	/**
	 * @param difficultyLevel the difficultyLevel to set
	 */
	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	
}
