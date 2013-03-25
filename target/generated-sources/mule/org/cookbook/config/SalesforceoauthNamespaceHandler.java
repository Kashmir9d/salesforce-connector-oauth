
package org.cookbook.config;

import javax.annotation.Generated;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/salesforceoauth</code>.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-25T12:28:48-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class SalesforceoauthNamespaceHandler
    extends NamespaceHandlerSupport
{


    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        registerBeanDefinitionParser("config", new salesforceoauthConnectorConfigDefinitionParser());
        registerBeanDefinitionParser("authorize", new AuthorizeDefinitionParser());
        registerBeanDefinitionParser("unauthorize", new UnauthorizeDefinitionParser());
        registerBeanDefinitionParser("retrieve-contact", new RetrieveContactDefinitionParser());
    }

}
