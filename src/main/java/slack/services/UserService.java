package slack.services;

import java.util.ArrayList;

import org.assertj.core.internal.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import slack.services.SSUserDetailsService;
import slack.models.User;
import slack.repositories.UserRepository;
@Service
public class UserService {
	@Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Long countByEmail(String email) {
        return userRepository.countByEmail(email);
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    
    public ArrayList<User> findAll(){
    	
    	ArrayList<User> listofusers = new ArrayList<User>();
    	
    	Iterable<User> iter = userRepository.findAll();
    	for(User u: iter) {
    		listofusers.add(u);
    	}
    	
    	return listofusers; 	
    }
    
    
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive("true");
        userRepository.save(user);
    }
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive("true");
        userRepository.save(user);
    }
    
    
    
    public SSUserDetailsService createservice()  
    {
    	return new SSUserDetailsService(userRepository);
    }

}
