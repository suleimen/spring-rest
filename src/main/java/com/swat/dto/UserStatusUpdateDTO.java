package com.swat.dto;

import com.swat.model.Status;

public class UserStatusUpdateDTO {

	private Integer id;

	private Status status;

	private Status previousStatus;

	public UserStatusUpdateDTO() {
		super();
	}

	public UserStatusUpdateDTO(Integer id, Status status, Status previousStatus) {
		super();
		this.id = id;
		this.status = status;
		this.previousStatus = previousStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(Status previousStatus) {
		this.previousStatus = previousStatus;
	}

}
