 package com.flightReservation.dao;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.flightReservation.controller.FlightReservationController;
import com.flightReservation.entities.BookTicket;
import com.flightReservation.entities.Flights;
import com.flightReservation.entities.Tickets;
import com.flightReservation.entities.User;

@Repository
public class FlightReservationDaoImpl implements IFlightReservationDao {
	
	org.slf4j.Logger logger=LoggerFactory.getLogger(FlightReservationDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public boolean addUser(User newUser) {
		User user=entityManager.find(User.class, newUser.getUserId());
		if(user!=null) {
			return false;
		}
		entityManager.persist(newUser);
		return true;
	}
	@Override
	public User findUserById(String userId) {
		User user=entityManager.find(User.class, userId);
		if(user==null) {
			return null;
		}
		return user;
	}
	@Override
	public Flights getFlightById(String fId) {
		Flights flight=entityManager.find(Flights.class, fId);
		if(flight==null) {
			return null;
		}
		return flight;
	}
	@Override
	public List<Flights> getAllFlights(String source,String destination, String journeyDate){
		String command="select flight from Flights flight where (flight.source=:source and flight.destination=:destination) and to_char(flight.journeyDate,'yyyy-mm-dd')=:journeyDate";
		TypedQuery<Flights> query=entityManager.createQuery(command,Flights.class);
		query.setParameter("source", source.toLowerCase());
		query.setParameter("destination", destination.toLowerCase());
		query.setParameter("journeyDate", journeyDate);
		List<Flights> flights=query.getResultList();
		return flights;
	}
	@Override
	public boolean login(String userId, String password) {
		// TODO Auto-generated method stub
		String command="select u from User u where u.userId=:userId and u.password=:password";
		TypedQuery<User> query=entityManager.createQuery(command, User.class);
		query.setParameter("userId", userId);
		query.setParameter("password", password);
		List<User> u= query.getResultList();
		if(u.size()==0)
		{
			return false;
		}
		else {
		User u1= u.get(0);
		return true;}
	}
	@Override
	public List<Tickets> viewBookedFlights(String userId) {
		User user = entityManager.find(User.class, userId);
		return ( user.getBookedTickets());
	}
	@Override
	public boolean bookingTicket(BookTicket ticket)
	{
		logger.trace(ticket.getGender());
		Flights flight=entityManager.find(Flights.class, ticket.getFlightId());
		if(flight.getAvailableSeats()==0)
		{
			return false;
		}
		flight.setAvailableSeats(flight.getAvailableSeats()-1);
		entityManager.merge(flight);
		User user= entityManager.find(User.class, ticket.getUserId());
		Tickets tickets=new Tickets(flight,user);
		tickets.setGender(ticket.getGender());
		tickets.setAge(ticket.getAge());
		tickets.setPassengerName(ticket.getPassengerName());
		entityManager.persist(tickets);
		//user.addTickets(ticket);
		//return "Hello "+ user.getFirstName()+"\n"+"Your Booking for flight no. "+flight.getFlightNumber()+" from "+flight.getSource()+" to "+flight.getDestination()+"\n"+"Is Successfully booked"+ "\n"+ "Final Price is "+flight.getCost()+ "\n"+ "\n" ;
		return true;
	}
	@Override
	public User getUserById(String userId)
	{
		User user=entityManager.find(User.class, userId);
		if(user==null) {
			return null;
		}
		return user;
	}
	@Override
	public Flights getBookedFlightsId(int ticketNumber) {
		// TODO Auto-generated method stub
		Tickets tickets=entityManager.find(Tickets.class, ticketNumber);
		if(tickets==null)
		{
			return null;
		}
		return tickets.getFlight();
	}
	@Override
	public int getTicketId(String userId, String flightNumber) {
		// TODO Auto-generated method stub
		User user=entityManager.find(User.class, userId);
		Flights flight=entityManager.find(Flights.class, flightNumber);
		String command="select t from Tickets t where t.user=:user and t.flight=:flight";
		TypedQuery<Tickets> query=entityManager.createQuery(command, Tickets.class);
		query.setParameter("user", user);
		query.setParameter("flight", flight);
		
			Tickets t= query.getResultList().get(0);
			if(t==null)
			{
				return 0;
			}

			return t.getTicketNumber();

		
	}
	@Override
	public boolean cancelTicket(int ticketNumber) {
		// TODO Auto-generated method stub
		Tickets ticket = entityManager.find(Tickets.class, ticketNumber);	
		if(ticket==null)
		{
			return false;
		}
		Flights flight=ticket.getFlight();
		User user = ticket.getUser();
		flight.removeTicket(ticket);
		user.removeTicket(ticket);
		flight.setAvailableSeats(flight.getAvailableSeats()+1);
		entityManager.merge(flight);
		entityManager.remove(ticket);
		return true;
	}
	@Override
	public Tickets getTicketById(int id) {
		// TODO Auto-generated method stub
		
		Tickets ticket=entityManager.find(Tickets.class, id);
		if(ticket==null) {
			return null;
		}
		return ticket;
	
	}
}
