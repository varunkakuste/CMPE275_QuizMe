/**
 * 
 */
package edu.sjsu.quizme.dao.layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import edu.sjsu.quizme.models.CategoryModel;
import edu.sjsu.quizme.models.DifficultyLevelModel;
import edu.sjsu.quizme.models.QuestionModel;
import edu.sjsu.quizme.models.QuizModel;
import edu.sjsu.quizme.queries.QuizMeQueries;

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

	/**
	 * Method to get all the categories from Database
	 * @throws Exception 
	 */
	@Override
	public List<CategoryModel> getCategories() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		CategoryModel categoryModel = null;
		List<CategoryModel> categoryList = new ArrayList<CategoryModel>();
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(QuizMeQueries.GET_CATEGORY_QUERY);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				categoryModel = new CategoryModel();
				categoryModel.setCategoryId(resultSet.getInt("CATEGORY_ID"));
				categoryModel.setCategory(resultSet.getString("CATEGORY"));
				categoryList.add(categoryModel);
			}
		} catch (SQLException sql) {
			throw new Exception(sql);
		} catch (Exception exp){
			throw new Exception(exp);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sql) {
					throw new Exception(sql);
				}
			}
		}
		return categoryList;
	}

	/**
	 * Method to get all the difficulty levels from Database
	 */
	@Override
	public List<DifficultyLevelModel> getDifficultyLevels() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		DifficultyLevelModel difficultyLevelModel = null;
		List<DifficultyLevelModel> difficultyLevelList = new ArrayList<DifficultyLevelModel>();
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(QuizMeQueries.GET_DIFFICULTY_LEVEL_QUERY);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				difficultyLevelModel = new DifficultyLevelModel();
				difficultyLevelModel.setDifficultyLevelId(resultSet.getInt("DIFFICULTY_ID"));
				difficultyLevelModel.setDifficultyLevel(resultSet.getString("DIFFICULTY"));
				difficultyLevelList.add(difficultyLevelModel);
			}
		} catch (SQLException sql) {
			throw new Exception(sql);
		} catch (Exception exp){
			throw new Exception(exp);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sql) {
					throw new Exception(sql);
				}
			}
		}
		return difficultyLevelList;
	}

	/**
	 * Method to get max quiz id from Database
	 * @throws Exception 
	 */
	@Override
	public int getMaxQuizId() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int maxId = 0;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(QuizMeQueries.GET_MAX_QUIZ_ID_QUERY);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				maxId = resultSet.getInt("MAX_QUIZ_ID");
			}
			preparedStatement.close();
		} catch (SQLException sql) {
			throw new Exception(sql);
		} catch (Exception exp){
			throw new Exception(exp);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sql) {
					throw new Exception(sql);
				}
			}
		}
		return maxId;
	}

	/**
	 * Method to create a new quiz into database
	 * @throws Exception 
	 */
	@Override
	public void createQuiz(QuizModel quiz) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			if(quiz != null) {
				connection = dataSource.getConnection();
				preparedStatement = connection.prepareStatement(QuizMeQueries.CREATE_QUIZ_QUERY);
				preparedStatement.setString(1, quiz.getQuizName());
				preparedStatement.setInt(2, quiz.getCategory());
				preparedStatement.setInt(3, quiz.getDifficultyLevel());
				
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
		} catch (SQLException sql) {
			throw new Exception(sql);
		} catch (Exception exp){
			throw new Exception(exp);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sql) {
					throw new Exception(sql);
				}
			}
		}
	}

	/**
	 * Method to insert questions for the quiz in Database
	 * @throws Exception 
	 */
	@Override
	public void createQuestions(List<QuestionModel> questionsList) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(QuizMeQueries.CREATE_QUESTIONS_FOR_QUIZ_QUERY);
			int maxQuizId = getMaxQuizId();
			for(QuestionModel question : questionsList) {
				preparedStatement.setInt(1, maxQuizId);
				preparedStatement.setString(2, question.getQuestion());
				preparedStatement.setString(3, question.getOptionA());
				preparedStatement.setString(4, question.getOptionB());
				preparedStatement.setString(5, question.getOptionC());
				preparedStatement.setString(6, question.getOptionD());
				preparedStatement.setString(7, question.getCorrectAnswer());
				preparedStatement.addBatch();
			}
			
			preparedStatement.executeBatch();
			preparedStatement.close();
		} catch (SQLException sql) {
			throw new Exception(sql);
		} catch (Exception exp){
			throw new Exception(exp);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sql) {
					throw new Exception(sql);
				}
			}
		}
	}
	
	/**
	 * Method to get the quizzes in Database
	 * @throws Exception 
	 */
	public ArrayList<String> getQuiz(QuizModel quiz, int userId) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> quizList=new ArrayList<String>();
		
		try {
			connection = (Connection) dataSource.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(QuizMeQueries.GET_QUIZ_LIST_QUERY);
			preparedStatement.setString(1, quiz.getQuizName()+"%");
			preparedStatement.setInt(2, quiz.getCategory());
			preparedStatement.setInt(3, quiz.getDifficultyLevel());
			preparedStatement.setInt(4, userId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				quizList.add(resultSet.getString("QUIZ_NAME"));
			}
		} catch (SQLException sql) {
			throw new Exception(sql);
		} catch (Exception exp){
			throw new Exception(exp);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sql) {
					throw new Exception(sql);
				}
			}
		}
		return quizList;
	}

//	/**
//	 * Method to SignUp
//	 */
//	@Override
//	public boolean signUp(UserModel user) throws Exception {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		boolean isUserSignedUp = false;
//		try {
//			connection = dataSource.getConnection();
//			preparedStatement = connection.prepareStatement(QuizMeQueries.SIGNUP_USER_QUERY);
//			preparedStatement.setString(1, user.getUserName());
//			preparedStatement.setString(2, user.getEmail());
//			preparedStatement.setString(3, user.getPassword());
//			preparedStatement.setString(4, user.getLastName());
//			preparedStatement.setString(5, user.getFirstName());
//			
//			preparedStatement.executeUpdate();
//			preparedStatement.close();
//			isUserSignedUp = true;
//		} catch (SQLException sql) {
//			throw new Exception(sql);
//		} catch (Exception exp){
//			throw new Exception(exp);
//		} finally {
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException sql) {
//					throw new Exception(sql);
//				}
//			}
//		}
//		return isUserSignedUp;
//	}
//	
//	/**
//	 * Method to get user details
//	 */
//	@Override
//	public UserModel getUserDetails(LoginModel login) throws Exception {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		UserModel user = null;		
//		try {
//			connection = (Connection) dataSource.getConnection();
//			preparedStatement = (PreparedStatement) connection.prepareStatement(QuizMeQueries.GET_USER_DETAILS);
//			preparedStatement.setString(1, login.getUserName());
//			preparedStatement.setString(2, login.getPassword());
//			resultSet = preparedStatement.executeQuery();
//			if(resultSet.next()) {
//				user = new UserModel();
//				user.setUserId(resultSet.getInt("USER_ID"));
//				user.setUserName(resultSet.getString("USERNAME"));
//				user.setEmail(resultSet.getString("EMAIL"));
//				user.setPassword(resultSet.getString("PASSWORD"));
//				user.setLastName(resultSet.getString("LASTNAME"));
//				user.setFirstName(resultSet.getString("FIRSTNAME"));
//			}
//		} catch (SQLException sql) {
//			throw new Exception(sql);
//		} catch (Exception exp){
//			throw new Exception(exp);
//		} finally {
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException sql) {
//					throw new Exception(sql);
//				}
//			}
//		}
//		return user;
//	}
//	
//	/**
//	 * Method to update user details
//	 */
//	@Override
//	public boolean updateUserDetails(UserModel user) throws Exception {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		boolean isUserUpdated = false;
//		try {
//			connection = dataSource.getConnection();
//			preparedStatement = connection.prepareStatement(QuizMeQueries.UPDATE_USER_DETAILS);
//			preparedStatement.setString(1, user.getUserName());
//			preparedStatement.setString(2, user.getEmail());
//			preparedStatement.setString(3, user.getPassword());
//			preparedStatement.setString(4, user.getLastName());
//			preparedStatement.setString(5, user.getFirstName());
//			preparedStatement.setInt(6, user.getUserId());
//			
//			preparedStatement.executeUpdate();
//			preparedStatement.close();
//			isUserUpdated = true;
//		} catch (SQLException sql) {
//			throw new Exception(sql);
//		} catch (Exception exp){
//			throw new Exception(exp);
//		} finally {
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException sql) {
//					throw new Exception(sql);
//				}
//			}
//		}
//		return isUserUpdated;
//	}
	
	@Override
	public ArrayList<QuizModel> getTakenQuiz(int userId){
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<QuizModel> quizList=new ArrayList<QuizModel>();
		
		System.out.println("in getTaken imp");
		
			try {
				connection = (Connection) dataSource.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				preparedStatement = (PreparedStatement) connection.prepareStatement(QuizMeQueries.GET_TAKEN);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				preparedStatement.setInt(1, userId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				resultSet = preparedStatement.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while(resultSet.next()) {
					int quizid=resultSet.getInt("QUIZ_ID");
					String quizname=resultSet.getString("QUIZ_NAME");
					int score=resultSet.getInt("SCORE");
					String comment1=resultSet.getString("COMMENTS");
					
					QuizModel temp= new QuizModel();
					temp.setQuiz_id(quizid);
					temp.setQuizName(quizname);
					temp.setScore(score);
					temp.setComment(comment1);
					
					System.out.println(temp.getQuiz_id());
					System.out.println(temp.getQuizName());
					System.out.println(temp.getScore());
					
					quizList.add(temp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			finally{	
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			}
		System.out.println(quizList);
		return quizList;
	}
}
