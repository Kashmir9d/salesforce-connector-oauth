
package org.cookbook.adapters;

import javax.annotation.Generated;
import org.cookbook.salesforceoauthConnector;
import org.mule.api.Capabilities;
import org.mule.api.Capability;


/**
 * A <code>salesforceoauthConnectorCapabilitiesAdapter</code> is a wrapper around {@link salesforceoauthConnector } that implements {@link org.mule.api.Capabilities} interface.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-21T11:22:52-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class salesforceoauthConnectorCapabilitiesAdapter
    extends salesforceoauthConnector
    implements Capabilities
{


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
        return false;
    }

}
