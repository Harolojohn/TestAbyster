package com.Api_Parking.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.util.UUID;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.Api_Parking.Model.RequestToPayObjectSuccess;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class MtnService {
	
	
	public String GenrateReferenceId() {
		
		String referenceId = UUID.randomUUID().toString();
		
		return referenceId;
	}
	
	//API USER
	
	public void  Api_user_post(String referenceId,String subscriptionKey){
		
		System.out.println("\n\nAPI USER");
		
	
		
		
		 System.out.println("\nReference ID: "+referenceId);
		 System.out.println("\nSuscriptionKey: "+subscriptionKey);
		
		//POST
		
		HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://ericssondeveloperapi.azure-api.net/v1_0/apiuser");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
           
            request.setHeader("X-Reference-Id", referenceId);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);


            // Request body
            StringEntity reqEntity = new StringEntity("{\n"
            		+ "  \"providerCallbackHost\": \"string\"\n"
            		+ "}");
            
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            
            System.out.println("\n"+response);
                    

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
		       
	   
	}
	
	//API KEY
	
	public  String  Api_key_post(String referenceId, String subscriptionKey){
			
		
		HttpClient httpclient = HttpClients.createDefault();

		try
        {
            URIBuilder builder = new URIBuilder("https://ericssondeveloperapi.azure-api.net/v1_0/apiuser/"+referenceId+"/apikey");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);


            // Request body
            StringEntity reqEntity = new StringEntity("");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            
            HttpEntity entity = response.getEntity();
            
             String Apikey = EntityUtils.toString(entity).substring(11,43);
             
            
            if (Apikey != null) {
             
            	 return Apikey;
               
           }
            else {
            	//pas de Api key
            	
            	return null;
            	
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            
            return null;
            
        }

	}
	
	//TOKEN
	
		public  String post_token(String autorisation, String subscriptionKey){
			
			System.out.println("\n\nTOKEN");
			
			 HttpClient httpclient = HttpClients.createDefault();

		        try
		        {
		            URIBuilder builder = new URIBuilder("https://ericssondeveloperapi.azure-api.net/collection/token/");

		            URI uri = builder.build();
		            HttpPost request = new HttpPost(uri);
		            request.setHeader("Authorization","Basic "+autorisation);
		            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);


		            // Request body
		            StringEntity reqEntity = new StringEntity("");
		            request.setEntity(reqEntity);

		            HttpResponse response = httpclient.execute(request);
		            HttpEntity entity = response.getEntity();
		            
		            System.out.println("Response: "+response);
		            
		            String token = EntityUtils.toString(entity).substring(17,580);
		            
		           

		            if (token != null) 
		            {
		                return token;
		            }
		            else {
		            	return null;
		            }
		        }
		        catch (Exception e)
		        {
		            System.out.println(e.getMessage());
		            
		            return null;
			
			
		}
		        
		}
		
		
		// Request to PAY 	DEMANDE DE PAIEMENT
		
		public  void requestToPay(String autorisation_token,String referenceId,String subscriptionKey,Integer montant_a_debiter,String numero_payeur,String numero_dest){
			
			System.out.println("\n\nRequest to PAY");
			
			 HttpClient httpclient = HttpClients.createDefault();

		        try
		        {
		            URIBuilder builder = new URIBuilder("https://ericssondeveloperapi.azure-api.net/collection/v1_0/requesttopay");


		            URI uri = builder.build();
		            HttpPost request = new HttpPost(uri);
		            request.setHeader("Authorization", autorisation_token);
		          //  request.setHeader("X-Callback-Url", "");
		            request.setHeader("X-Reference-Id", referenceId);
		            request.setHeader("X-Target-Environment", "sandbox");
		            request.setHeader("Content-Type", "application/json");
		            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

		           
		            // Request body
		            StringEntity reqEntity = new StringEntity("{\n"
		            		+ "  \"amount\":"+montant_a_debiter+",\n"
		            		+ "      \"currency\": \"EUR\",\n"
		            		+ "  \"externalId\": "+numero_dest+",\n"
		            		+ "  \"payer\": {\n"
		            		+ "    \"partyIdType\": \"MSISDN\",\n"
		            		+ "    \"partyId\": "+numero_payeur+"\n"
		            		+ "  },\n"
		            		+ "  \"payerMessage\": \"strRHRFHing\",\n"
		            		+ "  \"payeeNote\": \"FGHGFH\"\n"
		            		+ "}");
		            
		            request.setEntity(reqEntity);

		            HttpResponse response = httpclient.execute(request);
		            HttpEntity entity = response.getEntity();
		            
		            System.out.println("\n"+response);

		            if (entity != null) 
		            {
		                System.out.println(EntityUtils.toString(entity));
		            }
		        }
		        catch (Exception e)
		        {
		            System.out.println(e.getMessage());
		        }
			
		}
		
		public  RequestToPayObjectSuccess requestToPayStatus(String autorisation_token,String referenceId,String subscriptionKey){
			
			System.out.println("\n\nREQUEST TO PAY  STATUS");
			
			HttpClient httpclient = HttpClients.createDefault();

	        try
	        {
	            URIBuilder builder = new URIBuilder("https://ericssondeveloperapi.azure-api.net/collection/v1_0/requesttopay/"+referenceId);


	            URI uri = builder.build();
	            HttpGet request = new HttpGet(uri);
	            request.setHeader("Authorization", autorisation_token);
	            request.setHeader("X-Target-Environment", "sandbox");
	            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);


	            // Request body
	            StringEntity reqEntity = new StringEntity("");
	            //request.setEntity(reqEntity);

	            HttpResponse response = httpclient.execute(request);
	          HttpEntity entity = response.getEntity();
	          
	          RequestToPayObjectSuccess infos_transactions =new  RequestToPayObjectSuccess();
	        		  
	           if (entity != null) 
	            {
	                
	                GsonBuilder builder1 = new GsonBuilder();
		            builder1.setPrettyPrinting();
		            Gson gson = builder1.create();
		            
		             infos_transactions = gson.fromJson(EntityUtils.toString(entity),RequestToPayObjectSuccess.class);
	                
	           }
	          
	            
	            return infos_transactions;
	        }
	        catch (Exception e)
	        {
	            System.out.println(e.getMessage());
	            
	            return null;
	        }
			
		}
		
		
		
	
}
