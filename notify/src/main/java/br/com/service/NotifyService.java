package br.com.service;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dao.MongoDao;
import br.com.model.Notify;
@Service
@Getter
@Setter
public class NotifyService {
	@Autowired
	private MongoDao mongoDao ;
	
	public List<Notify> findNotify(String field){
		List<Notify> notify = mongoDao.findNotify(field);
		return notify;
	}
		
}
