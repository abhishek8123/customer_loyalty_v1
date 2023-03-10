package com.loyalty.pkg.controller;

import java.util.Random;
import javax.mail.MessagingException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.loyalty.pkg.helpers.Token;
import com.loyalty.pkg.models.User;
import com.loyalty.pkg.repository.UserRepository;

@Controller
public class RegisterController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/register")
	public ModelAndView getRegistration() {
		ModelAndView getRegisterPage = new ModelAndView("register");
		getRegisterPage.addObject("pageTitle", "Register");
		System.out.println("In Register page Controller");
		return getRegisterPage;
	}

	@PostMapping("/register")
	public ModelAndView register(@Valid @ModelAttribute("registerUser") User user, BindingResult result,
			@RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name,
			@RequestParam("email") String email, @RequestParam("password_at") String password,
			@RequestParam("confirm_password") String confirm_password) throws MessagingException {

		ModelAndView registrationPage = new ModelAndView("register");

		// Check for errors
		if (result.hasErrors() && confirm_password.isEmpty()) {

			registrationPage.addObject("confirm_pass", "The confirm password must be required");
			return registrationPage;
		}

		// TODO: Check for password match
		if (!password.equals(confirm_password)) {
			registrationPage.addObject("passwordMisMatch", "Passwords do not match");
			return registrationPage;
		}

		// TODO: get token Strings
		String token = Token.generateToken();
		// TODO: generaate random code
		Random rand = new Random();
		int bound = 123;
		int code = bound * rand.nextInt(bound);

		// TODO: GET EMAIL HTML BODY:
		String emailBody = com.loyalty.pkg.helpers.HTML.htmlEmailTemplete(token, Integer.toString(code));

		// TODO: Hash password
		String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

		// TODO: register here
		userRepository.registerUser(first_name, last_name, email, hashed_password, token, code);

		// TODO: send mail notification
		com.loyalty.pkg.mailMessenger.MailMessenger.htmlEmailMessenger("abhi19patil91@gmail.com", email,
				"Verify Account", emailBody);

		// TODO: return to register page
		String successMessage = "Account Registered Successfully, Please Check your Email and Verify Account!";
		registrationPage.addObject("success", successMessage);
		return registrationPage;

	}
}
