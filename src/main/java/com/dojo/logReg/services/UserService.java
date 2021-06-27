package com.dojo.logReg.services;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.dojo.logReg.models.Menu;
import com.dojo.logReg.models.User;
import com.dojo.logReg.repositories.MenuRepository;
import com.dojo.logReg.repositories.UserRepository;


@Service
public class UserService {
private final UserRepository userRepository;
private MenuRepository menuRepo;

    
    public UserService(UserRepository userRepository,MenuRepository menuRepo) {
        this.userRepository = userRepository;
        this.menuRepo=menuRepo;
    }
     //    create Menu
    public Menu create(Menu menu) {
    	return menuRepo.save(menu);
    }
    
     //display all Menus
    public List<Menu>getAllMenus(){
     return (List<Menu>) menuRepo.findAll();
    }
    
    public Menu viewOneMenu(Long id) {
    	return menuRepo.findById(id).orElse(null);
    }
    
    
   public Menu getMenu(Long id) {
		Optional<Menu> optional = menuRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}

    }
    
    
   public Menu updateMenu(Long id,String name,String description) {
    	Menu toUpdateMenu =menuRepo.findById(id).orElseGet(null);
    	if (toUpdateMenu==null) {
			return null;
		}
    	else {
    		toUpdateMenu.setName(name);
    		toUpdateMenu.setDescription(description);
    		
    		return menuRepo.save(toUpdateMenu);
    	}
      }
        public  Menu updateMenu( Menu menu) {
    	return menuRepo.save(menu);
    }
        
        
        public void deleteMenu(Long id) {
    		menuRepo.deleteById(id);
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
