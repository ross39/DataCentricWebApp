package com.course.DAOs;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.courses.Controllers.Course;
import com.mysql.jdbc.*;

public class DAO {
	
	private DataSource mysqlDS;
	public static Statement stmtObj;
	public static Connection connObj;
	public static ResultSet resultSetObj;
	public static PreparedStatement pstmt;

	public static Connection getConnection(){  
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			String db_url ="jdbc:mysql://localhost:3306/studentdb",
					db_userName = "root",
					db_password = "";
			connObj = (Connection) DriverManager.getConnection(db_url,db_userName,db_password);  
		} catch(Exception sqlException) {  
			sqlException.printStackTrace();
		}  
		return connObj;
	}
	
	/*public DAO() throws Exception{
		InitialContext context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/studentdb";
		mysqlDS = (DataSource) context.lookup(jndiName);
		connObj = (Connection)mysqlDS.getConnection();
		
	}*/
	
	public static ArrayList getCourseListFromDB(){
		ArrayList courseList = new ArrayList();
		try{
			stmtObj = (Statement) getConnection().createStatement();
			resultSetObj = stmtObj.executeQuery("select * from course ");
			while(resultSetObj.next()){
				Course course = new Course();
				course.setId(resultSetObj.getInt("cID"));
				course.setName(resultSetObj.getString("cName"));
				course.setDuration(resultSetObj.getInt("duration"));
				courseList.add(course); 
			}
			System.out.println("Total Records Fetched: " + courseList.size());
			connObj.close();
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
		return courseList;
	}

	public static String saveCourseDetailsInDB(Course newCourseObj) {
		// TODO Auto-generated method stub
		int saveResult = 0;
		String navResult = "";
		try {
			pstmt = (PreparedStatement) getConnection().prepareStatement("insert into course(cId, cName, duration) values(?,?,?)");
			pstmt.setInt(1, newCourseObj.getId());
			pstmt.setString(2,  newCourseObj.getName());
			pstmt.setInt(3, newCourseObj.getDuration());
			saveResult = pstmt.executeUpdate();
			connObj.close();
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
		if(saveResult != 0) {
			navResult = "list_courses.xhtml?faces-redirect=true";
		} else {
			navResult = "create_course.xhtml?faces-redirect=true";
		}
		return navResult;
	}

	public static String editCourseRecordInDB(int courseId) {
		// TODO Auto-generated method stub
		Course editRecord = null;
		System.out.println("editStudentRecordInDb(): Student Id: " + courseId);
		
		/*Set the course details*/
		Map<String, Object> sessionMapObject = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		try {
			stmtObj = (Statement) getConnection().createStatement();
			resultSetObj = stmtObj.executeQuery("select * from course where cId = " +courseId);
			if(resultSetObj != null) {
				resultSetObj.next();
				editRecord = new Course();
				editRecord.setId(resultSetObj.getInt("cId"));
				editRecord.setName(resultSetObj.getString("cName"));
				editRecord.setDuration(resultSetObj.getInt("duration"));
			}
			sessionMapObject.put("editRecordObj", editRecord);
			connObj.close();
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
		return "/editCourse.xhtml?faces-redirect=true";
	}

	public static String updateCoursetDetailsInDB(Course updateCourseObJ) {
		// TODO Auto-generated method stub
		try {
			pstmt = (PreparedStatement) getConnection().prepareStatement("update course set cId=?, cName=?, duration=?");
			pstmt.setInt(1,  updateCourseObJ.getId());
			pstmt.setString(2, updateCourseObJ.getName());
			pstmt.setInt(3, updateCourseObJ.getDuration());
			pstmt.executeUpdate();
			connObj.close();
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
		return "list_course.xhtml?faces-redirect=true";
	}
	
	
	
	public static String deleteCourseDetailsInDb(int courseId){
		System.out.println("deleteStudentRecordInDb() : student Id: " + courseId);
		try {
			pstmt = (PreparedStatement) getConnection().prepareStatement("delete from course where cId = " + courseId);
			pstmt.executeUpdate();
			connObj.close();
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
		return "/courseList.xhtml?faces-redirect=true";
		
	 }
}
