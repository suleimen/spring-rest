package com.swat.util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DefaultResponse<T> implements Serializable {

	private T data;
	private String message;
	private Date timestamp = new Date();
	private int state;
	private boolean success;

	public DefaultResponse(T data) {
		super();
		this.data = data;
	}

	public DefaultResponse(Throwable e) {
		super();
		this.data = (T) ExceptionUtils.getStackTrace(e);
		this.message = e.getMessage();
		this.state = HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

	public DefaultResponse(T data, String message) {
		super();
		this.data = data;
		this.message = message;
	}

	public DefaultResponse() {
	}

	public DefaultResponse(String message, int state) {
		super();
		this.message = message;
		this.state = state;
	}

	public DefaultResponse SUCCESS() {
		this.success = true;
		this.state = HttpStatus.OK.value();
		return this;
	}

	public DefaultResponse ERROR(int state) {
		this.success = false;
		this.state = state;
		this.message = this.message == null ? "https://en.wikipedia.org/wiki/List_of_HTTP_status_codes#" + state
				: this.message;
		return this;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
