/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.cookbook;

import java.util.HashMap;
import java.util.Map;

import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.oauth.*;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

/**
 * Cloud Connector
 *
 * @author MuleSoft, Inc.
 */

@org.mule.api.annotations.Connector(name="salesforceoauth", schemaVersion="1.0-SNAPSHOT")
@OAuth2(authorizationUrl = "https://login.salesforce.com/services/oauth2/authorize",
        accessTokenUrl = "https://login.salesforce.com/services/oauth2/token")
public class salesforceoauthConnector
{
	/**
     * API key
     */
	@Configurable
	@OAuthConsumerKey
	private String apiKey;
	
	/**
     * API secret
     */
	@Configurable
	@OAuthConsumerSecret
	private String apiSecret;
	
	/**
     * Access token
     */
	@OAuthAccessToken
	private String accessToken;
	
	/**
     * Partner connection
     */
    private PartnerConnection connection;
    
    @OAuthCallbackParameter(expression = "#[json:id]")
    private String userId;
    
    @OAuthAccessTokenIdentifier
    public String getAccessId() { 
    		return userId; 
    }
    
    
    @OAuthPostAuthorization
    public void postAuthorize() throws ConnectionException{
    	 ConnectorConfig config = new ConnectorConfig();
    	 config.setSessionId(accessToken);
    	 this.connection = Connector.newConnection(config);
    }
    
  //Private method to transform an SObject from Salesforce into a Map of names and values of each field with a non-null value.
    private Map<String, Object> sObjectToMap(SObject obj) throws com.sforce.ws.ConnectionException{
    	Map<String, Object> result = new HashMap<String, Object>();
			DescribeSObjectResult desObj = connection.describeSObject(obj.getType());
			Field[] fields = desObj.getFields();
			for(int j=0;j < fields.length; j++){
				if (obj.getField(fields[j].getName()) != null){
					result.put(fields[j].getName(), obj.getField(fields[j].getName()));
				}
			}
			return result;
    }
    
	
	 /**
     * Custom processor
     *
     * {@sample.xml ../../../doc/salesforce-oauth-connector.xml.sample salesforceoauth:retrieve-contact}
     * 
     * @param lastName the last name of the account to look up
     * @return Some string
     * @throws Exception throws a Salesforce WSConnectionException on issues with the connection
	 * 
     */

    @Processor
    @OAuthProtected
    public Map<String, Object> retrieveContact(@Optional @Default("") String lastName) throws Exception
    {
    		if (connection == null){
    			ConnectorConfig config = new ConnectorConfig();
       	 		config.setSessionId(accessToken);
       	 		this.connection = Connector.newConnection(config);
    		}
    	
			QueryResult queryResults;
			if (lastName != null && !lastName.isEmpty()){
				queryResults = connection.query("SELECT Id, FirstName, LastName, Account.Name " +
						"FROM Contact WHERE AccountId != NULL AND lastName == " + lastName + " ORDER BY CreatedDate ASC LIMIT 1");

			} else{
				queryResults = connection.query("SELECT Id, FirstName, LastName, Account.Name " +
						"FROM Contact WHERE AccountId != NULL ORDER BY CreatedDate ASC LIMIT 1");
			}
			SObject[] records = queryResults.getRecords();
			if (records.length > 0){
				return this.sObjectToMap(records[0]);
			} else {
				return null;
			}
    }

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public String getApiSecret() {
		return apiSecret;
	}
	
	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}
	
	public String getAccessToken(){
		return this.accessToken;
	}
	
	public void setUserId(String id){
		this.userId = id;
	}
	
	public String getUserId(){
		return userId;
	}
}
