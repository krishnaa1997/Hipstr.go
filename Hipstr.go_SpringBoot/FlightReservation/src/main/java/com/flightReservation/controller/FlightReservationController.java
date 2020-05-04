package com.flightReservation.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightReservation.entities.BookTicket;
import com.flightReservation.entities.Flights;
import com.flightReservation.entities.Tickets;
import com.flightReservation.entities.User;
import com.flightReservation.exception.UserDoesNotFound;
import com.flightReservation.service.FlightReservationServiceImpl;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders="*")
public class FlightReservationController {
	
	org.slf4j.Logger logger=LoggerFactory.getLogger(FlightReservationController.class);
	
	@Autowired
	private FlightReservationServiceImpl flightReservationServiceImpl;
	
	@PostMapping("/addNewUser")
	public boolean addNewUser(@RequestBody User newUser){
		boolean flag=flightReservationServiceImpl.addUser(newUser);
		return flag;
	}
	
	@GetMapping("/login/{name}/{password}")
	public boolean login(@PathVariable("name") String userId, @PathVariable("password") String password)
	{
		logger.trace("login execution");
		boolean id=flightReservationServiceImpl.login(userId,password);
		//return new ResponseEntity<User>(user,HttpStatus.OK);
		return id;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") String userId)
	{
		User user=flightReservationServiceImpl.getUserById(userId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("/flights/{flightNumber}")
	public ResponseEntity<Flights> getFlightById(@PathVariable("flightNumber") String flightNumber){
		Flights flight=flightReservationServiceImpl.getFlightById(flightNumber);
		return new ResponseEntity<Flights>(flight,HttpStatus.OK);
	}
	
	@GetMapping("/flights/{source}/{destination}/{date}")
	public ResponseEntity<List<Flights>> getAllFlights(@PathVariable("source") String source,@PathVariable("destination") String destination, @PathVariable("date") String journeyDate){
		
			List<Flights> flights=flightReservationServiceImpl.getAllFlights(source,destination,journeyDate);
			return new ResponseEntity<List<Flights>>(flights,HttpStatus.OK);
	}
	
	@GetMapping("/bookedFlights/{userId}")
	public ResponseEntity<List<Tickets>> viewBookedFlights(@PathVariable("userId") String userId)
	{
		List<Tickets> tickets = flightReservationServiceImpl.viewBookedFlights(userId);
		return new ResponseEntity<List<Tickets>>(tickets, HttpStatus.OK);
	}
	
	@GetMapping("/ticketDetails/{id}")
	public ResponseEntity<Tickets> getTicketById(@PathVariable("id") int id)
	{
		Tickets ticket=flightReservationServiceImpl.getTicketById(id);
		return new ResponseEntity<Tickets>(ticket,HttpStatus.OK);
	}
	
	@PostMapping("/bookingTicket")
	public boolean bookingTicket(@RequestBody BookTicket ticket)
	{
		logger.trace(ticket.getPassengerName());
		return flightReservationServiceImpl.bookingTicket(ticket);
	}
	
	@DeleteMapping("/cancelTicket/{id}")
	public boolean cancelTicket(@PathVariable("id") int id)
	{
		return flightReservationServiceImpl.cancelTicket(id);
	}
	
	@GetMapping("/{userId}/{flightId}")
	public int getTicketId(@PathVariable("userId") String userId, @PathVariable("flightId") String flightId)
	{
		//System.out.println(userId);
		return flightReservationServiceImpl.getTicketId(userId,flightId);
	}
	
	@GetMapping("/tickets/{ticketNumber}")
	public ResponseEntity<Flights> getBookedFlightsId(@PathVariable("ticketNumber") int ticketNumber)
	{
		Flights flight = flightReservationServiceImpl.getBookedFlightsId(ticketNumber);
		return new ResponseEntity<Flights>(flight, HttpStatus.OK);
	}
	
	
	

}
