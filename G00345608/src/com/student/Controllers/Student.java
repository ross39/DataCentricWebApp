package com.student.Controllers;

public class Student {
	private int studentId;
	private int courseId;
	private String name;
	private String address;
	
	public int getStudentId(){
		return studentId;
	}
	
	public void setStudentId(int id){
		this.studentId = id;
	}
	
	public int getCourseId(){
		return courseId;
	}
	
	public void setCourseId(int cid)
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
}
