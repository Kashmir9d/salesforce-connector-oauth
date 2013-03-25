
package org.cookbook.oauth;

import javax.annotation.Generated;
import org.cookbook.adapters.salesforceoauthConnectorOAuth2Adapter;
import org.mule.api.MessagingException;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.oauth.OAuthManager;
import org.mule.api.processor.MessageProcessor;
import org.mule.config.i18n.MessageFactory;

@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-25T12:03:30-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class FetchAccessTokenMessageProcessor implements MessageProcessor
{

    public String redirectUri;
    private String accessTokenUrl = null;
    private OAuthManager oauthManager;

    public FetchAccessTokenMessageProcessor(OAuthManager oauthManager) {
        this.oauthManager = oauthManager;
    }

    /**
     * Sets redirectUri
     * 
     * @param value Value to set
     */
    public void setRedirectUri(String value) {
        this.redirectUri = value;
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
     * Retrieves accessTokenUrl
     * 
     */
    public String getAccessTokenUrl() {
        return this.accessTokenUrl;
    }

    public MuleEvent process(MuleEvent event)
        throws MuleException
    {
        try {
            salesforceoauthConnectorOAuth2Adapter oauthAdapter = ((salesforceoauthConnectorOAuth2Adapter) oauthManager.createAccessToken(((String) event.getMessage().getInvocationProperty("_oauthVerifier"))));
            oauthAdapter.setAccessTokenUrl(accessTokenUrl);
            oauthAdapter.fetchAccessToken(accessTokenUrl, redirectUri);
            ((salesforceoauthConnectorOAuthManager) oauthManager).getAccessTokenPoolFactory().passivateObject(((salesforceoauthConnectorOAuth2Adapter) oauthAdapter).getAccessId(), oauthAdapter);
            event.getMessage().setInvocationProperty("OAuthAccessTokenId", ((salesforceoauthConnectorOAuth2Adapter) oauthAdapter).getAccessId());
        } catch (Exception e) {
            throw new MessagingException(MessageFactory.createStaticMessage("Unable to fetch access token"), event, e);
        }
        return event;
    }

}
