import { Token } from '@angular/compiler';
import { UserStorageService } from './storage/user-storage.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
    private userStorageService:UserStorageService) { }

  register(signupRequest:any): Observable<any> {
    return this.http.post(BASIC_URL+ "sign-up",signupRequest);
  }

  login(username:string,password:string):any{
    const headers = new HttpHeaders().set('Content-type','application/json');
    const body ={username,password};

    return this.http.post(BASIC_URL+ 'authenticate',body,{ headers,observe:'response'}).pipe(
      map((res)=>{
      const token = res.headers.get('authorization').substring(7);
      const user =res.body;
      if(token && user){
        this.userStorageService.saveToken(token);
        this.userStorageService.saveUser(user);
        return true;

      }
      return false;

    })
    )
  }
}

