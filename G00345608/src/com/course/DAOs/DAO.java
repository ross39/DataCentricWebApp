package com.course.DAOs;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.courses.Controllers.Course;
import com.mysql.jdbc.*;

public class DAO {

	public static Statement stmtObj;
	public static Connection connObj;
	public static ResultSet resultSetObj;
	public static PreparedStatement pstmt;

	public static Connection getConnection(){  
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			String db_url ="jdbc:mysql://localhost:3306/students",
					db_userName = "root",
					db_password = "";
			connObj = (Connection) DriverManager.getConnection(db_url,db_userName,db_password);  
		} catch(Exception sqlException) {  
			sqlException.printStackTrace();
		}  
		return connObj;
	}
	
	
	public static ArrayList getCourseListFromDB(){
		ArrayList courseList = new ArrayList();
		try{
			stmtObj = (Statement) getConnection().createStatement();
			resultSetObj = stmtObj.executeQuery("select * from course ");
			while(resultSetObj.next()){
				DAO daoObj = new DAO();
				daoObj.setId(resultSetObj.getInt("cID"));
				daoObj.setName(resultSetObj.getString("cName"));
				daoObj.setDuration(resultSetObj.getInt("duration"));
				courseList.add(daoObj); 
				
			}
		}
		
	}

	public static String saveCourseDetailsInDB(Course newCourseObj) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String editCourseRecordInDB(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String updateCoursetDetailsInDB(Course updateCourseObJ) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//public static String saveCourseDetailsInDb(Course newCourseObj){
		
		
	//  }
}
