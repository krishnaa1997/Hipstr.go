package com.flightReservation.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name="ticket")
public class Tickets implements Serializable{

	private static final long serialversionUID=1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int ticketNumber;
	String passengerName;
	
	String gender;
	int age;
	

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="userId",nullable=false)
	private User user;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="flightNumber",nullable=false)
	private Flights flight;
	
	public Tickets() {
		// TODO Auto-generated constructor stub
	}
	

	public Tickets(Flights flight, User user) {
		super();
		this.flight = flight;
		this.user = user;
		
		//this.age=age;
		
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Flights getFlight() {
		return flight;
	}

	public void setFlight(Flights flight) {
		this.flight = flight;
	}


	public String getPassengerName() {
		return passengerName;
	}


	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
	
	
}
