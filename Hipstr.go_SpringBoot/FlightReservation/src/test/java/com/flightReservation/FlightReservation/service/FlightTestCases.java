package com.flightReservation.FlightReservation.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.flightReservation.FlightReservationApplication;
import com.flightReservation.dao.FlightReservationDaoImpl;
import com.flightReservation.entities.User;
import com.flightReservation.exception.FlightNotFound;
import com.flightReservation.exception.UserDoesNotFound;
import com.flightReservation.exception.UserNotValid;
import com.flightReservation.service.FlightReservationServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightTestCases {
	
	static org.slf4j.Logger logger=LoggerFactory.getLogger(FlightTestCases.class);
	
	@Autowired
	private FlightReservationServiceImpl flightService;
	
	@BeforeEach
	void setup() {
		logger.info("Test Case Started");
		System.out.println("Test Case Started");
	}
	
	@AfterEach
	void tearDown() {
		logger.info("Test Case Over");
		System.out.println("Test Case Over");
	}
	
	@Test
	@DisplayName("User Login Successful")
	public void loginFirstTest() throws Exception {
		String message = null;
		try {
			if (flightService.login("kri","pass") == true) {
				message = "loggedIn";
			}
		} catch (UserDoesNotFound exp) {
			message = exp.getMessage();
		}
		String expectedMessage = "loggedIn";
		assertEquals(message, expectedMessage);

	}
	
	@Test
	@DisplayName("User Not Found")
	public void loginSecondTest() throws Exception {
		String message = null;
		try {
			if (flightService.login("xyz","pass") == true) {
				message = "loggedIn";
			}
		} catch (UserDoesNotFound exp) {
			message = exp.getMessage();
		}
		String expectedMessage = "Wrong UserId or Password";
		assertEquals(message, expectedMessage);

	}
	
	@Test
	@DisplayName("User registered successfully Found")
	public void registerFirstTest() throws Exception {
		User user=new User("kk1","krishna","agarwal","pass","male",881997626);
		String message = null;
		try {
			if (flightService.addUser(user) == true) {
				message = "Registered successfully";
			}
		} catch (UserNotValid exp) {
			message = exp.getMessage();
		}
		String expectedMessage = "Registered successfully";
		assertEquals(message, expectedMessage);

	}
	
	@Test
	@DisplayName("User Details Not Valid")
	public void registerSecondTest() throws Exception {
		User user=new User("kri","krishna","agarwal","pass","male",881997626);
		String message = null;
		try {
			if (flightService.addUser(user) == true) {
				message = "Registered successfully";
			}
		} catch (UserNotValid exp) {
			message = exp.getMessage();
		}
		String expectedMessage = "User Details Not Valid";
		assertEquals(message, expectedMessage);

	}

	@Test
	@DisplayName("get user by Id")
	public void findUserFirstTest() throws Exception {
		//User user=new User("kri","krishna","agarwal","pass","male",881997626);
		String message = null;
		try {
			if (flightService.findUserById("kri").getUserId() == "kri") {
				message = "successfully recieved";
			}
		} catch (UserNotValid exp) {
			message = exp.getMessage();
		}
		String expectedMessage = "successfully recieved";
		assertEquals(message, expectedMessage);

	}
	
	@Test
	@DisplayName("get user by Id")
	public void findUserSecondTest() throws Exception {
		//User user=new User("kri","krishna","agarwal","pass","male",881997626);
		String message = null;
		try {
			if (flightService.findUserById("xyz").getUserId() == "xyz") {
				message = "successfully recieved";
			}
		} catch (UserDoesNotFound exp) {
			message = exp.getMessage();
		}
		String expectedMessage ="User not found";
		assertEquals(message, expectedMessage);

	}
	
	@Test
	@DisplayName("get flight by Id")
	public void findflightFirstTest() throws Exception {
		//User user=new User("kri","krishna","agarwal","pass","male",881997626);
		String message = null;
		try {
			if (flightService.getFlightById("fly1").getFlightNumber() == "fly1") {
				message = "successfully recieved";
			}
		} catch (FlightNotFound exp) {
			message = exp.getMessage();
		}
		String expectedMessage = "successfully recieved";
		assertEquals(message, expectedMessage);

	}
	
	@Test
	@DisplayName("get flight by Id")
	public void findflightSecondTest() throws Exception {
		//User user=new User("kri","krishna","agarwal","pass","male",881997626);
		String message = null;
		try {
			if (flightService.getFlightById("fly").getFlightNumber() == "fly") {
				message = "successfully recieved";
			}
		} catch (FlightNotFound exp) {
			message = exp.getMessage();
		}
		String expectedMessage = "Flight Not Found";
		assertEquals(message, expectedMessage);

	}
}
