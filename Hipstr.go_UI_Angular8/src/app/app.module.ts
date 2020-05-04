import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
// To Handle HttpClient service
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginRegistrationComponent } from './login-registration/login-registration.component';
import { UserComponent } from './user/user.component';
import { HomeComponent } from './user/home/home.component';
import { LoginComponent } from './login-registration/login/login.component';
import { SearchFlightsComponent } from './user/search-flights/search-flights.component';
import { ConfirmBookingComponent } from './user/confirm-booking/confirm-booking.component';
import { MyBookingsComponent } from './user/my-bookings/my-bookings.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginRegistrationComponent,
    UserComponent,
    HomeComponent,
    LoginComponent,
    SearchFlightsComponent,
    ConfirmBookingComponent,
    MyBookingsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule, // ReactiveFormsModule for Reactive forms,
    FormsModule, // FormsModule for Template driven forms
    HttpClientModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
