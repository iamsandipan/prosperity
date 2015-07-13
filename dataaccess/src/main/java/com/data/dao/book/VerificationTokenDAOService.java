package com.data.dao.book;

import java.util.List;
import java.util.regex.Pattern;














import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.data.book.constants.VerificationTokenType;
import com.data.mongo.exception.NopSqlDbException;
import com.data.mongo.model.User;
import com.data.mongo.model.VerificationToken;
import com.data.oauth2.mongodb.UserRepository;
import com.data.oauth2.mongodb.VerificationTokenRepository;

/**
 * @version 1.0
 * @author: Sandipan
 * @since 13/05/2013
 */
@Service("verificationTokenService")
public class VerificationTokenDAOService {

    private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-f]{8}(-[0-9a-f]{4}){3}-[0-9a-f]{12}$");

    private VerificationTokenRepository tokenRepository;


    private UserRepository userRepository;


    @Value("${token.emailVerification.timeToLive.inMinutes}")
    private int emailVerificationTokenExpiryTimeInMinutes;

    @Value("${token.emailRegistration.timeToLive.inMinutes}")
    private int emailRegistrationTokenExpiryTimeInMinutes;

    @Value("${token.lostPassword.timeToLive.inMinutes}")
    private int lostPasswordTokenExpiryTimeInMinutes;

    @Value("${hostName.url}")
    private String hostNameUrl;

 
    @Autowired
    public VerificationTokenDAOService(UserRepository userRepository, VerificationTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

  
    @Transactional
    @Async
    public VerificationToken sendEmailRegistrationToken(String userId) throws NopSqlDbException {
        User user = ensureUserIsLoaded(userId);
        VerificationToken token = new VerificationToken(user,
                VerificationTokenType.emailRegistration, emailRegistrationTokenExpiryTimeInMinutes);
        tokenRepository.save(token);
        return token;
    }

    /**
     * generate token if user found otherwise do nothing
     *
     * @param lostPasswordRequest
     * @return a token or null if user not found
     */
    @Transactional
    @Async
    public VerificationToken sendLostPasswordToken(String emailAddress) {
        VerificationToken token = null;
        User user = userRepository.findByEmailAddress(emailAddress);
        if (user != null) {
            List<VerificationToken> tokens = tokenRepository.findByUserIdAndTokenType(user.getId(), VerificationTokenType.lostPassword);
            token = getActiveToken(tokens);
            if (token == null) {
                token = new VerificationToken(user,
                VerificationTokenType.lostPassword, lostPasswordTokenExpiryTimeInMinutes);
                tokenRepository.save(token);
            }

        }

        return token;
    }

    @Transactional
    public VerificationToken verify(String base64EncodedToken) throws NopSqlDbException {
        VerificationToken token = loadToken(base64EncodedToken);
        User user = userRepository.findOne(token.getUserId());
        if (token.isVerified() || user.isVerified()) {
            throw new NopSqlDbException();
        }
        token.setVerified(true);
        user.setVerified(true);
        userRepository.save(user);
        tokenRepository.save(token);
        return token;
    }

   
    @Transactional
    public VerificationToken resetPassword(String base64EncodedToken, String encodedPassword) throws NopSqlDbException {
        Assert.notNull(base64EncodedToken);
        VerificationToken token = loadToken(base64EncodedToken);
        if (token.isVerified()) {
            throw new NopSqlDbException();
        }
        token.setVerified(true);
        User user = userRepository.findOne(token.getUserId());
        try {
            user.setHashedPassword(encodedPassword);
        } catch (Exception e) {
            throw new NopSqlDbException();
        }
        //set user to verified if not already and authenticated role
        user.setVerified(true);
        userRepository.save(user);
        tokenRepository.save(token);
        return token;
    }

    private VerificationToken loadToken(String base64EncodedToken) throws NopSqlDbException {
    	Assert.notNull(base64EncodedToken);
        String rawToken = new String(Base64.decodeBase64(base64EncodedToken.getBytes()));
        VerificationToken token = tokenRepository.findByToken(rawToken);
        if (token == null) {
            throw new NopSqlDbException();
        }
        if (token.hasExpired()) {
            throw new NopSqlDbException();
        }
        return token;
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private User ensureUserIsLoaded(String userIdentifier) throws NopSqlDbException {
        User user = null;
        if (isValidUuid(userIdentifier)) {
            user = userRepository.findOne(userIdentifier);
        } else {
            user = userRepository.findByEmailAddress(userIdentifier);
        }
        if (user == null) {
            throw new NopSqlDbException();
        }
        return user;
    }

    private VerificationToken getActiveToken(List<VerificationToken> tokens) {
        VerificationToken activeToken = null;
        for (VerificationToken token : tokens) {
            if (!token.hasExpired() && !token.isVerified()) {
                activeToken = token;
                break;
            }
        }
        return activeToken;
    }

    private boolean isValidUuid(String uuid) {
        return UUID_PATTERN.matcher(uuid).matches();
    }

    public void setEmailVerificationTokenExpiryTimeInMinutes(int emailVerificationTokenExpiryTimeInMinutes) {
        this.emailVerificationTokenExpiryTimeInMinutes = emailVerificationTokenExpiryTimeInMinutes;
    }

    public void setEmailRegistrationTokenExpiryTimeInMinutes(int emailRegistrationTokenExpiryTimeInMinutes) {
        this.emailRegistrationTokenExpiryTimeInMinutes = emailRegistrationTokenExpiryTimeInMinutes;
    }

    public void setLostPasswordTokenExpiryTimeInMinutes(int lostPasswordTokenExpiryTimeInMinutes) {
        this.lostPasswordTokenExpiryTimeInMinutes = lostPasswordTokenExpiryTimeInMinutes;
    }

    public void setHostNameUrl(String hostNameUrl) {
        this.hostNameUrl = hostNameUrl;
    }
}
