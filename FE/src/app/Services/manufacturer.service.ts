import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';


const AUTH_API = 'http://localhost:8081/api/manufacturers';
@Injectable({
  providedIn: 'root'
})
export class ManufacturerService {

  constructor(
    private http: HttpClient,
    private authService: AuthService
    ) { }


    public setHeaders(): any{
      let token = `Bearer ${this.authService.getAuthorizationToken()}`
      //console.log('token:  ',token);
      const headers= new HttpHeaders().set('Authorization',token)
      //console.log('headers:  ' ,headers);
      return headers
    }
    
    public getNameManu(): Observable<any> {
      const headers = this.setHeaders()
      return this.http.get<any>(AUTH_API,{
        headers,
        responseType:'json'
      });
    }
}
