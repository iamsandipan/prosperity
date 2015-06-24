package com.prosperity.model;

import com.data.book.Lesson;

public class LessonWebModel {
	
	private String lessonId;
	
	private String lessonMeta;
	
	public LessonWebModel(){}
	
	public LessonWebModel(Lesson lesson){
		
		
		
	}

	public String getLessonMeta() {
		return lessonMeta;
	}

	public void setLessonMeta(String lessonMeta) {
		this.lessonMeta = lessonMeta;
	}

	public String getLessonId() {
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	
	

}
