import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Tickets } from '../models/tickets.model';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  baseUrl: string = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  /*  getSearchFlights()
  {
    console.log(this.flightSearchData);
    return this.http.get<Flights[]>(this.baseUrl +"/flights/"+this.flightSearchData.source+"/"+this.flightSearchData.destination+"/"+this.flightSearchData.journeyDate);
  }*/
  
  getTicketId(userId:string, flightId: string)
  {
    return this.http.get<number>(this.baseUrl+"/"+userId+"/"+flightId);
  }

  cancelTicket(id: number)
  {
    return this.http.delete(this.baseUrl+"/cancelTicket/"+id);
  }

  bookTicket(ticket:Tickets)
  {
    console.log(ticket);
    return this.http.post(this.baseUrl+"/bookingTicket", ticket);//bookingTicket/{flightId}/{userId}
  }

  getTicketById(id: number)
  {
    return this.http.get(this.baseUrl+"/ticketDetails/"+id);
  }
}
