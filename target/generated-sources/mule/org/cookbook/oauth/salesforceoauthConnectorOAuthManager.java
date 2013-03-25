
package org.cookbook.oauth;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.cookbook.adapters.salesforceoauthConnectorHttpCallbackAdapter;
import org.cookbook.adapters.salesforceoauthConnectorOAuth2Adapter;
import org.cookbook.salesforceoauthConnector;
import org.mule.api.Capabilities;
import org.mule.api.Capability;
import org.mule.api.MetadataAware;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.config.MuleProperties;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.construct.FlowConstructAware;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.api.oauth.OAuthManager;
import org.mule.api.process.ProcessAdapter;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.store.ObjectStore;
import org.mule.config.i18n.CoreMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A {@code salesforceoauthConnectorOAuthManager} is a wrapper around {@link salesforceoauthConnector } that adds access token management capabilities to the pojo.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-25T12:28:48-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class salesforceoauthConnectorOAuthManager
    extends salesforceoauthConnectorHttpCallbackAdapter
    implements Capabilities, MetadataAware, MuleContextAware, Initialisable, OAuthManager<salesforceoauthConnectorOAuth2Adapter> , ProcessAdapter<salesforceoauthConnectorOAuth2Adapter>
{

    private static Logger logger = LoggerFactory.getLogger(salesforceoauthConnectorOAuthManager.class);
    private salesforceoauthConnectorOAuth2Adapter defaultUnauthorizedConnector;
    private String apiKey;
    private String apiSecret;
    /**
     * muleContext
     * 
     */
    protected MuleContext muleContext;
    /**
     * Flow Construct
     * 
     */
    protected FlowConstruct flowConstruct;
    private ObjectStore accessTokenObjectStore;
    private String authorizationUrl = null;
    private String accessTokenUrl = null;
    /**
     * Access Token Pool Factory
     * 
     */
    private KeyedPoolableObjectFactory accessTokenPoolFactory;
    /**
     * Access Token Pool
     * 
     */
    private GenericKeyedObjectPool accessTokenPool;
    private final static String MODULE_NAME = "salesforceoauth";
    private final static String MODULE_VERSION = "1.0-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.3.2";
    private final static String DEVKIT_BUILD = "UNNAMED.1377.fd7d4f9";

    /**
     * Retrieves defaultUnauthorizedConnector
     * 
     */
    public salesforceoauthConnectorOAuth2Adapter getDefaultUnauthorizedConnector() {
        return this.defaultUnauthorizedConnector;
    }

    /**
     * Sets apiKey
     * 
     * @param value Value to set
     */
    public void setApiKey(String value) {
        this.apiKey = value;
    }

    /**
     * Retrieves apiKey
     * 
     */
    public String getApiKey() {
        return this.apiKey;
    }

    /**
     * Sets apiSecret
     * 
     * @param value Value to set
     */
    public void setApiSecret(String value) {
        this.apiSecret = value;
    }

    /**
     * Retrieves apiSecret
     * 
     */
    public String getApiSecret() {
        return this.apiSecret;
    }

    /**
     * Retrieves muleContext
     * 
     */
    public MuleContext getMuleContext() {
        return this.muleContext;
    }

    public void setMuleContext(MuleContext muleContext) {
        this.muleContext = muleContext;
        if (defaultUnauthorizedConnector instanceof MuleContextAware) {
            ((MuleContextAware) defaultUnauthorizedConnector).setMuleContext(muleContext);
        }
    }

    /**
     * Retrieves flowConstruct
     * 
     */
    public FlowConstruct getFlowConstruct() {
        return this.flowConstruct;
    }

    public void setFlowConstruct(FlowConstruct flowConstruct) {
        this.flowConstruct = flowConstruct;
        if (defaultUnauthorizedConnector instanceof FlowConstructAware) {
            ((FlowConstructAware) defaultUnauthorizedConnector).setFlowConstruct(flowConstruct);
        }
    }

    /**
     * Retrieves accessTokenObjectStore
     * 
     */
    public ObjectStore getAccessTokenObjectStore() {
        return this.accessTokenObjectStore;
    }

    /**
     * Sets accessTokenObjectStore
     * 
     * @param value Value to set
     */
    public void setAccessTokenObjectStore(ObjectStore value) {
        this.accessTokenObjectStore = value;
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
     * Retrieves accessTokenPoolFactory
     * 
     */
    public KeyedPoolableObjectFactory getAccessTokenPoolFactory() {
        return this.accessTokenPoolFactory;
    }

    public void initialise()
        throws InitialisationException
    {
        GenericKeyedObjectPool.Config config = new GenericKeyedObjectPool.Config();
        config.testOnBorrow = true;
        if (accessTokenObjectStore == null) {
            accessTokenObjectStore = muleContext.getRegistry().lookupObject(MuleProperties.DEFAULT_USER_OBJECT_STORE_NAME);
            if (accessTokenObjectStore == null) {
                throw new InitialisationException(CoreMessages.createStaticMessage("There is no default user object store on this Mule instance."), this);
            }
        }
        accessTokenPoolFactory = new salesforceoauthConnectorOAuthClientFactory(this);
        accessTokenPool = new GenericKeyedObjectPool(accessTokenPoolFactory, config);
        defaultUnauthorizedConnector = new salesforceoauthConnectorOAuth2Adapter();
        if (defaultUnauthorizedConnector instanceof Initialisable) {
            ((Initialisable) defaultUnauthorizedConnector).initialise();
        }
    }

    public void start()
        throws MuleException
    {
        if (defaultUnauthorizedConnector instanceof Startable) {
            ((Startable) defaultUnauthorizedConnector).start();
        }
    }

    public void stop()
        throws MuleException
    {
        if (defaultUnauthorizedConnector instanceof Stoppable) {
            ((Stoppable) defaultUnauthorizedConnector).stop();
        }
    }

    public void dispose() {
        if (defaultUnauthorizedConnector instanceof Disposable) {
            ((Disposable) defaultUnauthorizedConnector).dispose();
        }
    }

    public salesforceoauthConnectorOAuth2Adapter createAccessToken(String verifier)
        throws Exception
    {
        salesforceoauthConnectorOAuth2Adapter connector = new salesforceoauthConnectorOAuth2Adapter();
        connector.setOauthVerifier(verifier);
        connector.setAuthorizationUrl(getAuthorizationUrl());
        connector.setAccessTokenUrl(getAccessTokenUrl());
        connector.setApiKey(getApiKey());
        connector.setApiSecret(getApiSecret());
        if (connector instanceof MuleContextAware) {
            connector.setMuleContext(muleContext);
        }
        if (connector instanceof Initialisable) {
            connector.initialise();
        }
        if (connector instanceof Startable) {
            connector.start();
        }
        return connector;
    }

    public salesforceoauthConnectorOAuth2Adapter acquireAccessToken(String userId)
        throws Exception
    {
        if (logger.isDebugEnabled()) {
            StringBuilder messageStringBuilder = new StringBuilder();
            messageStringBuilder.append("Pool Statistics before acquiring [key ");
            messageStringBuilder.append(userId);
            messageStringBuilder.append("] [active=");
            messageStringBuilder.append(accessTokenPool.getNumActive(userId));
            messageStringBuilder.append("] [idle=");
            messageStringBuilder.append(accessTokenPool.getNumIdle(userId));
            messageStringBuilder.append("]");
            logger.debug(messageStringBuilder.toString());
        }
        salesforceoauthConnectorOAuth2Adapter object = ((salesforceoauthConnectorOAuth2Adapter) accessTokenPool.borrowObject(userId));
        if (logger.isDebugEnabled()) {
            StringBuilder messageStringBuilder = new StringBuilder();
            messageStringBuilder.append("Pool Statistics after acquiring [key ");
            messageStringBuilder.append(userId);
            messageStringBuilder.append("] [active=");
            messageStringBuilder.append(accessTokenPool.getNumActive(userId));
            messageStringBuilder.append("] [idle=");
            messageStringBuilder.append(accessTokenPool.getNumIdle(userId));
            messageStringBuilder.append("]");
            logger.debug(messageStringBuilder.toString());
        }
        return object;
    }

    public void releaseAccessToken(String userId, salesforceoauthConnectorOAuth2Adapter connector)
        throws Exception
    {
        if (logger.isDebugEnabled()) {
            StringBuilder messageStringBuilder = new StringBuilder();
            messageStringBuilder.append("Pool Statistics before releasing [key ");
            messageStringBuilder.append(userId);
            messageStringBuilder.append("] [active=");
            messageStringBuilder.append(accessTokenPool.getNumActive(userId));
            messageStringBuilder.append("] [idle=");
            messageStringBuilder.append(accessTokenPool.getNumIdle(userId));
            messageStringBuilder.append("]");
            logger.debug(messageStringBuilder.toString());
        }
        accessTokenPool.returnObject(userId, connector);
        if (logger.isDebugEnabled()) {
            StringBuilder messageStringBuilder = new StringBuilder();
            messageStringBuilder.append("Pool Statistics after releasing [key ");
            messageStringBuilder.append(userId);
            messageStringBuilder.append("] [active=");
            messageStringBuilder.append(accessTokenPool.getNumActive(userId));
            messageStringBuilder.append("] [idle=");
            messageStringBuilder.append(accessTokenPool.getNumIdle(userId));
            messageStringBuilder.append("]");
            logger.debug(messageStringBuilder.toString());
        }
    }

    public void destroyAccessToken(String userId, salesforceoauthConnectorOAuth2Adapter connector)
        throws Exception
    {
        if (logger.isDebugEnabled()) {
            StringBuilder messageStringBuilder = new StringBuilder();
            messageStringBuilder.append("Pool Statistics before destroying [key ");
            messageStringBuilder.append(userId);
            messageStringBuilder.append("] [active=");
            messageStringBuilder.append(accessTokenPool.getNumActive(userId));
            messageStringBuilder.append("] [idle=");
            messageStringBuilder.append(accessTokenPool.getNumIdle(userId));
            messageStringBuilder.append("]");
            logger.debug(messageStringBuilder.toString());
        }
        accessTokenPool.invalidateObject(userId, connector);
        if (logger.isDebugEnabled()) {
            StringBuilder messageStringBuilder = new StringBuilder();
            messageStringBuilder.append("Pool Statistics after destroying [key ");
            messageStringBuilder.append(userId);
            messageStringBuilder.append("] [active=");
            messageStringBuilder.append(accessTokenPool.getNumActive(userId));
            messageStringBuilder.append("] [idle=");
            messageStringBuilder.append(accessTokenPool.getNumIdle(userId));
            messageStringBuilder.append("]");
            logger.debug(messageStringBuilder.toString());
        }
    }

    /**
     * Returns true if this module implements such capability
     * 
     */
    public boolean isCapableOf(Capability capability) {
        if (capability == Capability.LIFECYCLE_CAPABLE) {
            return true;
        }
        if (capability == Capability.OAUTH2_CAPABLE) {
            return true;
        }
        if (capability == Capability.OAUTH_ACCESS_TOKEN_MANAGEMENT_CAPABLE) {
            return true;
        }
        return false;
    }

    @Override
    public<P >ProcessTemplate<P, salesforceoauthConnectorOAuth2Adapter> getProcessTemplate() {
        return new ManagedAccessTokenProcessTemplate(this, getMuleContext());
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public String getModuleVersion() {
        return MODULE_VERSION;
    }

    public String getDevkitVersion() {
        return DEVKIT_VERSION;
    }

    public String getDevkitBuild() {
        return DEVKIT_BUILD;
    }

    public String authorize(Map<String, String> extraParameters, String authorizationUrl, String redirectUri) {
        StringBuilder urlBuilder = new StringBuilder();
        if (authorizationUrl!= null) {
            urlBuilder.append(authorizationUrl);
        } else {
            urlBuilder.append(this.authorizationUrl);
        }
        urlBuilder.append("?");
        urlBuilder.append("response_type=code&");
        urlBuilder.append("client_id=");
        urlBuilder.append(getApiKey());
        urlBuilder.append("&redirect_uri=");
        urlBuilder.append(redirectUri);
        for (String parameter: extraParameters.keySet()) {
            urlBuilder.append("&");
            urlBuilder.append(parameter);
            urlBuilder.append("=");
            urlBuilder.append(extraParameters.get(parameter));
        }
        logger.debug(("Authorization URL has been generated as follows: " + urlBuilder));
        return urlBuilder.toString();
    }

}
