package com.prosperity.book;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.data.book.Lesson;
import com.data.dao.book.CardDAOService;
import com.data.dao.book.LessonDAOService;
import com.google.gson.Gson;
import com.prosperity.model.CardWebModel;
import com.prosperity.model.LessonWebModel;

@Path("/lessonservice")
@Component
public class LessonResource {
	@Autowired
	private LessonDAOService lessonDAOService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getVersion")
    public String getVersion() {
		return "1.0.0-Beta";
    }
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @Path("/createLesson")
    public String createLesson(String lessonMeta) {
		return lessonDAOService.createLesson(lessonMeta);
    }
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @Path("/getLesson/{id}")
    public Lesson findLesson(@PathParam("id") String lessonId) {
		return lessonDAOService.findByLessonId(lessonId);
    }

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateLesson/{id}")
    public String updateLesson(Lesson lesson, @PathParam("id") String id) {
		return lessonDAOService.updateLesson(lesson, id);
    }
	
	
	


}
