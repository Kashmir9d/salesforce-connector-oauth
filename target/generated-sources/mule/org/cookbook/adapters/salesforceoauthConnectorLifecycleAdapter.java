
package org.cookbook.adapters;

import javax.annotation.Generated;
import org.cookbook.salesforceoauthConnector;
import org.mule.api.MuleException;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.config.MuleManifest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A <code>salesforceoauthConnectorLifecycleAdapter</code> is a wrapper around {@link salesforceoauthConnector } that adds lifecycle methods to the pojo.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-25T12:28:48-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class salesforceoauthConnectorLifecycleAdapter
    extends salesforceoauthConnectorMetadataAdapater
    implements Disposable, Initialisable, Startable, Stoppable
{


    @Override
    public void start()
        throws MuleException
    {
    }

    @Override
    public void stop()
        throws MuleException
    {
    }

    @Override
    public void initialise()
        throws InitialisationException
    {
        Logger log = LoggerFactory.getLogger(salesforceoauthConnectorLifecycleAdapter.class);
        String runtimeVersion = MuleManifest.getProductVersion();
        if (runtimeVersion.equals("Unknown")) {
            log.warn("Unknown Mule runtime version. This module may not work properly!");
        } else {
            String[] expectedMinVersion = "3.2".split("\\.");
            if (runtimeVersion.contains("-")) {
                runtimeVersion = runtimeVersion.split("-")[ 0 ];
            }
            String[] currentRuntimeVersion = runtimeVersion.split("\\.");
            for (int i = 0; (i<expectedMinVersion.length); i ++) {
                try {
                    if (Integer.parseInt(currentRuntimeVersion[i])<Integer.parseInt(expectedMinVersion[i])) {
                        throw new RuntimeException("This module is only valid for Mule 3.2");
                    }
                } catch (NumberFormatException nfe) {
                    log.warn("Error parsing Mule version, cannot validate current Mule version");
                } catch (ArrayIndexOutOfBoundsException iobe) {
                    log.warn("Error parsing Mule version, cannot validate current Mule version");
                }
            }
        }
    }

    @Override
    public void dispose() {
    }

}
