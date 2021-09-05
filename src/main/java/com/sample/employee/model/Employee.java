package com.sample.employee.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "Name")
	@NotBlank(message = "Name is mandatory")
	private String name;

	@Column(name = "EmailAddress")
	@Email
	private String email;

	@Column(name = "JoiningDate")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate joiningDate;

	public Employee() {
	}

	public Employee(String name, String emailAddress, LocalDate joiningDate) {
		super();
		this.name = name;
		this.email = emailAddress;
		this.joiningDate = joiningDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return email;
	}

	public void setEmailAddress(String emailAddress) {
		this.email = emailAddress;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", emailAddress=" + email + ", joiningDate="
				+ joiningDate + "]";
	}

}
