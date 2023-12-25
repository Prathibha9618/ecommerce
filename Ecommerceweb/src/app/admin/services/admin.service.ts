import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/Services/auth/storage/user-storage.service';

const BASIC_URL = "http://localhost:8081/api/";


@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }

  addCategory(categoryDto:any):Observable<any>{
    console.log("succuessfully entered");
    return this.http.post(BASIC_URL + 'admin/category',categoryDto,{
      headers:this.createAuthorizationHeader(),
    })

  }
  private createAuthorizationHeader():HttpHeaders{
    const token = UserStorageService.getToken();
    console.log('Token:', token);
    return new HttpHeaders().set(
      'Authorization','Bearer' + token);

  }
}
