import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';
import { FlightService } from 'src/app/services/flight.service';
import { Flights } from 'src/app/models/flights.model';

import { TicketService } from 'src/app/services/ticket.service';
import { Tickets } from 'src/app/models/tickets.model';

@Component({
  selector: 'app-my-bookings',
  templateUrl: './my-bookings.component.html',
  styleUrls: ['./my-bookings.component.css']
})
export class MyBookingsComponent implements OnInit {

  userId: string;
  user: User;
  i: number;
  flightId: string;
  flights: Flights[]=[];
  tickets: Tickets[]=[];
  booked_flag:boolean=true;
  cancel_flag:boolean=false;
  flight: Flights;
  flightNotFoundFlag: boolean=false;
  flightNotFound: string;

  constructor(private userService: UserService, private flightService: FlightService, private ticketService: TicketService) { }

  ngOnInit(): void {
    this.userId=this.userService.getLoggedInUserData();
    this.getTickets(this.userId);
    
  }

  getTickets(userId: string)
  {
    this.userService.getUserById(userId).subscribe(data=>{this.user=data; console.log(this.user);
      this.getFlights(this.user);}, err=>{
        console.log(err.error.message);
      });

  }
  getFlights(user:User)
  {
    this.booked_flag=true;
    this.cancel_flag=false;
    this.flightNotFoundFlag=false;
    if(user.bookedTickets.length==0)
    {
      this.booked_flag=false;
      this.cancel_flag=false;
      this.flightNotFound="Not yet booked any ticket with us.";
      this.flightNotFoundFlag=true;
    }
    for(this.i=0;this.i<user.bookedTickets.length;this.i++)
    {
      this.tickets.push(user.bookedTickets[this.i]); 
      this.flightService.getFlightsById(user.bookedTickets[this.i].ticketNumber).subscribe(
        data=>{
          console.log(data);
          this.flights.push(data);
             
        }, err=>{console.log(err.error.log);}
      );
    }
  }

  cancelTicket(flight:Flights)
  {


    let result=confirm("do you want cancel");
    if(result){
      this.cancel_flag=true;
      this.booked_flag=false;
      this.flightNotFoundFlag=false;

    this.ticketService.getTicketId(this.userId, flight.flightNumber).subscribe(data=>{
      this.ticketService.cancelTicket(data).subscribe(data=>{
        console.log(data);

        this.flight=flight;
        this.tickets=[];
        this.flights=[];
         

      }, err=>{
        alert(err.error.message);
      });
    


    });}
  }

  showBookings()
  {
    this.getTickets(this.userId);  
  }

}
