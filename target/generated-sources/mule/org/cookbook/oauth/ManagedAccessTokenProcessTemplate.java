
package org.cookbook.oauth;

import javax.annotation.Generated;
import org.cookbook.adapters.salesforceoauthConnectorOAuth2Adapter;
import org.cookbook.process.ProcessCallbackProcessInterceptor;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.oauth.OAuthManager;
import org.mule.api.process.ProcessCallback;
import org.mule.api.process.ProcessInterceptor;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;

@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-25T12:03:30-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class ManagedAccessTokenProcessTemplate<P >implements ProcessTemplate<P, salesforceoauthConnectorOAuth2Adapter>
{

    private final ProcessInterceptor<P, salesforceoauthConnectorOAuth2Adapter> processInterceptor;

    public ManagedAccessTokenProcessTemplate(OAuthManager<salesforceoauthConnectorOAuth2Adapter> oauthManager, MuleContext muleContext) {
        ProcessInterceptor<P, salesforceoauthConnectorOAuth2Adapter> processCallbackProcessInterceptor = new ProcessCallbackProcessInterceptor<P, salesforceoauthConnectorOAuth2Adapter>();
        ProcessInterceptor<P, salesforceoauthConnectorOAuth2Adapter> refreshTokenProcessInterceptor = new RefreshTokenProcessInterceptor<P>(processCallbackProcessInterceptor);
        ProcessInterceptor<P, salesforceoauthConnectorOAuth2Adapter> managedAccessTokenProcessInterceptor = new ManagedAccessTokenProcessInterceptor<P>(refreshTokenProcessInterceptor, oauthManager, muleContext);
        processInterceptor = managedAccessTokenProcessInterceptor;
    }

    public P execute(ProcessCallback<P, salesforceoauthConnectorOAuth2Adapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
        throws Exception
    {
        return processInterceptor.execute(processCallback, null, messageProcessor, event);
    }

    public P execute(ProcessCallback<P, salesforceoauthConnectorOAuth2Adapter> processCallback, Filter filter, MuleMessage message)
        throws Exception
    {
        return processInterceptor.execute(processCallback, null, filter, message);
    }

}
