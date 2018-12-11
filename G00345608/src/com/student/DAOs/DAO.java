package com.student.DAOs;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.context.FacesContext;

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
				Student student = new Student();
				student.setStudentId(resultSetObj.getInt("sid"));
				student.setCourseId(resultSetObj.getString("cID"));
				student.setName(resultSetObj.getString("name"));
				student.setAddress(resultSetObj.getString("address"));
				StudentList.add(student); 
			}
			
			System.out.println("Total Records Fetched: " +StudentList.size());
			connObj.close();
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
		return StudentList;
		
	}
	
	public static String saveStudentDetailsInDB(Student newStudentObj) {
		int saveResult = 0;
		String navigationResult ="";
		try {
			pstmt = (PreparedStatement) getConnection().prepareStatement("insert into student(sid,cId,name,address) values(?,?,?,?)");
			pstmt.setInt(1,  newStudentObj.getStudentId());
			pstmt.setString(2, newStudentObj.getCourseId());
			pstmt.setString(3, newStudentObj.getName());
			pstmt.setString(4, newStudentObj.getAddress());
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
		if(saveResult != 0) {
			navigationResult ="list_students.xhtml?faces-redirect=true";
		} else {
			navigationResult = "createStudent.xhtml?faces-redirect=true";
		}
		return navigationResult;
	}
	
	 public static String editStudentRecordInDB(int studentId) {
		 Student editRecord = null;
		 System.out.println("editStudentRecordInDB() : Student Id: " + studentId);
		 Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		 
		 try {
			 
		 }
		 return null;
	 }
	 
	  public static String updateStudentDetailsInDB(Student updateStudentObj) {
		  return null;
	  }

	  
	  public static String deleteStudentRecordInDB(int studentId){
		  return null;
	  }



	
	
	
	
	
}
