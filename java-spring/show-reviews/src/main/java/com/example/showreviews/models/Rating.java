package com.example.showreviews.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ratings")
public class Rating {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Please enter a number between 1 and 5")
	@Min(value=1, message="Please enter a number between 1 and 5")
	@Max(value=5, message="Please enter a number between 1 and 5")
	private Integer rate;
	    
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	    
	    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="rated_shows_id")
	private Show show;
	    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="rated_users_id")
	private User user;
	    
	public Rating() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Show getRated_shows() {
		return show;
	}

	public void setRated_shows(Show rated_shows) {
		this.show = rated_shows;
	}

	public User getRated_users() {
		return user;
	}

	public void setRated_users(User rated_users) {
		this.user = rated_users;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
	    
}
