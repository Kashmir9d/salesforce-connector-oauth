
package org.cookbook.oauth;

import java.io.Serializable;
import javax.annotation.Generated;
import org.cookbook.salesforceoauthConnector;


/**
 * Serializable class used to save and restore OAuth state from {@link salesforceoauthConnector }
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-21T11:22:52-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class salesforceoauthConnectorOAuthState implements Serializable
{

    public String accessToken;
    public String authorizationUrl;
    public String accessTokenUrl;
    public String refreshToken;

    /**
     * Retrieves accessToken
     * 
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * Sets accessToken
     * 
     * @param value Value to set
     */
    public void setAccessToken(String value) {
        this.accessToken = value;
    }

    /**
     * Retrieves authorizationUrl
     * 
     */
    public String getAuthorizationUrl() {
        return this.authorizationUrl;
    }

    /**
     * Sets authorizationUrl
     * 
     * @param value Value to set
     */
    public void setAuthorizationUrl(String value) {
        this.authorizationUrl = value;
    }

    /**
     * Retrieves accessTokenUrl
     * 
     */
    public String getAccessTokenUrl() {
        return this.accessTokenUrl;
    }

    /**
     * Sets accessTokenUrl
     * 
     * @param value Value to set
     */
    public void setAccessTokenUrl(String value) {
        this.accessTokenUrl = value;
    }

    /**
     * Retrieves refreshToken
     * 
     */
    public String getRefreshToken() {
        return this.refreshToken;
    }

    /**
     * Sets refreshToken
     * 
     * @param value Value to set
     */
    public void setRefreshToken(String value) {
        this.refreshToken = value;
    }

}
