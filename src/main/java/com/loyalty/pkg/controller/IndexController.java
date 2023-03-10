package com.loyalty.pkg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.loyalty.pkg.repository.UserRepository;

@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public ModelAndView getIndex() {
		ModelAndView getIndexPage = new ModelAndView("index");
		getIndexPage.addObject("pageTitle", "Home");
		System.out.println("In index page Controller");
		return getIndexPage;
	}

	@GetMapping("/dashbord")
	public ModelAndView getDashbord() {
		ModelAndView getDashbordPage = new ModelAndView("dashbord");
		getDashbordPage.addObject("pageTitle", "Dashbord");
		System.out.println("In Dashbord page Controller");
		return getDashbordPage;
	}
	
	@GetMapping("/error")
	public ModelAndView getErrors() {
		ModelAndView getErrorPage = new ModelAndView("error");
		getErrorPage.addObject("pageTitle", "Errors");
		System.out.println("in Error page Controller");
		return getErrorPage;
	}
	

	@GetMapping("/verify")
	public ModelAndView getVerify(@RequestParam("token") String token, @RequestParam("code") String code) {

		// set View
		ModelAndView getVerifyPage;

		// Get Token In Database:
		String dbToken = userRepository.checkToken(token);

		// Check If Token Is Valid:
		if (dbToken == null) {
			getVerifyPage = new ModelAndView("error");
			getVerifyPage.addObject("error", "This Session Has Expired");
			return getVerifyPage;
		}
		// End Of Check If Token Is Valid.

		// Update and Verify Account:
		userRepository.verifyAccount(token, code);

		getVerifyPage = new ModelAndView("login");

		getVerifyPage.addObject("success", "Account varified successfully, Please proced to Log In");

		System.out.println("In verify Account Controller");
		return getVerifyPage;
	}

}
