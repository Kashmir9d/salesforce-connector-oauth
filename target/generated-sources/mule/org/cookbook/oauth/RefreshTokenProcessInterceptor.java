
package org.cookbook.oauth;

import java.util.List;
import javax.annotation.Generated;
import org.cookbook.adapters.salesforceoauthConnectorOAuth2Adapter;
import org.cookbook.processors.AbstractExpressionEvaluator;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.oauth.OAuth2Adapter;
import org.mule.api.process.ProcessCallback;
import org.mule.api.process.ProcessInterceptor;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-25T12:03:30-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class RefreshTokenProcessInterceptor<T >
    extends AbstractExpressionEvaluator
    implements ProcessInterceptor<T, salesforceoauthConnectorOAuth2Adapter>
{

    private static Logger logger = LoggerFactory.getLogger(RefreshTokenProcessInterceptor.class);
    private final ProcessInterceptor<T, salesforceoauthConnectorOAuth2Adapter> next;

    public RefreshTokenProcessInterceptor(ProcessInterceptor<T, salesforceoauthConnectorOAuth2Adapter> next) {
        this.next = next;
    }

    public T execute(ProcessCallback<T, salesforceoauthConnectorOAuth2Adapter> processCallback, salesforceoauthConnectorOAuth2Adapter object, MessageProcessor messageProcessor, MuleEvent event)
        throws Exception
    {
        T result = null;
        Exception cause = null;
        try {
            result = this.next.execute(processCallback, object, messageProcessor, event);
            return result;
        } catch (Exception e) {
            if (processCallback.getManagedExceptions()!= null) {
                for (Class exceptionClass: ((List<Class> ) processCallback.getManagedExceptions())) {
                    if (exceptionClass.isInstance(e)) {
                        if (((OAuth2Adapter) object).getRefreshToken()!= null) {
                            if (logger.isDebugEnabled()) {
                                logger.debug("A managed exception has been thrown. Attempting to refresh access token.");
                            }
                            try {
                                ((OAuth2Adapter) object).refreshAccessToken(((salesforceoauthConnectorOAuth2Adapter) object).getAccessTokenUrl());
                            } catch (Exception newException) {
                                if (logger.isDebugEnabled()) {
                                    logger.debug("Another exception was thrown while attempting to refresh the access token. Throwing original exception back up", newException);
                                }
                                throw e;
                            }
                            result = this.next.execute(processCallback, object, messageProcessor, event);
                            return result;
                        }
                    }
                }
            }
            throw e;
        }
    }

    public T execute(ProcessCallback<T, salesforceoauthConnectorOAuth2Adapter> processCallback, salesforceoauthConnectorOAuth2Adapter object, Filter filter, MuleMessage event)
        throws Exception
    {
        throw new UnsupportedOperationException();
    }

}
