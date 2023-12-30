import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/Services/auth/storage/user-storage.service';

const BASIC_URL = "http://localhost:8081/";


@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }

  addCategory(categoryDto:any):Observable<any>{
    console.log("succuessfully entered");
    return this.http.post(BASIC_URL + 'api/admin/category',categoryDto,{
      headers:this.createAuthorizationHeader(),
    })
  }
  getAllCategories():Observable<any>{
    console.log("succuessfully entered get all categories");
    return this.http.get(BASIC_URL + 'api/admin/categories',{
      headers:this.createAuthorizationHeader(),
    })
  }
  addProduct(productDto:any):Observable<any>{
    console.log("succuessfully entered");
    return this.http.post(BASIC_URL + 'api/admin/product',productDto,{
      headers:this.createAuthorizationHeader(),
    })
  }
  getAllProducts():Observable<any>{
    console.log("succuessfully entered get all products");
    return this.http.get(BASIC_URL + 'api/admin/products',{
      headers:this.createAuthorizationHeader(),
    })
  }

  getAllProductsByName(name:any):Observable<any>{
    return this.http.get(BASIC_URL + `api/admin/search/${name}`,{
      headers:this.createAuthorizationHeader(),
    })
  }
  deleteProduct(productId:any):Observable<any>{
    console.log("succuessfully entered");
    return this.http.delete(BASIC_URL + `api/admin/product/${productId}`,{
      headers:this.createAuthorizationHeader(),
    })
  }

  private createAuthorizationHeader(): HttpHeaders {
    const token = UserStorageService.getToken();
    console.log('Token:', token);
    return new HttpHeaders().set('Authorization', 'Bearer ' + token);  // Fixed
  }

}
