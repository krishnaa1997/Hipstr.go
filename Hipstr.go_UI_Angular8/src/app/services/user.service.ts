import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  id: string;

  // Injecting HttpClient service to make REST Api calls.
  // To perform CRUD operations with Http Verbs - GET, POST, PUT, DELETE
  constructor(private http: HttpClient) { }

  // endpoint or REST api URL
  baseUrl: string = "http://localhost:8080";

  loggedInUserData(id: string)
  {
    this.id=id;
  }
  getLoggedInUserData()
  {
    return this.id;
  }

  loginGetUser(user: User)
  {
    return this.http.get<boolean>(this.baseUrl+"/login/"+user.userId+"/"+user.password);
  }

  registerUser(user: User)
  {
    return this.http.post(this.baseUrl + "/addNewUser", user);
  }

  getUserById(id: string)
  {
    return this.http.get<User>(this.baseUrl + "/"+ id);
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
}
