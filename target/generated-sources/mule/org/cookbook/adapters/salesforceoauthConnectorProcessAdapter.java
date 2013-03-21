
package org.cookbook.adapters;

import javax.annotation.Generated;
import org.cookbook.salesforceoauthConnector;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.process.ProcessAdapter;
import org.mule.api.process.ProcessCallback;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;


/**
 * A <code>salesforceoauthConnectorProcessAdapter</code> is a wrapper around {@link salesforceoauthConnector } that enables custom processing strategies.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-21T11:22:52-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class salesforceoauthConnectorProcessAdapter
    extends salesforceoauthConnectorLifecycleAdapter
    implements ProcessAdapter<salesforceoauthConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, salesforceoauthConnectorCapabilitiesAdapter> getProcessTemplate() {
        final salesforceoauthConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,salesforceoauthConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, salesforceoauthConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, salesforceoauthConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
