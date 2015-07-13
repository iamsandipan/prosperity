
package com.prosperity.book;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.book.Card;
import com.data.dao.book.CardDAOService;
import com.google.gson.Gson;
import com.prosperity.model.CardWebModel;

/** Example resource class hosted at the URI path "/myresource"
 */

@Path("/cardservice")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@RequestMapping("/cardservice")
public class CardResource {
	
	@Autowired
	private CardDAOService cardDAOService;
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    
	//Get it Sub Resource http://localhost:8080/prosperity-book/webresources/myresource/getVersion
	
	@RequestMapping(value="/getVersion", method = RequestMethod.GET)
	public @ResponseBody String getVersion() {
		return "1.0.0-Beta";
    }

	
	@RequestMapping(value="/createCard", method = RequestMethod.POST)
	public @ResponseBody String createCard(CardWebModel lesson) {
		Gson gson = new Gson();
		String createdLesson = cardDAOService.createCard(gson.toJson(lesson), lesson.getPreviousCardId(), lesson.getNextCardId());
		return createdLesson;
    }
	
	
	
	@RequestMapping(value="/getCard/{id}", method = RequestMethod.GET)
    public @ResponseBody CardWebModel getCard(@PathParam("id") String id) {
		//Return the xml formmat of the lesson
		Card card = cardDAOService.findCardById(id);
		CardWebModel cardModel = new CardWebModel(card);
		return cardModel;
    }
	
	
	
/*	@GET
    @Path("/results/send/{userid}")
    public String sendResults(@PathParam("userid") String userid) {
		//Return the xml formmat of the lesson
		return "you asked for pim" + userid ;
    }
	

	@GET
    @Path("/results/get/{userid}")
    public String getResults(@PathParam("userid") String userid) {
		//Return the xml formmat of the lesson
		return "you asked for pim" + userid ;
    }
*/
	
	//http://localhost:8080/prosperity-book/webresources/myresource/logEvent/type/ABC1/context/CON1
	/*@GET
    @Path("/logEvent/type/{type}/context/{context}")
    public String logEvent(@PathParam("type") String type, @PathParam("context") String context) {
		EventTypes eventType = EventTypes.getType(type);
		if(eventType != EventTypes.INVALID ){
			itemWebLayerServices.createEvent(type, context);
		}
        return "Event Created";
    }
	*/
}
