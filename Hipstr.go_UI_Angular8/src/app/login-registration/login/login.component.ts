import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  registerForm: FormGroup;
  submitted: boolean = false;
  submitted1: boolean = false;
  userId: string;
  login_flag: boolean = true;
  register_flag: boolean = false;
  flag: boolean;
  lFlag: boolean;
  userExist:boolean=false;


  constructor(private formBuilder: FormBuilder, private userService: UserService, private route: Router) { }

  ngOnInit(): void {

    this.loginForm = this.formBuilder.group({
      userId: ['', Validators.required],
      password: ['', Validators.required],
    });

    this.registerForm= this.formBuilder.group({
      userId: ['', Validators.required],
      firstName: ['', [Validators.required,Validators.pattern("^[a-zA-Z]+$")]],
      lastName: ['', [Validators.required, Validators.pattern("^[a-zA-Z]+$")]],
      password: ['', Validators.required],
      mobileNumber: ['', [Validators.required, Validators.pattern("[6-9][0-9]{9}")]],
      gender: ['', Validators.required]
    });
  }

  loginFlag()
  {
    this.login_flag=true;
    this.register_flag=false;

  }

  checkUserName()
  {
    
    this.userService.getUserById(this.registerForm.controls.userId.value).subscribe(data=>{
      if(data!=null)
      {
        this.userExist= true;
      }
    }, err=>{ this.userExist=false;
      console.log(err.error.message);});
  }

  registerFlag()
  {
    this.login_flag=false;
    this.register_flag=true;
  }

  login(){
    //this.loginForm.controls.userId=this.loginForm.controls.userId.value.toLowerCase();
    this.submitted1=true;
    this.userService.loginGetUser(this.loginForm.value).subscribe(data => {
        this.userId=this.loginForm.controls.userId.value;
        this.userService.loggedInUserData(this.userId);
        console.log(data);
        this.route.navigate(['../home']);
    }, err=>{alert(err.error.message);});


  }
  register()
  {
    this.submitted=true;
    this.checkUserName();
    if(this.registerForm.invalid || this.userExist)
    {
      return;
    }
    this.userService.registerUser(this.registerForm.value).subscribe(data=>{
      this.registerForm.reset();
      this.submitted=false;
      alert("successfully registered ..!");
      console.log("register "+data);

    }, err=>{alert(err.error.message);});

  }

}
