
package org.cookbook.oauth;

import javax.annotation.Generated;
import org.cookbook.adapters.salesforceoauthConnectorCapabilitiesAdapter;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.oauth.OAuthAdapter;
import org.mule.api.process.ProcessCallback;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;

@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-25T12:28:48-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class OAuthProcessTemplate<P >implements ProcessTemplate<P, salesforceoauthConnectorCapabilitiesAdapter>
{

    private final salesforceoauthConnectorCapabilitiesAdapter object;

    public OAuthProcessTemplate(salesforceoauthConnectorCapabilitiesAdapter object) {
        this.object = object;
    }

    public P execute(ProcessCallback<P, salesforceoauthConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
        throws Exception
    {
        if (processCallback.isProtected()) {
            ((OAuthAdapter) object).hasBeenAuthorized();
        }
        return processCallback.process(object);
    }

    public P execute(ProcessCallback<P, salesforceoauthConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
        throws Exception
    {
        if (processCallback.isProtected()) {
            ((OAuthAdapter) object).hasBeenAuthorized();
        }
        return processCallback.process(object);
    }

}
