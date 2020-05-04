import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { User } from '../models/user.model';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  id: string;
  user: User;
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.id=this.userService.getLoggedInUserData();
    this.getUser(this.id);

  }

  getUser(id)
  {
    this.userService.getUserById(id).subscribe(data=>{
      this.user=data;
      console.log(this.user);
    }, err=>{
      alert("auto logged out");
      console.log(err.error.message);
      this.router.navigate(['../login-registration']);
    });

  }

  logout()
  {
    this.userService.loggedInUserData(null);
    this.router.navigate(['../login-registration']);
  }

}
