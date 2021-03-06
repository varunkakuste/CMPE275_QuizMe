/**
 * 
 */
package edu.sjsu.quizme;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sjsu.quizme.models.EmailModel;
import edu.sjsu.quizme.models.UserModel;

/**
 * @author Varun
 *
 */
@Controller
public class EmailController {
	@Autowired
	private JavaMailSender mailSender;
	private HttpSession session = null;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public String home(Model model, @RequestParam HashMap<String, String> reqParams, HttpServletRequest request) {
		UserModel user = null;
		String selectedQuizName = null;
		session = request.getSession();
		user = (UserModel) session.getAttribute("userDetails");
		if(reqParams != null && !reqParams.isEmpty()) {
			selectedQuizName = reqParams.get("selectedQuizName");
		}
		EmailModel email = new EmailModel();
		email.setSubject(selectedQuizName + " Quiz refered to you by your friend");
		email.setMailBody("Hello User, "+user.getUserName() + " wants to share our quiz with you. Click the link to take the quiz: http://quizme.elasticbeanstalk.com/login");
		model.addAttribute("emailForm", email);
		return "emailForm";
	}
	
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public String doSendEmail(Model model, HttpServletRequest request, @ModelAttribute("emailForm") @Valid EmailModel emailForm, BindingResult bindingResult) {
		try {
			if (!bindingResult.hasErrors()) {
				// takes input from e-mail form
				String recipientAddress = emailForm.getEmailTo(); //request.getParameter("recipient");
				String subject = emailForm.getSubject(); //request.getParameter("subject");
				String mailBody = emailForm.getMailBody(); //request.getParameter("message");
				
				// creates a simple e-mail object
				SimpleMailMessage email = new SimpleMailMessage();
				email.setTo(recipientAddress);
				email.setSubject(subject);
				email.setText(mailBody);
				
				// sends the e-mail
				mailSender.send(email);
				model.addAttribute("mailSentInfo", "email sent");
				model.addAttribute("emailForm", new EmailModel());
			} else {
				model.addAttribute("emailEmptyError", "email empty");
			}
		} catch(Exception expt) {
			model.addAttribute("mailSendError", "email sent error");
		}
		
		// forwards to the view named "Result"
		return "emailForm";
	}

}
