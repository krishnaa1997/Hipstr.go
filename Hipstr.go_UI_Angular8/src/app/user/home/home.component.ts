import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { FlightService } from 'src/app/services/flight.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  sub:any;
  id:any;
  user: User;
  searchForm: FormGroup;
  submitted: boolean = false;
  count: boolean=false;
  className: string;

  constructor(private route:ActivatedRoute,private router: Router, private userService:UserService, private formBuilder: FormBuilder, private flightService: FlightService) { }

  //userId: string= this.route.
  

  ngOnInit(): void {

    this.id=this.userService.getLoggedInUserData();
      this.getUser(this.id);
      console.log(this.id);

      this.searchForm = this.formBuilder.group({
        source: ['', Validators.required],
        destination: ['', Validators.required],
        journeyDate:['', Validators.required],
      });
  }


  getUser(id)
  {
    this.userService.getUserById(id).subscribe(data=>{
      this.user=data;
      console.log(this.user);
    },err=>{console.log(err.error.message);});

  }

  search()
  {
      this.submitted=true;
      this.flightService.searchData(this.searchForm.value);
      this.router.navigate(['home/searchflights']);
  }

  specificSearch(source: String, destination: String)
  {
    this.searchForm.controls['source'].setValue(source);
    this.searchForm.controls['destination'].setValue(destination);
    this.searchForm.controls['journeyDate'].setValue("2020-05-03");
    this.search();
  }

  onClick()
  {
    this.count=true;
    if(this.count)
    {
      this.className="full";
    }
    else{
      this.className="half";
    }

  }


}
