/**
 * 
 */
package edu.sjsu.quizme.dao.layer;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Varun
 *
 */
@Component
public class QuizMeDaoImpl implements IQuizMeDao {
	
	/**
	 * Logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(QuizMeDaoImpl.class);
	
	/**
	 * dataSource
	 */
	private DataSource dataSource;
	
	/**
	 * Default Constructor
	 */
	public QuizMeDaoImpl() {}

	/**
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * get home
	 */
	@Override
	public String getHome() {
		logger.info("Class: QuizMeDaoImpl <-> Method: getHome() Start");
		String result = "Data from DAO Layer";
		logger.info("Class: QuizMeDaoImpl <-> Method: getHome() End");
		return result;
	}
	
}
