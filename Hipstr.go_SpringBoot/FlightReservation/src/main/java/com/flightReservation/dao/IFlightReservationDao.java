package com.flightReservation.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import com.flightReservation.entities.BookTicket;
import com.flightReservation.entities.Flights;
import com.flightReservation.entities.Tickets;
import com.flightReservation.entities.User;

public interface IFlightReservationDao {
	
	public boolean addUser(User newUser);
	public User findUserById(String userId);
	public Flights getFlightById(String fId);
	public List<Flights> getAllFlights(String source,String destination, String journeyDate);
	public boolean login(String userId, String password);
	public List<Tickets> viewBookedFlights(String userId);
	public boolean bookingTicket(BookTicket ticket);
	public User getUserById(String userId);
	public Flights getBookedFlightsId(int ticketNumber);
	public int getTicketId(String userId, String flightNumber);
	public boolean cancelTicket(int ticketNumber);
	public Tickets getTicketById(int id);

}
