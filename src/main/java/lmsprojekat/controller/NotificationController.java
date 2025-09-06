package lmsprojekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.dto.NotificationDTO;
import lmsprojekat.service.AbstractCrudService;
import lmsprojekat.service.NotificationService;

@RestController
@RequestMapping("/api/notification")
public class NotificationController extends BaseCrudController<NotificationDTO, Long>{

    private final NotificationService service;
    
    public NotificationController(NotificationService service) {
    	this.service = service;
    }

	@Override
	protected NotificationService getService() {
		return service;
	}
    
}
