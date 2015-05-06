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
public class LoginModel {
	
	@NotEmpty @NotNull @Size(min=2, max=30)
	private String userName;
	@NotEmpty @NotNull @Size(min=6, max=30)
	private String password;
	
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
