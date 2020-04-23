package com.example.demo.servs;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repos.UserRepo;

@Service
public class UserServ {
	private final UserRepo urepo;
	public UserServ(UserRepo urepo) {
		this.urepo = urepo;
	}
	
	public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return urepo.save(user);
    }
	
	public User findOne(Long id) {
    	Optional<User> u = urepo.findById(id);
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
	
	public boolean authenticateUser(String email, String password) {
        User user = urepo.findByEmail(email);
        if(user == null) {
            return false;
        } else {
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
}
