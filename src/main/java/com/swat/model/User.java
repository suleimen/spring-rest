package com.swat.model;

import javax.persistence.*;

import com.swat.dto.UserDTO;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = true)
	private String name;

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "image_url", nullable = true)
	private String imageUrl;

	@Enumerated(value = EnumType.STRING)
	private Status status = Status.OFFLINE;

	public User() {

	}

	public User(UserDTO userDTO) {
		super();
		this.name = userDTO.getName();
		this.email = userDTO.getEmail();
		this.imageUrl = userDTO.getImageUrl();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
