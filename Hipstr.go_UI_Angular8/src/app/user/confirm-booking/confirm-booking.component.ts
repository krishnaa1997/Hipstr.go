import { Component, OnInit } from '@angular/core';
import { FlightService } from 'src/app/services/flight.service';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';
import { ActivatedRoute, Router } from '@angular/router';
import { TicketService } from 'src/app/services/ticket.service';
import { Flights } from 'src/app/models/flights.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Tickets } from 'src/app/models/tickets.model';

@Component({
  selector: 'app-confirm-booking',
  templateUrl: './confirm-booking.component.html',
  styleUrls: ['./confirm-booking.component.css']
})
export class ConfirmBookingComponent implements OnInit {

  flightId:string;
  user: User;
  id: string;
  userId: string;
  flight: Flights;
  userDetailForm: FormGroup;
  submitted: boolean = false;
  passengers:Tickets[]=[];
  count: number=0;
  i:number;
  bookbtn: boolean= true;
  seatsAvailable: number;
  seatsAvailableFlag: boolean=true;

  constructor(private formBuilder: FormBuilder,private router: Router,private ticketService: TicketService,private flightService: FlightService, private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params.id;
    this.userId=this.userService.getLoggedInUserData();
    this.userService.getUserById(this.userId).subscribe(data=>{this.user=data; console.log(this.user);});
    this.flightService.getFlightById(this.id).subscribe(data=>{
      this.flight=data;
      this.seatsAvailable=data.availableSeats;
      console.log(this.flight);
    });



    this.userDetailForm=this.formBuilder.group({
      passengerName: ['',[Validators.required,Validators.pattern("^[a-zA-Z ]+$")]],
      age:['', [Validators.required, Validators.min(0), Validators.max(110)]],
      gender:['',Validators.required],
      userId:['', Validators.required],
      flightId:['', Validators.required]
    });
  }

  

  bookTicket()
  {

    for(this.i=0;this.i<this.passengers.length;this.i++)
    {
      this.passengers[this.i].userId=this.userId;
      this.passengers[this.i].flightId=this.flight.flightNumber;
      console.log(this.passengers[this.i]);
      this.ticketService.bookTicket(this.passengers[this.i]).subscribe(data=>{
        
        this.router.navigate(['/home/mybookings']);
        console.log(data);
      }, err=>{
        alert("An Error Occured unable to book ticket please try once again");
      });
    }
    alert("Ticket is booked successfully..!");
  }

  
  addPassenger()
  {
this.submitted=true;

this.seatsAvailable=this.seatsAvailable-1;    
    console.log(this.userDetailForm.value);
    this.passengers.push(this.userDetailForm.value);
    this.count=this.count+1;
    this.userDetailForm.reset();
    this.submitted=false;
    if(this.passengers.length==0)
    {
      this.bookbtn=true;
    }
    else{this.bookbtn=false;}

    if(this.seatsAvailable==0)
    {
      this.seatsAvailableFlag=false;
    }
    else
    {
      this.seatsAvailableFlag=true;
    }
  }

  deletePassenger(i: number)
  {
    this.passengers.splice(i,1);
    this.count=this.count-1;
    this.seatsAvailable=this.seatsAvailable+1;
    if(this.seatsAvailable==0)
    {
      this.seatsAvailableFlag=false;
    }
    else
    {
      this.seatsAvailableFlag=true;
    }
    if(this.passengers.length==0)
    {
      this.bookbtn=true;
    }
    else{this.bookbtn=false;}
  }

}
