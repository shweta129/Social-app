package com.niit.controlles;

import org.springframework.beans.factory.annotation.Autowired;

import com.niit.service.EmailService;

public class RequestController {

	@Autowired
	private EmailService emailService;
}
