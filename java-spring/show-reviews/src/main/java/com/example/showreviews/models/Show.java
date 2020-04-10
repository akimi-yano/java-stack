package com.example.showreviews.models;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="shows")
public class Show {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Title must be present")
	@Size(min=1, max=255, message="Please enter a title")
	private String title;

	@NotNull(message="Network must be present")
	@Size(min=1, max=255, message="Please enter a network")
	private String network;

	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@Transient
//	@Formula("(select avg(r.rate) from ratings r where r.id = id)")
	private Double averageRating;
	
	
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="ratings", 
    		joinColumns=@JoinColumn(name="rated_shows_id"),
    		inverseJoinColumns=@JoinColumn(name="rated_users_id")
    		)
    private List<User> rated_users;
    

	public Show() {
		
	}

	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
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

	public List<User> getRated_users() {
		return rated_users;
	}

	public void setRated_users(List<User> rated_users) {
		this.rated_users = rated_users;
	}
	
	public Double getAverageRating() {
		return averageRating;
	}
	
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	
	
}
