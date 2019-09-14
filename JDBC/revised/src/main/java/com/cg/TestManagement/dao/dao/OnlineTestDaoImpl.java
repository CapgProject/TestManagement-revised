package com.cg.TestManagement.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.Exception.UserException;
import com.cg.TestManagement.dto.OnlineTest;
import com.cg.TestManagement.dto.User;
import com.cg.TestManagement.util.DbUtil;

public class OnlineTestDaoImpl implements OnlineTestDao {

	private static Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private static Logger myLogger;

	static {

		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir") + "/src/main/resources/";
		System.out.println("Current working directory is " + userDir);
		PropertyConfigurator.configure(userDir + "log4j.properties");
		myLogger = Logger.getLogger("OnlineTestDaoImpl.class");
	}

	static {
		try {
			connection = DbUtil.getConnection();
			myLogger.info("Connection obtained");
		} catch (UserException e) {
			// TODO Auto-generated catch block
			myLogger.error("Connection not obtained at Employee Dao");
			System.out.println("Connection not obtained at Employee Dao" + e);
		}
	}

	public OnlineTest saveTest(OnlineTest onlineTest) throws UserException{
		// TODO Auto-generated method stub
		String sql = "insert into test(test_name, test_duration, test_total_marks,test_marks_scored, test_start_date_time, test_end_date_time) values(?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, onlineTest.getTestName());
			preparedStatement.setTime(2,Time.valueOf(onlineTest.getTestDuration()));
			preparedStatement.setDouble(3, onlineTest.getTestTotalMarks());
			preparedStatement.setDouble(4, onlineTest.getTestMarksScored());
			preparedStatement.setTimestamp(5, Timestamp.valueOf(onlineTest.getStartTime()));
			preparedStatement.setTimestamp(6, Timestamp.valueOf(onlineTest.getEndTime()));
			int result = preparedStatement.executeUpdate();
			if(result == 0) {
				return null;
			}
			BigInteger generatedId = BigInteger.valueOf(0L);
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedId = BigInteger.valueOf(resultSet.getLong(1));
				System.out.println("Auto generated id : " + generatedId);
			}
			onlineTest.setTestId(generatedId);;
			System.out.println("Added Test to the database with id as : " + generatedId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error at add Test Dao method: " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error at add Test Dao method: " + e);
				}
			}
		}
		return onlineTest;
	}

	public OnlineTest searchTest(BigInteger testId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	public OnlineTest removeTest(BigInteger testId) throws UserException {
		// TODO Auto-generated method stub
	}
		

	public Map<BigInteger, OnlineTest> showTests() throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	public Question saveQuestion(Question question) throws UserException {
		// TODO Auto-generated method stub
		OnlineTest onlineTest=new OnlineTest();
		String sql="insert into Question(question_title, question_option_a, question_option_b, question_option_c, question_option_d, question_chosen_answer, question_correct_answer, question_marks, question_marks_scored, test_id) values(?,?,?,?,?,?,?,?,?,?)";
		try {
			preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, question.getQuestionTitle());
			preparedStatement.setString(2, question.getQusetionOptions()[0]);
			preparedStatement.setString(3, question.getQusetionOptions()[1]);
			preparedStatement.setString(4, question.getQusetionOptions()[2]);
			preparedStatement.setString(5, question.getQusetionOptions()[3]);
			preparedStatement.setInt(6, question.getChosenAnswer());
			preparedStatement.setInt(7, question.getQuestionAnswer());
			preparedStatement.setDouble(8,  question.getQuestionMarks());
			preparedStatement.setDouble(9, question.getMarksScored());
			preparedStatement.setLong(10, onlineTest.getTestId().longValue());
			int result = preparedStatement.executeUpdate();
			if(result == 0) {
				return null;
			}
			BigInteger generatedId = BigInteger.valueOf(0L);
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedId = BigInteger.valueOf(resultSet.getLong(1));
				System.out.println("Auto generated id : " + generatedId);
			}
			
			question.setQuestionId(generatedId);
			System.out.println("Added Question to the database with id as : " + generatedId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error at add Question Dao method: " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error at add Question Dao method: " + e);
				}
			}
		}
		return question;
	}

			
			
		
	public Question searchQuestion(BigInteger questId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	public Question removeQuestion(BigInteger questId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<BigInteger, Question> showQuestions() throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	public User searchUser(Long userId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	public User saveUser(User user) throws UserException {
		// TODO Auto-generated method stub
		String sql="insert into User(user_name, user_password, user_is_admin, test_id) values(?,?,?,?)";
		OnlineTest onlineTest=new OnlineTest();
	    try {
	    	preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    	preparedStatement.setString(1, user.getUserName());
	    	preparedStatement.setString(2, user.getUserPassword());
	    	preparedStatement.setBoolean(3, user.getIsAdmin());
	    	preparedStatement.setLong(5, onlineTest.getTestId().longValue());
	    	int result=preparedStatement.executeUpdate();
	    	if(result==0)
	    	{
	    		return null;
	    	}
	    	
	    	BigInteger generatedId = BigInteger.valueOf(0L);
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedId = BigInteger.valueOf(resultSet.getLong(1));
				System.out.println("Auto generated id : " + generatedId);
			}
			
			user.setUserId(generatedId.longValue());
			System.out.println("Added User to the database with id as : " + generatedId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error at add User Dao method: " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error at add User Dao method: " + e);
				}
			}
		}
		return user;
	}
	    
		


	public Map<Long, User> showUsers() throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

}
