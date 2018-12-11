package com.student.Controllers;

import java.util.ArrayList;

import com.student.DAOs.*;

public class Student {
	private int studentId;
	private String courseId;
	private String name;
	private String address;
	
	public ArrayList fromStudentDB;
	
	public int getStudentId(){
		return studentId;
	}
	
	public void setStudentId(int id){
		this.studentId = id;
	}
	
	public String getCourseId(){
		return courseId;
	}
	
	public void setCourseId(String cid)
	{
		this.courseId = cid;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String sName)
	{
		this.name = sName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
		
	}
	public void init() {
		fromStudentDB = DAO.getStudentListFromDB();
	}
	
	public ArrayList studentList() {
		return fromStudentDB;
	}
	
	public String saveStudentDetails(Student newStudentObj) {
		return DAO.saveStudentDetailsInDB(newStudentObj);
	}
	
	public String editStudentRecord(int studentId) {
		return DAO.editStudentRecordInDB(studentId);
	}
	
	public String updateStudentDetails(Student updateStudentObj) {
		return DAO.updateStudentDetailsInDB(updateStudentObj);
	}
	
	public String deleteStudentRecord(int studentId) {
		return DAO.deleteStudentRecordInDB(studentId);
	}
	
	
	
	
}
