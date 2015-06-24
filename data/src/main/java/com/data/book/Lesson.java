package com.data.book;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the lesson database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Lesson.findByLessonId", query="SELECT l FROM Lesson l where l.id = :id")
}) 
public class Lesson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="lesson_meta")
	private String lessonMeta;

	public Lesson() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLessonMeta() {
		return this.lessonMeta;
	}

	public void setLessonMeta(String lessonMeta) {
		this.lessonMeta = lessonMeta;
	}

}