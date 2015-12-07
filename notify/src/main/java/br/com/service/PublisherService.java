package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.broker.Publisher;
import br.com.model.Notify;

@Service
public class PublisherService {

	@Autowired
	private Publisher publisher;
	
	public void publish(List<Notify> notifies) throws Exception {
		for(Notify notify : notifies) {
			publisher.publish(notify);
		}
	}
}
