package com.dojo.logReg.services;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.dojo.logReg.models.Idea;
import com.dojo.logReg.models.User;
import com.dojo.logReg.repositories.IdeaRepository;
import com.dojo.logReg.repositories.UserRepository;


@Service
public class UserService {
private final UserRepository userRepository;
private IdeaRepository ideaRepo;

    
    public UserService(UserRepository userRepository,IdeaRepository ideaRepo) {
        this.userRepository = userRepository;
        this.ideaRepo=ideaRepo;
    }
     //    create Idea
    public Idea create(Idea idea) {
    	return ideaRepo.save(idea);
    }
    
     //display all Ideas
    public List<Idea>getAllIdeas(){
     return (List<Idea>) ideaRepo.findAll();
    }
    
    public Idea viewOneIdea(Long id) {
    	return ideaRepo.findById(id).orElse(null);
    }
    
    
   public Idea getIdea(Long id) {
		Optional<Idea> optional = ideaRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}

    }
    
    
   public Idea updateIdea(Long id,String name,String description) {
    	Idea toUpdateIdea =ideaRepo.findById(id).orElseGet(null);
    	if (toUpdateIdea==null) {
			return null;
		}
    	else {
    		toUpdateIdea.setName(name);
    		toUpdateIdea.setDescription(description);
    		
    		
    		return ideaRepo.save(toUpdateIdea);
    	}
      }
        public  Idea updateIdea( Idea idea) {
    	return ideaRepo.save(idea);
    }
        
        
        public void deleteIdea(Long id) {
    		ideaRepo.deleteById(id);
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
