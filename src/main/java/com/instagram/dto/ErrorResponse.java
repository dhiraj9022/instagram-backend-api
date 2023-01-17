package com.instagram.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private String msg;

	public ErrorResponse(String message) {
		msg = message;
	}

}
