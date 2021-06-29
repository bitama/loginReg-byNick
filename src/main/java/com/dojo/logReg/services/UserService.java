package com.dojo.logReg.services;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.dojo.logReg.models.Task;
import com.dojo.logReg.models.User;
import com.dojo.logReg.repositories.TaskRepository;
import com.dojo.logReg.repositories.UserRepository;


@Service
public class UserService {
private final UserRepository userRepository;
private TaskRepository taskRepo;

    
    public UserService(UserRepository userRepository,TaskRepository taskRepo) {
        this.userRepository = userRepository;
        this.taskRepo=taskRepo;
    }
     //    create Task
    public Task create(Task task) {
    	return taskRepo.save(task);
    }
    
     //display all Task
    public List<Task>getAllTasks(){
     return (List<Task>) taskRepo.findAll();
    }

    public Task viewOneTask(Long id) {
    	return taskRepo.findById(id).orElse(null);
    	
    }
 
    
    
   public Task getTask(Long id) {
		Optional<Task> optional = taskRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}

    }
    
    
   public Task updateTask(Long id,String name) {
    	Task toUpdateTask =taskRepo.findById(id).orElseGet(null);
    	if (toUpdateTask==null) {
			return null;
		}
    	else {
    		toUpdateTask.setName(name);
    		return taskRepo.save(toUpdateTask);
    	}
      }
        public  Task updateTask( Task idea) {
    	return taskRepo.save(idea);
    }
        
        
        public void deleteTask(Long id) {
    		taskRepo.deleteById(id);
    	}
    
    
    
    
  // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
}
