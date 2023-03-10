package com.loyalty.pkg.config;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.loyalty.pkg.helpers.Token;
import com.loyalty.pkg.models.User;
import com.loyalty.pkg.repository.UserRepository;

@Controller
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/login")
	public ModelAndView getLogin() {
		System.out.println("In Login Page Controller");
		ModelAndView getLoginPage = new ModelAndView("login");

		// set Token String
		String token = Token.generateToken();
	
		// send token to view
		getLoginPage.addObject("token", token);
		getLoginPage.addObject("PageTitle", "Login");
		return getLoginPage;
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password_at") String password_at,
			@RequestParam("_token") String token, Model model, HttpSession session) {

		// TODO: validate input fields / from data:
		if (email.isEmpty() || email == null || password_at.isEmpty() || password_at == null) {
			model.addAttribute("error", "User Name or Password Cannot be Empty");
			return "login";
		}

		// get email from database
		String getDatabaseEmail = userRepository.getUserEmail(email);

		// TODO: check if email exists
		if (getDatabaseEmail != null) {

			// get password in database
			String getDatabasePassword = userRepository.getUserPassword(email);

			// validate password
			if (!BCrypt.checkpw(password_at, getDatabasePassword)) {

				model.addAttribute("error", "Incurrect Username and Password");
				return "login";
			}
			// end of validate password

		} else {
			model.addAttribute("error", "Something went wrong please contact support");
			return "error";
		}

		// end Check if email exists

		// TODO: Check if user Acoount is varified
		int verified = userRepository.isVerified(getDatabaseEmail);
		if (verified != 1) {
			model.addAttribute("error", "This Account is not yet Verified, please Check email and Verify account");
			return "login";
		}

		// TODO: Proced to user log in
		User user = userRepository.getUserDetails(getDatabaseEmail);
		
		// set session attribute
		session.setAttribute("user", user);
		session.setAttribute("token", token);
		session.setAttribute("authenticated", true);
		
		
		// TODO: check if value is not null
		return "redirect:/app/dashbord";
	}

}
