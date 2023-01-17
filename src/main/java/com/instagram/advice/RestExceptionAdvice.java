package com.instagram.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.instagram.dto.ErrorResponse;
import com.instagram.exception.NotFoundException;

@RestControllerAdvice
public class RestExceptionAdvice {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse exceptionHandler(NotFoundException exception) {
		return new ErrorResponse(exception.getMessage());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ErrorResponse exceptionHandler1(NotFoundException exception) {
		return new ErrorResponse(exception.getMessage());
	}
}
