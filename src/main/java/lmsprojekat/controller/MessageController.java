package lmsprojekat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.dto.MessageDTO;
import lmsprojekat.service.MessageService;


@RestController
@RequestMapping("/api/message")
public class MessageController extends BaseCrudController<MessageDTO, Long>{
	
	private final MessageService service;
	
//    public Message sendMessage(Message message) {
//        return message;
//    }
    
    public MessageController(MessageService service) {
    	this.service = service;
    }

	@Override
	protected MessageService getService() {
		return service;
	}
}