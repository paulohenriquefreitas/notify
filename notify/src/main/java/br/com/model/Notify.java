package br.com.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Document(collection="urlinfo")
public class Notify {
	
	private String id;
	private String field;
	private double value;
	private String url;
	private String name;
}
