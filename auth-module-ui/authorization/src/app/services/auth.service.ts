
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RegisterForm } from '../models/registerForm';
import { Observable } from 'rxjs';


const headers = {
    'Authorization': 'Basic ' + btoa('clientid:clientsecret'),
    'Content-type': 'application/x-www-form-urlencoded'
  }
  

@Injectable({
    providedIn: 'root'
})
export class AuthService {

private regUrl = 'http://localhost:8085/auth/register';

 
  constructor(private http: HttpClient) {
  }
 

  register(regForm: RegisterForm){
    const h ={headers: new HttpHeaders({ 'Content-Type': 'application/json' })}
    return this.http.post(this.regUrl, regForm, h);
  } 
 
}  