package br.com.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Notify {
	
	private String itemId;
	private double price	;
	private String url;
}
