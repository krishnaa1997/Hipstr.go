package com.flightReservation.service;

import java.util.List;

import com.flightReservation.entities.BookTicket;
import com.flightReservation.entities.Flights;
import com.flightReservation.entities.Tickets;
import com.flightReservation.entities.User;

public interface IFlightReservationService {
	
	public boolean addUser(User newUser);
	public User findUserById(String userId);
	public Flights getFlightById(String flightNumber);
	public List<Flights> getAllFlights(String source,String destination, String journeyDate);

	public boolean login(String userId, String password);
	
	public List<Tickets> viewBookedFlights(String userId);
	public boolean bookingTicket(BookTicket ticket);
	public User getUserById(String userId);
	public Flights getBookedFlightsId(int ticketNumber);
	public int getTicketId(String userId, String flightId); 
	public boolean cancelTicket(int id);
	public Tickets getTicketById(int id);

}
