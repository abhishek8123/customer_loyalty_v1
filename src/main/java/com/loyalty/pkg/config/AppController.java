package com.loyalty.pkg.config;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.loyalty.pkg.models.Account;
import com.loyalty.pkg.models.User;
import com.loyalty.pkg.repository.AccountRepository;

@Controller
@RequestMapping("/app")
public class AppController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping("/dashbord")
	public ModelAndView getDashbord(HttpSession session) {
		ModelAndView getDashbordPage = new ModelAndView("dashbord");
		
		
		// get the details of logged in user
//		User user = (User) session.getAttribute("user");
		
		// get the accounts of logged in user
//		List<Account> getUserAccounts = accountRepository.getUserAccountsById(user.getUser_id());
		
		// get balance 
//		BigDecimal totalAccountBalance = accountRepository.getTotalBalance(user.getUser_id());
		
		// set objects
//		getDashbordPage.addObject("userAccounts", getUserAccounts);
//		getDashbordPage.addObject("totalalance", totalAccountBalance);
		
		return getDashbordPage;
	}

}
