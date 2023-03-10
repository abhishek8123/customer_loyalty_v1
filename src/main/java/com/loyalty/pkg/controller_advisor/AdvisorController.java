package com.loyalty.pkg.controller_advisor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.loyalty.pkg.models.User;

@ControllerAdvice
public class AdvisorController {

	@ModelAttribute("registerUser")
	public User getUsertDefaults() {

		return new User();

	}
}
