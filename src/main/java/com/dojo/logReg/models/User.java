package com.dojo.logReg.models;



import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="users")
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	  @NotEmpty(message="UserName is required")
	  private String userName;
     @Email(message="Email must be valid")
     @NotEmpty(message="Email isRequired")
    private String email;
    @NotNull(message="Password is required")
    @Size(min=5, max=128,message="Password must be greater than 5 characters")
    private String password;
    @Transient
    @NotNull(message="Passworg Comfirmation required")
    @Size(min=5, max=128,message="Password Comfirmation must not be less than 5")
    private String passwordConfirmation;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @OneToMany(mappedBy="user",fetch=FetchType.LAZY)
    private List<Task>tasks;
     
    
    public User() {
    	
    }

   public User(Long id, @NotEmpty(message = "UserName is required") String userName,
			@Email(message = "Email must be valid") @NotEmpty(message = "Email isRequired") String email,
			@NotNull(message = "Password is required") @Size(min = 5, max = 128, message = "Password must be greater than 5 characters") String password,
			@NotNull(message = "Passworg Comfirmation required") @Size(min = 5, max = 128, message = "Password Comfirmation must not be less than 5") String passwordConfirmation,
			Date createdAt, Date updatedAt, List<Task> tasks) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.tasks = tasks;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
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
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
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








	