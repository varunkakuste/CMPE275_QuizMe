/**
 * 
 */
package edu.sjsu.quizme.service.layer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.quizme.dao.layer.IQuizMeDao;

/**
 * @author Varun
 *
 */
@Component
public class QuizMeServiceImpl implements IQuizMeService {
	
	/**
	 * Logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(QuizMeServiceImpl.class);
	
	/**
	 * quizMeDao instance
	 */
	@Autowired
	private IQuizMeDao quizMeDao;
	
	/**
	 * Default Constructor
	 */
	public QuizMeServiceImpl(){}

	/**
	 * get home
	 */
	@Override
	public String getHome() {
		logger.info("Class: QuizMeServiceImpl <-> Method: getHome() Start");
		String result = quizMeDao.getHome();
		logger.info("Class: QuizMeServiceImpl <-> Method: getHome() End");
		return result;
	}
}
