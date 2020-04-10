package com.example.greatideas.models;


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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Name must be present")
	@Size(min=1, max=255, message="Please enter a name")
	private String name;
	
	@NotNull(message="Email must be present")
	@Email(message="Email must be in valid format")
	private String email;
	
	@Size(min=8, max=255, message="Password must be at least 8 characters long")
	private String password;
	
	@Transient
	private String passwordConfirmation;
	
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
    @OneToMany(mappedBy="creator", fetch=FetchType.LAZY)
    private List<Idea> createdIdeas;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="users_likes", 
    		joinColumns=@JoinColumn(name="liked_users_id"),
    		inverseJoinColumns=@JoinColumn(name="liked_ideas_id")
    		)
    private List<Idea> liked_ideas;
    
	public User() {
	
	}
	
	
	
	
	public List<Idea> getLiked_ideas() {
		return liked_ideas;
	}




	public void setLiked_ideas(List<Idea> liked_ideas) {
		this.liked_ideas = liked_ideas;
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




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}




	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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




	public List<Idea> getCreatedIdeas() {
		return createdIdeas;
	}




	public void setCreatedIdeas(List<Idea> createdIdeas) {
		this.createdIdeas = createdIdeas;
	}








}
