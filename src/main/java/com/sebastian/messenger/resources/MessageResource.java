package com.sebastian.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sebastian.messenger.model.Message;
import com.sebastian.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

		private MessageService messageService = new MessageService();
	
		@GET		
		public List<Message> getMessages(@QueryParam("year") int year,
				                         @QueryParam("start") int start,
				                         @QueryParam("size") int size   ) {
			
			if(year > 0) return messageService.getAllMessagesForYear(year);
			if(start >= 0 && size > 0) return messageService.getAllMessagesPaginated(start, size);
			
			return messageService.getAllMessages();
		}
		
		@GET
		@Path("/{messageId}")		
		public Message getMessage(@PathParam("messageId") long messageId) {
			
			System.out.println("aqui esta el valor: " + messageId);
			return messageService.getMessage(messageId);
			
		}
		
		@POST		
		public Message addMessage(Message message) {
			return messageService.addMessage(message);			
		}
		
		
		@PUT		
		@Path("/{messageId}")		
		public Message putMessage(@PathParam("messageId") long id, Message message) {
			message.setId(id);
			return messageService.updateMessage(message);
		}
		
		@DELETE
		@Path("/{messageId}")		
		public void removeMessage(@PathParam("messageId") long messageId) {
			messageService.removeMessage(messageId);
		}
		
		
		@GET
		@Path("/{messageId}/comments/")
		public CommentResource getCommentResource() {
			
			return new CommentResource();
		}
		
}
