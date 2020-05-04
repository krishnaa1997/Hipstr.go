package com.flightReservation.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightReservation.dao.FlightReservationDaoImpl;
import com.flightReservation.entities.BookTicket;
import com.flightReservation.entities.Flights;
import com.flightReservation.entities.Tickets;
import com.flightReservation.entities.User;
import com.flightReservation.exception.FlightNotFound;
import com.flightReservation.exception.TicketNotFound;
import com.flightReservation.exception.UserDoesNotFound;
import com.flightReservation.exception.UserNotValid;

@Service
@Transactional
public class FlightReservationServiceImpl implements IFlightReservationService {
	org.slf4j.Logger logger=LoggerFactory.getLogger(FlightReservationServiceImpl.class);

	@Autowired
	private FlightReservationDaoImpl flightReservationDaoImpl;
	
	/*Method use to add user in the database is the userId in the new user is 
	 * previously exist then function will throw an error or else it will return true*/
	@Override
	public boolean addUser(User newUser) {
		boolean flag= flightReservationDaoImpl.addUser(newUser);
		if(flag==false)
		{
			throw new UserNotValid("User Details Not Valid");
		}
		return flag;
	}
	@Override
	public User findUserById(String userId) {
		User user= flightReservationDaoImpl.findUserById(userId);
		if(user==null)
		{
			throw new UserDoesNotFound("User not found");
		}
		return user;
	}
	@Override
	public Flights getFlightById(String flightNumber) {
		Flights flight= flightReservationDaoImpl.getFlightById(flightNumber);
		if(flight==null)
		{
			throw new FlightNotFound("Flight Not Found");
		}
		return flight;
	}
	@Override
	public List<Flights> getAllFlights(String source,String destination, String journeyDate){
		List<Flights> flights= flightReservationDaoImpl.getAllFlights(source,destination, journeyDate);
		if(flights.size()==0)
		{
			throw new FlightNotFound("No flight are available based on your search prefrence");
		}
		return flights;
	}
	@Override
	public boolean login(String userId, String password) {
		// TODO Auto-generated method stub
		
		boolean flag= flightReservationDaoImpl.login(userId,password);
		if(flag==false)
		{
			throw new UserDoesNotFound("Wrong UserId or Password");
		}
		return flag;
	}
	@Override
	public List<Tickets> viewBookedFlights(String userId)
	{
		List<Tickets> tickets= flightReservationDaoImpl.viewBookedFlights(userId);
		if(tickets.size()==0)
		{
			throw new TicketNotFound("No previous Bookings to show");
		}
		return tickets;
	}
	@Override
	public boolean bookingTicket(BookTicket ticket)
	{
		logger.trace(ticket.getUserId());
		boolean flag= flightReservationDaoImpl.bookingTicket(ticket);
		if(flag==false)
		{
			throw new FlightNotFound("Seats not available");
		}
		return flag;
	}
	@Override
	public User getUserById(String userId)
	{
		User user= flightReservationDaoImpl.getUserById(userId);
		if(user==null)
		{
			throw new UserDoesNotFound("User not found");
		}
		return user;
	}
	@Override
	public Flights getBookedFlightsId(int ticketNumber) {
		// TODO Auto-generated method stub
		Flights flight= flightReservationDaoImpl.getBookedFlightsId(ticketNumber);
		if(flight==null)
		{
			throw new FlightNotFound("No bookings to show");
		}
		return flight;
	}
	@Override
	public int getTicketId(String userId, String flightId) {
		// TODO Auto-generated method stub
		int tId= flightReservationDaoImpl.getTicketId(userId,flightId);
		if(tId==0)
		{
			throw new TicketNotFound("No Ticket Available with this userId & flightId");
		}
		return tId;
	}
	@Override
	public boolean cancelTicket(int id) {
		// TODO Auto-generated method stub
		boolean flag= flightReservationDaoImpl.cancelTicket(id);
		if(flag==false)
		{
			throw new TicketNotFound("Not found any ticket with this ticket Id");
		}
		return flag;
	}
	@Override
	public Tickets getTicketById(int id) {
		// TODO Auto-generated method stub
		Tickets ticket=flightReservationDaoImpl.getTicketById(id);
		if(ticket==null)
		{
			throw new TicketNotFound("Ticket Not Found");
		}
		
		return ticket;
	}
}
