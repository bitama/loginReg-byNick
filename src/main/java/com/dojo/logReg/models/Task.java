package com.dojo.logReg.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="tasks")
public class Task {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@NotEmpty(message="name is required")
	@Size(min=3,message="Must be at least 3")
	private String name;
	@NotEmpty(message="name is required")
	@Size(min=3,message="Must be at least 3")
	private String assignee;
	@NotEmpty(message="name is required")
	@Size(min=3,message="Must be at least 3")
	private String priority;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    public Task() {
    	
    }
    
   public Task(Long id,
			@NotEmpty(message = "name is required") @Size(min = 3, message = "Must be at least 3") String name,
			@NotEmpty(message = "name is required") @Size(min = 3, message = "Must be at least 3") String assignee,
			@NotEmpty(message = "name is required") @Size(min = 3, message = "Must be at least 3") String priority,
			User user, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.assignee = assignee;
		this.priority = priority;
		this.user = user;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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

public String getAssignee() {
	return assignee;
}

public void setAssignee(String assignee) {
	this.assignee = assignee;
}

public String getPriority() {
	return priority;
}

public void setPriority(String priority) {
	this.priority = priority;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
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

@PrePersist
protected void onCreate(){
    this.createdAt = new Date();
}
@PreUpdate
protected void onUpdate(){
    this.updatedAt = new Date();
}

    
   
}
