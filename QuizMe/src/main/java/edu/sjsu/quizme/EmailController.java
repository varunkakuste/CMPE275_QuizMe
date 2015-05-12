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
		email.setMailBody("Hello User, "+user.getUserName() + " wants to share our quiz with you. Click the link to take the quiz: http://localhost:8080/quizme/login");
		model.addAttribute("emailForm", email);
		return "emailForm";
	}

}
