import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { Flights } from '../models/flights.model';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  // Injecting HttpClient service to make REST Api calls.
  // To perform CRUD operations with Http Verbs - GET, POST, PUT, DELETE
  constructor(private http: HttpClient) { }

  // endpoint or REST api URL
  baseUrl: string = "http://localhost:8080";
  flightSearchData:Flights;
  id: string;

  searchData(flights: Flights)
  {
    this.flightSearchData=flights;
  }

  bookingData(id:string)
  {
    this.id=id;
  }

  getSearchData()
  {
    return this.flightSearchData;
  }

  getSearchFlights()
  {
    console.log(this.flightSearchData);
    return this.http.get<Flights[]>(this.baseUrl +"/flights/"+this.flightSearchData.source+"/"+this.flightSearchData.destination+"/"+this.flightSearchData.journeyDate);
  }

  getFlightsById(id: number)
  {
    //this.http.get<Flights>(this.baseUrl+"/tickets/"+id).subscribe(data=>{this.bookefFlights.push(data);console.log(this.bookefFlights);});
    
    return this.http.get<Flights>(this.baseUrl+"/tickets/"+id);
  }

  getFlightById(id: string)
  {
    return this.http.get<Flights>(this.baseUrl+"/flights/" + id); //flights/{flightNumber}
  }

  
}
  /*
  // GET - getAllUsers()
  getAllUsers() {
    return this.http.get<User[]>(this.baseUrl);
  }
  // GET - getUserById(id)
  getUserById(id: number) {
    return this.http.get<User>(this.baseUrl + "/" + id);
  }
  // POST - createNewUser(user)
  createNewUser(user: User) {
    return this.http.post(this.baseUrl, user);
  }
  // PUT - updateUserById(user)
  updateUserById(user: User) {
    return this.http.put(this.baseUrl + "/" + user.id, user);
  }
  // DELETE - deleteUserById(id)
  deleteUserById(id: number) {
    console.log(1)
    return this.http.delete(this.baseUrl + "/" + id);
  }*/
