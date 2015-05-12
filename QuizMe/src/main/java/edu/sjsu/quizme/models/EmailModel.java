/**
 * 
 */
package edu.sjsu.quizme.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.NotNull;

/**
 * @author Varun
 *
 */
public class EmailModel {

	@NotEmpty @NotNull @Email
	private String emailTo;
	private String subject;
	private String mailBody;
	private String link;
	private String selectedQuizName;
	
	/**
	 * @return the emailTo
	 */
	public String getEmailTo() {
		return emailTo;
	}
	/**
	 * @param emailTo the emailTo to set
	 */
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the mailBody
	 */
	public String getMailBody() {
		return mailBody;
	}
	/**
	 * @param mailBody the mailBody to set
	 */
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the selectedQuizName
	 */
	public String getSelectedQuizName() {
		return selectedQuizName;
	}
	/**
	 * @param selectedQuizName the selectedQuizName to set
	 */
	public void setSelectedQuizName(String selectedQuizName) {
		this.selectedQuizName = selectedQuizName;
	}
	
}
