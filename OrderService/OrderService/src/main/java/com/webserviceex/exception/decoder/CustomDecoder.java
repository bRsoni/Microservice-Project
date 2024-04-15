package com.webserviceex.exception.decoder;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webserviceex.exception.ErrorResponse;
import com.webserviceex.exception.OrderServiceCustomException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
			return new OrderServiceCustomException(errorResponse.getMessage(),errorResponse.getErrorCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new OrderServiceCustomException("Service Internal Exception","SERVICE_EXCEPTION");
	}
	

}
