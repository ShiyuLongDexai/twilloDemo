package com.lendup.myapp.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")
public class Response {
	 String Say;
	 
	 @XmlElement(name = "Say")
	public String getSay() {
		return Say;
	}

	public void setSay(String say) {
		Say = say;
	}
	 
}
