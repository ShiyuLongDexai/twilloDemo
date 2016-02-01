package com.lendup.myapp;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
 
import java.util.ArrayList;
import java.util.List;

public class TwTest {
	 // Find your Account Sid and Token at twilio.com/user/account
	  public static final String ACCOUNT_SID = "AC32a3c49700934481addd5ce1659f04d2";
	  public static final String AUTH_TOKEN = "{{ auth_token }}";
	 
	  public static void main(String[] args) throws TwilioRestException {
	    TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
	 
	    // Build a filter for the MessageList
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("Body", "Jenny please?! I love you <3"));
	    params.add(new BasicNameValuePair("To", "+14159352345"));
	    params.add(new BasicNameValuePair("From", "+14158141829"));
	 
	    MessageFactory messageFactory = client.getAccount().getMessageFactory();
	    Message message = messageFactory.create(params);
	    System.out.println(message.getSid());
	  }
}
