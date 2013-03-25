
package org.cookbook.processors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Generated;
import org.cookbook.oauth.ExtractAuthorizationCodeMessageProcessor;
import org.cookbook.oauth.FetchAccessTokenMessageProcessor;
import org.cookbook.oauth.salesforceoauthConnectorOAuthManager;
import org.cookbook.process.DefaultHttpCallback;
import org.mule.api.DefaultMuleException;
import org.mule.api.MessagingException;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.callback.HttpCallback;
import org.mule.api.construct.FlowConstructAware;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.api.processor.InterceptingMessageProcessor;
import org.mule.api.processor.MessageProcessor;
import org.mule.config.i18n.CoreMessages;

@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-25T12:03:30-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class AuthorizeMessageProcessor
    extends AbstractMessageProcessor<salesforceoauthConnectorOAuthManager>
    implements FlowConstructAware, MuleContextAware, Initialisable, Startable, Stoppable, InterceptingMessageProcessor
{

    private MessageProcessor listener;
    private String authorizationUrl = null;
    private String accessTokenUrl = null;
    private HttpCallback oauthCallback;
    private final static Pattern AUTH_CODE_PATTERN = Pattern.compile("code=([^&]+)");
    private String state;

    /**
     * Sets listener
     * 
     * @param value Value to set
     */
    public void setListener(MessageProcessor value) {
        this.listener = value;
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
     * Retrieves authorizationUrl
     * 
     */
    public String getAuthorizationUrl() {
        return this.authorizationUrl;
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

    /**
     * Sets state
     * 
     * @param value Value to set
     */
    public void setState(String value) {
        this.state = value;
    }

    public void initialise()
        throws InitialisationException
    {
    }

    public void start()
        throws MuleException
    {
        salesforceoauthConnectorOAuthManager moduleObject = null;
        try {
            moduleObject = findOrCreate(salesforceoauthConnectorOAuthManager.class, false, null);
        } catch (IllegalAccessException e) {
            throw new DefaultMuleException(CoreMessages.failedToStart("authorize"), e);
        } catch (InstantiationException e) {
            throw new DefaultMuleException(CoreMessages.failedToStart("authorize"), e);
        }
        if (oauthCallback == null) {
            FetchAccessTokenMessageProcessor fetchAccessTokenMessageProcessor = new FetchAccessTokenMessageProcessor(moduleObject);
            oauthCallback = new DefaultHttpCallback(Arrays.asList(new ExtractAuthorizationCodeMessageProcessor(AUTH_CODE_PATTERN), fetchAccessTokenMessageProcessor, listener), getMuleContext(), moduleObject.getDomain(), moduleObject.getLocalPort(), moduleObject.getRemotePort(), moduleObject.getPath(), moduleObject.getAsync(), getFlowConstruct().getExceptionListener(), moduleObject.getConnector());
            fetchAccessTokenMessageProcessor.setRedirectUri(oauthCallback.getUrl());
            if (accessTokenUrl!= null) {
                fetchAccessTokenMessageProcessor.setAccessTokenUrl(accessTokenUrl);
            } else {
                fetchAccessTokenMessageProcessor.setAccessTokenUrl(moduleObject.getAccessTokenUrl());
            }
            oauthCallback.start();
        }
    }

    public void stop()
        throws MuleException
    {
        if (oauthCallback!= null) {
            oauthCallback.stop();
        }
    }

    /**
     * Starts the OAuth authorization process
     * 
     * @param event MuleEvent to be processed
     * @throws MuleException
     */
    public MuleEvent process(MuleEvent event)
        throws MuleException
    {
        salesforceoauthConnectorOAuthManager moduleObject = null;
        try {
            moduleObject = findOrCreate(salesforceoauthConnectorOAuthManager.class, false, null);
            Map<String, String> extraParameters = new HashMap<String, String>();
            if (state!= null) {
                try {
                    String transformerState = ((String) evaluateAndTransform(getMuleContext(), event, AuthorizeMessageProcessor.class.getDeclaredField("state").getGenericType(), null, state));
                    extraParameters.put("state", transformerState);
                } catch (NoSuchFieldException e) {
                    throw new MessagingException(CoreMessages.createStaticMessage("internal error"), event, e);
                }
            }
            String location = moduleObject.authorize(extraParameters, authorizationUrl, oauthCallback.getUrl());
            event.getMessage().setOutboundProperty("http.status", "302");
            event.getMessage().setOutboundProperty("Location", location);
            return event;
        } catch (Exception e) {
            throw new MessagingException(CoreMessages.failedToInvoke("authorize"), event, e);
        }
    }

}
