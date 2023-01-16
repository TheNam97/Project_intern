import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Observable } from 'rxjs';
import { User } from '../model/user.model';
import { Products } from '../model/Products.model';
import { AuthService } from './auth.service';

const AUTH_API = 'http://localhost:8081/api/products/';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(
    private http: HttpClient,
    private authService: AuthService
    ) { }
  
  ngOnInit(){
  }

  public setHeaders(): any{
    let token = `Bearer ${this.authService.getAuthorizationToken()}`
    //console.log('token:  ',token);
    const headers= new HttpHeaders().set('Authorization',token)
    //console.log('headers:  ' ,headers);
    return headers
  }

  public insertProduct(product: Products){
    const headers = this.setHeaders()
    return this.http.post<any>(AUTH_API + 'insert', product, {
      headers,
      responseType:'json'
    });
  }

  public getAllProduct(): Observable<any> {
    const headers = this.setHeaders()
    return this.http.get<any>(AUTH_API,{
      headers,
      responseType:'json'
    });
  }

  public getProductByName(nameProduct: string): Observable<any> {
    const headers = this.setHeaders()
    return this.http.get<any>(AUTH_API + nameProduct,{
      headers,
      responseType:'json'
    });
  }

  public updateProduct(id: any, product: Products): Observable<any> {
    const headers = this.setHeaders()
    console.log(product);
    console.log(AUTH_API+id);
    return this.http.put<any>(AUTH_API+id,product,{
      headers,
      responseType:'json'
    });
  }

  public deleteProduct(id: any): Observable<any> {
    const headers = this.setHeaders()
    return this.http.delete<any>(AUTH_API+id,{
      headers,
      responseType:'json'
    });
  }
  public report(): Observable<any> {
    const headers = this.setHeaders()
    console.log(AUTH_API+'report/pdf');
    return this.http.get<any>(AUTH_API+'report/pdf',{
      headers,
      responseType:'text' as 'json' 
    });
  }
}


