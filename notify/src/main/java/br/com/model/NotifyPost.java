package br.com.model;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class NotifyPost {
	
	private String itemId;
	private String field;
	private Double value;

	public NotifyPost(@JsonProperty("itemId") String itemId, 
			@JsonProperty("field") String field,
			@JsonProperty("value") Double value) {
		this.itemId = itemId;
		this.field = field;
		this.value = value;
	}
	
	public String getField() {
		return field;
	}
}
