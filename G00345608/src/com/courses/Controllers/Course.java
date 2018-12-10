package com.courses.Controllers;

import java.util.ArrayList;

import com.course.DAOs.*;
public class Course {

	private int id;
	private String name;
	private int duration;
	
	public ArrayList courseListFromDB;
	
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getDuration(){
		return duration;
	}
	
	public void setDuration(int duration){
		this.duration = duration;
	}
	
	public void init(){
		courseListFromDB = DAO.getCourseListFromDB();	
	}
	
	public ArrayList courseList(){
		return courseListFromDB;
	}
	
	public String saveCourseDetails(Course newCourseObj){
		return DAO.saveCourseDetailsInDB(newCourseObj);
	}
	
	public String editCourseDetails(int courseId){
		return DAO.editCourseRecordInDB(courseId);
	}
	
	public String updateCourseDetails(Course updateCourseObJ){
		return DAO.updateCoursetDetailsInDB(updateCourseObJ);
	}
	
	public String deleteCourseRecord(int courseId){
		return DAO.deleteCourseRecordInDB(courseId);
	}
	
	
	
}
