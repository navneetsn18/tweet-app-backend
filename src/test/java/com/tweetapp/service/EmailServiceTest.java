package com.tweetapp.service;

import static org.assertj.core.api.Assertions.fail;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;

public class EmailServiceTest {

	@Test
	public void testSendMail() {
		EmailService emailService = new EmailService();
		try {
			emailService.sendNewPasswordEmail("navneetsn18", "password", "testing.for.nsn@gmail.com");
		} catch (MessagingException e) {
			fail("Should Not Come Here");
		}
	}
}
