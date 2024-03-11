package com.Email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Email.Services.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService service;
	String to= "parihardeepak4162@gmail.com, sdedeepak@gmail.com, infociondeepakparihar@gmail.com";

	@RequestMapping("/home")
	public String welcome()
	{
		service.sendEmail("testing","i am sending mail using webApi",to,
				          "parihardeepak4162@gmail.com");
//		service.sendAttach("testing","i am sending mail using webApi","parihardeepak416@gmail.com",
//				"parihardeepak4162@gmail.com");
		return "success";
	}
}
