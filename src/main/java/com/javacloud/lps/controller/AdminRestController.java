package com.javacloud.lps.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacloud.lps.entity.Loan;
import com.javacloud.lps.entity.User;
import com.javacloud.lps.exception.EmailNotFoundException;
import com.javacloud.lps.exception.LoanNotFoundException;
import com.javacloud.lps.exception.UserNotFoundException;
import com.javacloud.lps.response.Response;
import com.javacloud.lps.service.LoanService;
import com.javacloud.lps.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api1")
public class AdminRestController {

	@Autowired
	private LoanService loanService;

	@Autowired
	private UserService userService;

	@GetMapping("/getLoansByNo/{loanNo}")
	public Response<Loan> getLoans(@PathVariable int loanNo) {

		Loan theLoan = loanService.findById(loanNo);

		if (theLoan != null) {
			return new Response<>(false, "Loan found", theLoan);

		} else {
			throw new LoanNotFoundException("Loan not found ");

		}

	}

	@PostMapping("/addLoans")
	public Response<Loan> addLoan(@Valid @RequestBody Loan theLoan) {

		theLoan.setLoanNo(0);
		Loan loan1 = loanService.save(theLoan);
		if (loan1 != null) {
			return new Response<>(false, "Loan Program  added successfully", loan1);
		} else {
			return new Response<>(true, "loan cannot be added", null);
		}

	}

	@PutMapping("/updateLoans")
	public Response<Loan> updateProduct(@Valid @RequestBody Loan theProduct) {

		Loan product1 = loanService.save(theProduct);
		if (product1 != null) {
			return new Response<>(false, "Loan updated successfully", product1);
		} else {
			return new Response<>(true, "updation failed", null);
		}
	}

	@DeleteMapping("/deleteLoans/{loanNo}")
	public Response<Loan> deleteProduct(@PathVariable int loanNo) {

		Loan tempLoan = loanService.findById(loanNo);
		if (tempLoan != null) {
			loanService.deleteById(loanNo);
			return new Response<>(false, "Loan deleted successfully", tempLoan);
		} else {

			throw new LoanNotFoundException("loan not found");
		}

	}

	@GetMapping("/getLoansByPage/{pageNo}/{itemsPerPage}")
	public Page<Loan> getLoans(@PathVariable int pageNo, @PathVariable int itemsPerPage) {
		return loanService.getLoans(pageNo, itemsPerPage);
	}

	@GetMapping("/getSortedLoans/{pageNo}/{itemsPerPage}/{fieldName}")
	public Page<Loan> getSortLoans(@PathVariable int pageNo, @PathVariable int itemsPerPage,
			@PathVariable String fieldName) {
		return loanService.getSortLoans(pageNo, itemsPerPage, fieldName);
	}

	@PostMapping("/addUser")
	public Response<User> addClient(@Valid @RequestBody User theUser) {

		User res = userService.findByEmail(theUser.getEmail());
		User res1 = userService.findByPhone(theUser.getMobileNo());
		User res2 = userService.findByAadhar(theUser.getAdharNo());

		if (res != null) {
			return new Response<User>(true, "This Email already Exist", null);

		} else if (res1 != null) {
			return new Response<User>(true, "This Phone Number already Exist", null);

		} else if (res2 != null) {
			return new Response<User>(true, "This Aadhar Number already Exist", null);

		}

		User user1 = userService.save(theUser);

		if (user1 != null) {

			return new Response<>(false, "User added successfully", user1);

		} else {
			return new Response<>(true, "save failed", null);
		}
	}

	@DeleteMapping("/deleteUser/{userId}")
	public Response<User> deleteClient(@PathVariable int userId) {

		User tempUser = userService.findById(userId);

		if (tempUser != null) {
			userService.deleteById(userId);
			return new Response<>(false, "User Deleted Successfully", tempUser);
		} else {
			throw new UserNotFoundException("record not found");
		}
	}

	@GetMapping("/getUserByPage/{pageNo}/{itemsPerPage}")
	public Page<User> getClients(@PathVariable int pageNo, @PathVariable int itemsPerPage) {
		return userService.getClients(pageNo, itemsPerPage);
	}

	@GetMapping("/sortUser/{pageNo}/{itemsPerPage}/{fieldName}")
	public Page<User> getSortClients(@PathVariable int pageNo, @PathVariable int itemsPerPage,
			@PathVariable String fieldName) {
		return userService.getSortClients(pageNo, itemsPerPage, fieldName);
	}

	@GetMapping("/getCustomer/{pageNo}/{itemsPerPage}")
	public Page<User> getAllCustomer(@PathVariable int pageNo, @PathVariable int itemsPerPage) {
		return userService.findAllCustomer(pageNo, itemsPerPage);
	}

	@GetMapping("/getSortCustomer/{pageNo}/{itemsPerPage}/{fieldName}")
	public Page<User> getAllSortCustomer(@PathVariable int pageNo, @PathVariable int itemsPerPage,
			@PathVariable String fieldName) {
		return userService.findAllSortedCustomer(pageNo, itemsPerPage, fieldName);

	}
	@GetMapping("getUserById/{userId}")
	public Response<User> getClient(@PathVariable int userId) {

		User theUser = userService.findById(userId);

		if (theUser != null) {
			return new Response<>(false, "records found", theUser);
		} else {
			throw new UserNotFoundException("record not found");
		}

	}
	@PutMapping("/changePassword/{email}/{password}")
	public Response<User> changePassword(@PathVariable String email,@PathVariable String password ){
		User user= userService.getByEmail(email);
		if(user!=null) {
			user.setPassword(password);
			userService.save(user);
			return new Response<User>(false,"Password changed successfully",user);
		}
		else {
			return new Response<User>(true,"user not found",null);
		}
	}

//	@PutMapping("/updateUser")
//	public Response<User> updateUser(@Valid @RequestBody User theUser) {
//		
//		User user1 = userService.save1(theUser);
//		if(user1!=null) {
//			return new Response<>(false,"User updated successfully",user1);
//		}
//		else {
//			return new Response<>(true,"updation failed",null);
//		}
//	}
	@PutMapping("/updateUser")
	public Response<User> updateUser( @RequestBody User user) {
	    	
		User user2 = userService.findById(user.getUserId());
		if(user2==null) {
			throw new UserNotFoundException("user not found...!!");
		}
		else {
			user.setPassword(user2.getPassword());
			userService.save1(user);
			return new Response<User>(false,"updated successfully",user);
		}
	}
	
	@PutMapping("/forgotpwd/{email}/{password}")
	public Response<User> forgotPassword(@PathVariable String email,@PathVariable String password , @RequestBody User theUser) {
		
theUser= userService.findByEmail(email);
		
		
		if (theUser!= null) {
			theUser.setPassword(password);
			userService.save(theUser);
			return new Response<>(false, "successfully saved", theUser);
       
		} else {
			throw new EmailNotFoundException("Email does not exist");
		}
	}
	
	}


