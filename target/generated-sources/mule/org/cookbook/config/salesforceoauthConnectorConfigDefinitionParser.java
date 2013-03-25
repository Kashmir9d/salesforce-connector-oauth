
package org.cookbook.config;

import javax.annotation.Generated;
import org.cookbook.oauth.salesforceoauthConnectorOAuthManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-25T12:28:48-07:00", comments = "Build UNNAMED.1377.fd7d4f9")
public class salesforceoauthConnectorConfigDefinitionParser
    extends AbstractDefinitionParser
{


    public BeanDefinition parse(Element element, ParserContext parserContext) {
        parseConfigName(element);
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(salesforceoauthConnectorOAuthManager.class.getName());
        builder.setScope(BeanDefinition.SCOPE_SINGLETON);
        setInitMethodIfNeeded(builder, salesforceoauthConnectorOAuthManager.class);
        setDestroyMethodIfNeeded(builder, salesforceoauthConnectorOAuthManager.class);
        parseProperty(builder, element, "apiKey", "apiKey");
        parseProperty(builder, element, "apiSecret", "apiSecret");
        Element httpCallbackConfigElement = DomUtils.getChildElementByTagName(element, "oauth-callback-config");
        if (httpCallbackConfigElement!= null) {
            parseProperty(builder, httpCallbackConfigElement, "domain");
            parseProperty(builder, httpCallbackConfigElement, "localPort");
            parseProperty(builder, httpCallbackConfigElement, "remotePort");
            parseProperty(builder, httpCallbackConfigElement, "async");
            parseProperty(builder, httpCallbackConfigElement, "path");
            if (hasAttribute(httpCallbackConfigElement, "connector-ref")) {
                builder.addPropertyValue("connector", new RuntimeBeanReference(httpCallbackConfigElement.getAttribute("connector-ref")));
            }
        }
        Element oauthStoreConfigElement = DomUtils.getChildElementByTagName(element, "oauth-store-config");
        if (oauthStoreConfigElement!= null) {
            parsePropertyRef(builder, oauthStoreConfigElement, "objectStore-ref", "accessTokenObjectStore");
        }
        parseProperty(builder, element, "authorizationUrl");
        parseProperty(builder, element, "accessTokenUrl");
        BeanDefinition definition = builder.getBeanDefinition();
        setNoRecurseOnDefinition(definition);
        return definition;
    }

}
