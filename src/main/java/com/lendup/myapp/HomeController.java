package com.lendup.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.io.File;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lendup.myapp.logic.Logic;
import com.lendup.myapp.model.PhoneCall;
import com.lendup.myapp.model.Response;
import com.lendup.myapp.service.CallService;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sdk.resource.list.CallList;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Resource
	private CallService callService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	public static final String ACCOUNT_SID = "ACcfc433f76e27de91f97bdee94e585e39";
	  public static final String AUTH_TOKEN = "d4bedacfa0f765d5bc71a7d80c6f8bf5";
	  private PhoneCall p;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws TwilioRestException 
	 * @throws InterruptedException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, 
			@RequestParam(value = "digits", required = false) String digits,
			@RequestParam(value = "sleep", required = false) String sleep,
			Model model) throws TwilioRestException, NumberFormatException, InterruptedException  {

		logger.info("Welcome home! The client locale is {}.", locale);
		p = new PhoneCall();
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN); 
		 
		ArrayList<PhoneCall> calls = (ArrayList<PhoneCall>) callService.getPhoneCalls();
		model.addAttribute("calls", calls);
				
		try{
		if(digits!=null){
			//Build the parameters 
			if(sleep !=null){
				Thread.sleep(Integer.parseInt(sleep)*1000);
			}
			List<NameValuePair> params = new ArrayList<NameValuePair>(); 
			params.add(new BasicNameValuePair("To", digits)); 
			params.add(new BasicNameValuePair("From", "+16177516337")); 
			params.add(new BasicNameValuePair("Url", "https://s3-us-west-2.amazonaws.com/shiyusdemos/in.xml"));  
			params.add(new BasicNameValuePair("Method", "GET"));  
			params.add(new BasicNameValuePair("FallbackMethod", "GET"));  
			params.add(new BasicNameValuePair("StatusCallbackMethod", "GET"));    
			params.add(new BasicNameValuePair("Record", "false")); 
		 
			CallFactory callFactory = client.getAccount().getCallFactory(); 
			Call call = callFactory.create(params); 
			
			p.setCalltime(new Date(System.currentTimeMillis()));
			p.setInput("1234");
			p.setPhonenumber(digits);
			p.setSleepTime(sleep);
			
			
			System.out.println(call.getSid()); 
			    //Build the parameters 
//			   List<NameValuePair> parsams = new ArrayList<NameValuePair>(); 
//			   params.add(new BasicNameValuePair("To", "+18572721118")); 
//			   params.add(new BasicNameValuePair("From", "+16177516337")); 
//			   params.add(new BasicNameValuePair("Body", "Hey Jenny! Good luck on the bar exam!")); 
//			   params.add(new BasicNameValuePair("MediaUrl", "http://farm2.static.flickr.com/1075/1404618563_3ed9a44a3a.jpg"));  
//			 
//			   MessageFactory messageFactory = client.getAccount().getMessageFactory(); 
//			   Message message = messageFactory.create(params); 
//			   System.out.println(message.getSid()); 
		}
		} catch (Exception e){
			if(e instanceof TwilioRestException){
				System.err.println("TwilioRestException thrown  :" + e);
			} else if (e instanceof NumberFormatException){
				System.err.println("NumberFormatException thrown  :" + e);
			} else if (e instanceof InterruptedException){
				System.err.println("InterruptedException thrown  :" + e);
			} else {
				System.err.println("Exception thrown  :" + e);
			}
		}
		// 
		
		return "home";
	}

	
	@RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody Response getCoffeeInXML(@PathVariable String name) {

		Response response = new Response();
		response.setSay(name);
		
		return response;

	}
	
	@RequestMapping(value = "/tw/intro",
			headers={ "Accept=application/xml", "X-Requested-With=XMLHttpRequest" }, produces="application/xml")
	public @ResponseBody Document intro(Locale locale, Model model) throws ParserConfigurationException  {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("company");
		doc.appendChild(rootElement);

		// staff elements
		Element staff = doc.createElement("Staff");
		rootElement.appendChild(staff);
		logger.info("Welcome home! The client locale is {}.", locale);
		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate);
		
		return doc;
	}
	
	@RequestMapping(value = "/callAgain", method = RequestMethod.GET)
	public String callAgain(Locale locale,
			@RequestParam(value = "number", required = false) String number,
			@RequestParam(value = "input", required = false) String input,
			Model model) throws TwilioRestException  {
		
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN); 
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("To", number)); 
		params.add(new BasicNameValuePair("From", "+16177516337")); 
		params.add(new BasicNameValuePair("Url", "http://24.5.178.236:8080/myapp/tw/num?Digits="+input));  
		params.add(new BasicNameValuePair("Method", "GET"));  
		params.add(new BasicNameValuePair("FallbackMethod", "GET"));  
		params.add(new BasicNameValuePair("StatusCallbackMethod", "GET"));    
		params.add(new BasicNameValuePair("Record", "false")); 
	 
		CallFactory callFactory = client.getAccount().getCallFactory(); 
		Call call = callFactory.create(params); 
		
		
		
		return "home";
	}
	
	@RequestMapping(value = "/tw/num", method = RequestMethod.GET, headers = {"X-Twilio-Signature"})
	public @ResponseBody Response num(Locale locale,
			@RequestParam(value = "Digits", required = false) String digits,
			Model model)  {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Logic l = new Logic();
		Response response = new Response();
		response.setSay("Your input is " +digits+", you output is "+l.helper(digits));
		
		return response;
	}
	
	@RequestMapping(value = "/tw/num", method = RequestMethod.POST, headers = {"X-Twilio-Signature"})
	public @ResponseBody Response num2(Locale locale,
			@RequestParam(value = "Digits", required = false) String digits,
			Model model)  {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Logic l = new Logic();
		Response response = new Response();
		response.setSay("Your input is " +digits+", you output is "+l.helper(digits));
		p.setInput(digits);
		callService.addCall(p);
		return response;
	}
	
//	@RequestMapping(value = "/tw/out", method = RequestMethod.GET)
//	public  String num(Locale locale,
//			Model model)  {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		 // Find your Account Sid and Token at twilio.com/user/account
//		  public static final String ACCOUNT_SID = "ACcfc433f76e27de91f97bdee94e585e39";
//		  public static final String AUTH_TOKEN = "{{ auth_token }}";
//		 
//		  public static void main(String[] args) throws TwilioRestException {
//		    TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
//		 
//		    // Build a filter for the CallList
//		    List<NameValuePair> params = new ArrayList<NameValuePair>();
//		    params.add(new BasicNameValuePair("Url", "http://demo.twilio.com/docs/voice.xml"));
//		    params.add(new BasicNameValuePair("To", "+14155551212"));
//		    params.add(new BasicNameValuePair("From", "+14158675309"));
//		     
//		     
//		    CallFactory callFactory = client.getAccount().getCallFactory();
//		    Call call = callFactory.create(params);
//		    System.out.println(call.getSid());
//		
//		return out;
//		
//	}
}
