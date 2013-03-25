
package org.cookbook.processors;

import javax.annotation.Generated;
import org.cookbook.adapters.salesforceoauthConnectorOAuth2Adapter;
import org.cookbook.oauth.salesforceoauthConnectorOAuthManager;
import org.mule.api.DefaultMuleException;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.UnableToAcquireConnectionException;
import org.mule.api.construct.FlowConstructAware;
import org.mule.api.context.MuleContextAware;
import org.mule.api.processor.MessageProcessor;
import org.mule.config.i18n.CoreMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-25T12:28:48-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class UnauthorizeMessageProcessor
    extends AbstractMessageProcessor<salesforceoauthConnectorOAuthManager>
    implements FlowConstructAware, MuleContextAware, MessageProcessor
{

    private static Logger logger = LoggerFactory.getLogger(UnauthorizeMessageProcessor.class);

    /**
     * Unauthorize the connector
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
        } catch (IllegalAccessException e) {
            throw new DefaultMuleException(CoreMessages.failedToStart("authorize"), e);
        } catch (InstantiationException e) {
            throw new DefaultMuleException(CoreMessages.failedToStart("authorize"), e);
        }
        salesforceoauthConnectorOAuth2Adapter connector = null;
        try {
            String _transformedToken = ((String) evaluateAndTransform(getMuleContext(), event, AbstractConnectedProcessor.class.getDeclaredField("_accessTokenIdType").getGenericType(), null, getAccessTokenId()));
            if (logger.isDebugEnabled()) {
                logger.debug(("Attempting to acquire access token using from store for user "+ _transformedToken.toString()));
            }
            connector = moduleObject.acquireAccessToken(_transformedToken);
            if (connector == null) {
                throw new UnableToAcquireConnectionException();
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug((("Access Token has been acquired for [tokenId="+ connector.getAccessId())+"]"));
                }
                moduleObject.destroyAccessToken(_transformedToken, connector);
                if (logger.isDebugEnabled()) {
                    logger.debug((("Access token for [tokenId="+ connector.getAccessId())+"] has been successfully destroyed"));
                }
            }
        } catch (Exception e) {
            throw new DefaultMuleException(CoreMessages.createStaticMessage("Unable to unauthorize the connector"), e);
        }
        return event;
    }

}
