package com.prosperity.api;

import javax.xml.bind.annotation.XmlRootElement;

import com.data.mongo.model.ApiUser;

/**
 * @version 1.0
 * @author: Sandipan
 * @since 29/04/2013
 */
@XmlRootElement
public class LoginResponse {

    private ApiUser apiUser;
    private CreateUserResponse authenticatedUserToken;

    public ApiUser getApiUser() {
        return apiUser;
    }

    public void setApiUser(ApiUser apiUser) {
        this.apiUser = apiUser;
    }

    public CreateUserResponse getAuthenticatedUserToken() {
        return authenticatedUserToken;
    }

    public void setAuthenticatedUserToken(CreateUserResponse authenticatedUserToken) {
        this.authenticatedUserToken = authenticatedUserToken;
    }
}
