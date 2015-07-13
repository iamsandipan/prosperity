package com.porterhead.api;

import com.data.book.constants.Role;
import com.data.dao.book.UserDAOService;
import com.data.mongo.exception.NopSqlDbException;
import com.data.mongo.model.ApiUser;
import com.data.mongo.model.User;
import com.porterhead.exception.AuthenticationException;
import com.porterhead.exception.DuplicateUserException;
import com.porterhead.exception.UserNotFoundException;
import com.prosperity.book.BaseService;

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

import javax.validation.Validator;

import static org.springframework.util.Assert.notNull;



@Service
public class UserService extends BaseService implements UserDetailsService {

    private Logger LOG = LoggerFactory.getLogger(UserService.class);
    private UserDAOService userDaoService;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(Validator validator, UserDAOService userDaoService, PasswordEncoder passwordEncoder) {
        super(validator);
        this.userDaoService = userDaoService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return locateUser(username);
    }

    @Transactional
    public ApiUser createUser(final CreateUserRequest createUserRequest) {

        LOG.info("Validating user request.");
        validate(createUserRequest);
        final String emailAddress = createUserRequest.getUser().getEmailAddress().toLowerCase();
        try {
			if (userDaoService.findByEmailAddress(emailAddress) == null) {
			    LOG.info("User does not already exist in the data store - creating a new user [{}].",
			            emailAddress);
			    User newUser = insertNewUser(createUserRequest);
			    LOG.debug("Created new user [{}].", newUser.getEmailAddress());
			    return new ApiUser(newUser);
			} else {
			    LOG.info("Duplicate user located, exception raised with appropriate HTTP response code.");
			    throw new DuplicateUserException();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public ApiUser authenticate(String username, String password) {
        Assert.notNull(username);
        Assert.notNull(password);
        User user = locateUser(username);
        if(!passwordEncoder.encode(password).equals(user.getHashedPassword())) {
            throw new AuthenticationException();
        }
        return new ApiUser(user);
    }

    @Transactional
    public ApiUser saveUser(String userId, UpdateUserRequest request) {
        validate(request);
        User user = userDaoService.findById(userId);
        if(user == null) {
            throw new UserNotFoundException();
        }
        if(request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if(request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if(request.getEmailAddress() != null) {
            if(!request.getEmailAddress().equals(user.getEmailAddress())) {
                user.setEmailAddress(request.getEmailAddress());
                user.setVerified(false);
            }
        }
        try {
			userDaoService.saveUser(user);
		} catch (NopSqlDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
        return userDaoService.locateUser(username);
    }

    private User insertNewUser(final CreateUserRequest createUserRequest) {
        String password = createUserRequest.getPassword().getPassword();
        User newUser = new User(createUserRequest.getUser(), passwordEncoder.encode(password), Role.ROLE_USER);
        return userDaoService.insertNewUser(newUser);
    }


}