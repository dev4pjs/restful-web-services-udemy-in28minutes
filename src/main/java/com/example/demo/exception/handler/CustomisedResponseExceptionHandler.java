package com.example.demo.exception.handler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.UserNotFoundException;

@ControllerAdvice()
public class CustomisedResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse();
		response.setTimeStamp(new Date());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setDetails("User Not Found in Backend, Please check if you are passing valid userid");
		return new ResponseEntity(response, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception ex, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse();
		response.setTimeStamp(new Date());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setDetails("Internal Error");

		return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setTimeStamp(new Date());
		response.setMessage("Validation Failed!");
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setDetails(ex.getBindingResult().toString());
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

}
