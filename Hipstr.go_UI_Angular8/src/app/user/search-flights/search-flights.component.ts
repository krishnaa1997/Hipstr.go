import { Component, OnInit } from '@angular/core';
import { FlightService } from 'src/app/services/flight.service';
import { Flights } from 'src/app/models/flights.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router'
import { UserService } from 'src/app/services/user.service';
import { TicketService } from 'src/app/services/ticket.service';

@Component({
  selector: 'app-search-flights',
  templateUrl: './search-flights.component.html',
  styleUrls: ['./search-flights.component.css']
})
export class SearchFlightsComponent implements OnInit {

  flights: Flights[];
  flightSearchData: Flights;
  searchForm: FormGroup;
  submitted: boolean = false;
  userId: string;
  flightFoundFlag: boolean=true;
  flightNotFoundFlag: boolean=false;
  flightNotFound: string;
  constructor(private flightService: FlightService,private ticketService:TicketService,private userService: UserService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.getSearchData();
    this.getFlights();
    this.searchForm= this.formBuilder.group({
      source: [this.flightSearchData.source, Validators.required],
      destination: [this.flightSearchData.destination, Validators.required],
      journeyDate:[this.flightSearchData.journeyDate, Validators.required]
    }); 

  }

  search()
  {    this.flightNotFoundFlag=false;
    this.flightFoundFlag=true;
    this.flightService.searchData(this.searchForm.value);
    this.getFlights();
  }

  getSearchData()
  {
    this.flightSearchData=this.flightService.getSearchData();
  }

  getFlights()
  {

    this.flightService.getSearchFlights().subscribe(data=>{
      this.flights=data;
      console.log(this.flights);
    },err=>{
      this.flightNotFound=err.error.message;
      this.flightNotFoundFlag=true;
      this.flightFoundFlag=false;
      //alert(err.error.message);
    });
  }

  bookTicket(flight: Flights)
  {
    //this.flightService.bookingData(id);
    if(flight.availableSeats==0)
    {
      alert("Not Able to book ticket as no seat is available");
    }
    else{

    this.router.navigate(['/home/confirmbooking', flight.flightNumber]);
  }

}

swap()
{
  let temp= this.searchForm.controls.source.value;
  this.searchForm.controls.source.setValue(this.searchForm.controls.destination.value);
  this.searchForm.controls.destination.setValue(temp);
}
}