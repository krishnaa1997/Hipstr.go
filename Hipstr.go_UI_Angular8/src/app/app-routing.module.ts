import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './user/user.component';
import { LoginRegistrationComponent } from './login-registration/login-registration.component';
import { HomeComponent } from './user/home/home.component';
import { LoginComponent } from './login-registration/login/login.component';
import { SearchFlightsComponent } from './user/search-flights/search-flights.component';
import { ConfirmBookingComponent } from './user/confirm-booking/confirm-booking.component';
import { MyBookingsComponent } from './user/my-bookings/my-bookings.component';


const routes: Routes = [
  {path:'', redirectTo: 'login-registration', pathMatch: 'full'},
  {path:'home', component:UserComponent,
  children:[

    {path: '', component: HomeComponent},
    {
      path: 'searchflights', component: SearchFlightsComponent
    },
    {path: 'confirmbooking/:id', component: ConfirmBookingComponent},
    {path: 'mybookings', component: MyBookingsComponent},
  ]
},
  {path:'login-registration', component:LoginRegistrationComponent,
children:[
  {
    path:'', component: LoginComponent
  },
]
},
  {path:'**',redirectTo:'login-registration', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
