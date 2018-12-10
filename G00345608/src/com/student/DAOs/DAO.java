package com.student.DAOs;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.student.Controllers.Student;
import com.student.DAOs.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DAO {

	
	public static Statement stmtObj;
	public static Connection connObj;
	public static ResultSet resultSetObj;
	public static PreparedStatement pstmt;

	
	//Make the connection
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
	
	public static ArrayList getStudentListFromDB(){
		ArrayList StudentList = new ArrayList();
		try{
			stmtObj = (Statement) getConnection().createStatement();
			resultSetObj = stmtObj.executeQuery("select * from student");
			while(resultSetObj.next()){
				DAO daoObj = new DAO();
				daoObj.setStudentId(resultSetObj.getInt("sid"));
				daoObj.setCourseId(resultSetObj.getString("cID"));
				daoObj.setName(resultSetObj.getInt("name"));
				daoObj.setAddress(resultSetObj.getString("address"));
				StudentList.add(daoObj); 
			}
		}
		
	}
	
	public static String saveStudentDetailsInDB(Student newStudentObj) {
		return null;
		
		
	}
	
	 public static String editStudentRecordInDB(int studentId) {
		 return null;
	 }
	 
	  public static String updateStudentDetailsInDB(Student updateStudentObj) {
		  return null;
	  }

	  
	  public static String deleteStudentRecordInDB(int studentId){
		  return null;
	  }



	
	
	
	
	
}
