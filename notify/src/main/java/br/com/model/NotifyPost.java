package br.com.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NotifyPost {
	
	private String itemId;
	private String field;
	private double value;
	public String getField() {
		return field;
	}
	
	

}
