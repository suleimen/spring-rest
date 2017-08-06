package com.swat.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import com.swat.util.DefaultResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class DefaultExceptionHandler {

	@RequestMapping(produces = { "application/vnd.captech-v1.0+json", "application/vnd.captech-v2.0+json" })
	@ExceptionHandler({ MissingServletRequestParameterException.class,
			UnsatisfiedServletRequestParameterException.class, HttpRequestMethodNotSupportedException.class,
			ServletRequestBindingException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody Map<String, Object> handleRequestException(Exception ex) {
		ex.printStackTrace();
		Map<String, Object> map = new HashMap();
		map.put("error", "Request Error");
		map.put("cause", ex.getMessage());
		return map;
	}

	@RequestMapping(produces = { "application/vnd.captech-v1.0+json", "application/vnd.captech-v2.0+json" })
	@ExceptionHandler({ DataIntegrityViolationException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody DefaultResponse handleConstraintException(DataIntegrityViolationException ex)
			throws IOException {
		ex.printStackTrace();
		return new DefaultResponse(getCause(ex)).ERROR(HttpStatus.BAD_REQUEST.value());
	}

	@RequestMapping(produces = { "application/vnd.captech-v1.0+json", "application/vnd.captech-v2.0+json" })
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody DefaultResponse handleUncaughtException(Exception ex) throws IOException {
		ex.printStackTrace();
		return new DefaultResponse(ex.getMessage()).ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

	@RequestMapping(produces = { "application/vnd.captech-v1.0+json", "application/vnd.captech-v2.0+json" })
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody DefaultResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
			throws IOException {
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		List<String> messages = new ArrayList();
		for (FieldError eror : errors) {
			messages.add(eror.getField() + " : " + eror.getDefaultMessage());
		}
		return new DefaultResponse(messages).ERROR(HttpStatus.BAD_REQUEST.value());
	}

	public Throwable getCause(Throwable ex) {
		return ex.getCause() != null ? getCause(ex.getCause()) : ex;
	}

}
