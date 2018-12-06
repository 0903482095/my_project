package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "passwordresettoken")
public class PasswordResetToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String token;

	@OneToOne(targetEntity = User.class, fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

//	private Time expiryDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public Time getExpiryDate() {
//		return expiryDate;
//	}
//
//	public void setExpiryDate(Time expiryDate) {
//		this.expiryDate = expiryDate;
//	}
//
//	public void setExpiryDate(int minutes) {
//		Calendar now = Calendar.getInstance();
//		now.add(Calendar.MINUTE, minutes);
//		this.expiryDate = new Time(now.getTime().getTime());
//	}
//
//	public boolean isExpired() {
//		return Calendar.getInstance().after(this.expiryDate);
//	}
}
