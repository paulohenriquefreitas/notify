package br.com.controller;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.Notify;
import br.com.model.NotifyPost;
import br.com.service.NotifyService;
import br.com.service.PublisherService;

@RestController
@RequestMapping("notify")
@Getter
@Setter
public class NotifyController {
	private static final Logger LOG = Logger.getLogger(NotifyController.class);
	
	@Autowired
	private NotifyService notifyService;
	
	@Autowired
	private PublisherService publisherService;
	
	@RequestMapping(value = "{itemId}", method = RequestMethod.POST)
    public  ResponseEntity<?> put(@PathVariable String itemId, @RequestBody NotifyPost notifyPost) throws Exception {
		List<Notify> notify = notifyService.findNotify(notifyPost.getField());
		if(notify == null) {
			LOG.info("Notify [" + itemId + "] not found.");			
		}else{
			callMessageConsumer(notify);
		}
		return new ResponseEntity<>(HttpStatus.OK);
    }

	private void callMessageConsumer(List<Notify> notifies) throws Exception {
		publisherService.publish(notifies);
	}
}