
package org.cookbook.processors;

import javax.annotation.Generated;

@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-25T12:28:48-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public abstract class AbstractConnectedProcessor
    extends AbstractExpressionEvaluator
{

    private Object accessTokenId;
    private String _accessTokenIdType;

    /**
     * Retrieves accessTokenId
     * 
     */
    public Object getAccessTokenId() {
        return this.accessTokenId;
    }

    /**
     * Sets accessTokenId
     * 
     * @param value Value to set
     */
    public void setAccessTokenId(Object value) {
        this.accessTokenId = value;
    }

}
