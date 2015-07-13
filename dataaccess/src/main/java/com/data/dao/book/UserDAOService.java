package com.data.dao.book;

import static org.springframework.util.Assert.notNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.data.book.constants.Role;
import com.data.mongo.exception.NopSqlDbException;
import com.data.mongo.model.ApiUser;
import com.data.mongo.model.User;
import com.data.oauth2.mongodb.UserRepository;



@Service
public class UserDAOService implements UserDetailsService {

    private Logger LOG = LoggerFactory.getLogger(UserDAOService.class);
   
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

/*    @Autowired
    public UserDAOService(final UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return locateUser(username);
    }

    public  User findByEmailAddress(String userName){
    	return userRepository.findByEmailAddress(userName.toLowerCase());
    }

    public String encode(String passwd){
    	return passwordEncoder.encode(passwd);
    }
    
    
    @Transactional
    public ApiUser createUser(final String  emailAddress, User user, String password) {

        try {
			if (userRepository.findByEmailAddress(emailAddress) == null) {
			    LOG.info("User does not already exist in the data store - creating a new user [{}].", emailAddress);
			    User newUser = insertNewUser(user);
			    LOG.debug("Created new user [{}].", newUser.getEmailAddress());
			    return new ApiUser(newUser);
			} else {
			    LOG.info("Duplicate user located, exception raised with appropriate HTTP response code.");
			    throw new NopSqlDbException();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public ApiUser authenticate(String username, String password) throws NopSqlDbException {
        Assert.notNull(username);
        Assert.notNull(password);
        User user = locateUser(username);
        if(!passwordEncoder.encode(password).equals(user.getHashedPassword())) {
            throw new NopSqlDbException();
        }
        return new ApiUser(user);
    }
    
    public User findById(String userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    public ApiUser saveUser(User user) throws NopSqlDbException {
        userRepository.save(user);
        return new ApiUser(user);
    }

    public ApiUser getUser(String userId) throws NopSqlDbException {
        Assert.notNull(userId);
        User user = userRepository.findById(userId);
        if(user == null) {
            throw new NopSqlDbException();
        }
        return new ApiUser(user);
    }

    /**
     * Locate the user and throw an exception if not found.
     *
     * @param username
     * @return a User object is guaranteed.
     * @throws AuthenticationException if user not located.
     */
    public User locateUser(final String username) {
        notNull(username, "Mandatory argument 'username' missing.");
        User user = userRepository.findByEmailAddress(username.toLowerCase());
        if (user == null) {
            LOG.debug("Credentials [{}] failed to locate a user.", username.toLowerCase());
            throw new UsernameNotFoundException("failed to locate a user");
        }
        return user;
    }

    public User insertNewUser(final User createUserRequest) {
        return userRepository.save(createUserRequest);
    }


}