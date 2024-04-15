package com.webserviceex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(OrderServiceCustomException.class)
	public ResponseEntity<ErrorResponse> handleProductServiceException(OrderServiceCustomException orderServiceCustomException){
		ErrorResponse errorResponse = ErrorResponse.builder()
								      .message(orderServiceCustomException.getMessage())
								      .errorCode(orderServiceCustomException.getErrorCode())
								      .build();
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	

}
