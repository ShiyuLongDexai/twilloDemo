package com.lendup.myapp.logic;

public class Logic {
	public String helper (String input){
		long in = Long.parseLong(input);
		if(in%3==0&&in%5==0){
			return "Fizz Buzz";
		}
		if(in%3==0){
			return "Fizz";
		}
		if(in%5==0){
			return "Buzz";
		}
		return input;
	}
}
