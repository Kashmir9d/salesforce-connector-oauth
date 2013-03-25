
package org.cookbook.oauth;

import javax.annotation.Generated;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.cookbook.adapters.salesforceoauthConnectorOAuth2Adapter;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.api.store.ObjectStoreException;

@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-25T02:10:12-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class salesforceoauthConnectorOAuthClientFactory implements KeyedPoolableObjectFactory
{

    private salesforceoauthConnectorOAuthManager oauthManager;

    public salesforceoauthConnectorOAuthClientFactory(salesforceoauthConnectorOAuthManager oauthManager) {
        this.oauthManager = oauthManager;
    }

    public Object makeObject(Object key)
        throws Exception
    {
        if (!(key instanceof String)) {
            throw new RuntimeException("Invalid key type");
        }
        salesforceoauthConnectorOAuthState state = null;
        if (!oauthManager.getAccessTokenObjectStore().contains(((String) key))) {
            throw new RuntimeException((("There is no access token stored under the key "+((String) key))+". You need to call the <authorize> message processor. The key will be given to you via a flow variable after the OAuth dance is completed. You can extract it using flowVars['tokenId']."));
        }
        state = ((salesforceoauthConnectorOAuthState) oauthManager.getAccessTokenObjectStore().retrieve(((String) key)));
        salesforceoauthConnectorOAuth2Adapter connector = new salesforceoauthConnectorOAuth2Adapter();
        connector.setApiKey(oauthManager.getApiKey());
        connector.setApiSecret(oauthManager.getApiSecret());
        connector.setAccessToken(state.getAccessToken());
        connector.setAuthorizationUrl(state.getAuthorizationUrl());
        connector.setAccessTokenUrl(state.getAccessTokenUrl());
        connector.setRefreshToken(state.getRefreshToken());
        connector.setUserId(state.getUserId());
        if (connector instanceof Initialisable) {
            ((Initialisable) connector).initialise();
        }
        if (connector instanceof MuleContextAware) {
            ((MuleContextAware) connector).setMuleContext(oauthManager.getMuleContext());
        }
        if (connector instanceof Startable) {
            ((Startable) connector).start();
        }
        connector.postAuthorize();
        return connector;
    }

    public void destroyObject(Object key, Object obj)
        throws Exception
    {
        if (!(key instanceof String)) {
            throw new RuntimeException("Invalid key type");
        }
        if (!(obj instanceof salesforceoauthConnectorOAuth2Adapter)) {
            throw new RuntimeException("Invalid connector type");
        }
        try {
        } catch (Exception e) {
            throw e;
        } finally {
            if (((salesforceoauthConnectorOAuth2Adapter) obj) instanceof Stoppable) {
                ((Stoppable) obj).stop();
            }
            if (((salesforceoauthConnectorOAuth2Adapter) obj) instanceof Disposable) {
                ((Disposable) obj).dispose();
            }
        }
    }

    public boolean validateObject(Object key, Object obj) {
        if (!(key instanceof String)) {
            throw new RuntimeException("Invalid key type");
        }
        if (!(obj instanceof salesforceoauthConnectorOAuth2Adapter)) {
            throw new RuntimeException("Invalid connector type");
        }
        salesforceoauthConnectorOAuthState state = null;
        try {
            if (!oauthManager.getAccessTokenObjectStore().contains(((String) key))) {
                return false;
            }
            state = ((salesforceoauthConnectorOAuthState) oauthManager.getAccessTokenObjectStore().retrieve(((String) key)));
            if (((salesforceoauthConnectorOAuth2Adapter) obj).getAccessToken() == null) {
                return false;
            }
            if (!((salesforceoauthConnectorOAuth2Adapter) obj).getAccessToken().equals(state.getAccessToken())) {
                return false;
            }
            if ((((salesforceoauthConnectorOAuth2Adapter) obj).getRefreshToken() == null)&&(state.getRefreshToken()!= null)) {
                return false;
            }
            if ((((salesforceoauthConnectorOAuth2Adapter) obj).getRefreshToken()!= null)&&(!((salesforceoauthConnectorOAuth2Adapter) obj).getRefreshToken().equals(state.getRefreshToken()))) {
                return false;
            }
        } catch (ObjectStoreException _x) {
            return false;
        }
        return true;
    }

    public void activateObject(Object key, Object obj)
        throws Exception
    {
    }

    public void passivateObject(Object key, Object obj)
        throws Exception
    {
        if (!(key instanceof String)) {
            throw new RuntimeException("Invalid key type");
        }
        if (!(obj instanceof salesforceoauthConnectorOAuth2Adapter)) {
            throw new RuntimeException("Invalid connector type");
        }
        salesforceoauthConnectorOAuthState state = null;
        if (oauthManager.getAccessTokenObjectStore().contains(((String) key))) {
            state = ((salesforceoauthConnectorOAuthState) oauthManager.getAccessTokenObjectStore().retrieve(((String) key)));
            oauthManager.getAccessTokenObjectStore().remove(((String) key));
        }
        if (state == null) {
            state = new salesforceoauthConnectorOAuthState();
        }
        state.setAccessToken(((salesforceoauthConnectorOAuth2Adapter) obj).getAccessToken());
        state.setAccessTokenUrl(((salesforceoauthConnectorOAuth2Adapter) obj).getAccessTokenUrl());
        state.setAuthorizationUrl(((salesforceoauthConnectorOAuth2Adapter) obj).getAuthorizationUrl());
        state.setRefreshToken(((salesforceoauthConnectorOAuth2Adapter) obj).getRefreshToken());
        state.setUserId(((salesforceoauthConnectorOAuth2Adapter) obj).getUserId());
        oauthManager.getAccessTokenObjectStore().store(((String) key), state);
    }

}
