
import { Component } from '@angular/core';
import { Router } from '@angular/router';
//
import { User } from '../model/user.model';
import { AuthService } from '../Services/auth.service';
import { ProductsService } from '../Services/products.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  
  user : User = new User();

  constructor(
    private router : Router,
    // private userService : UserService,
    private authService : AuthService,
    private productService : ProductsService,
    // private tokenService : TokenService
  ){}

  public signin(){
    console.log(this.user);
    this.authService.signIn(this.user).subscribe(data =>
      {
        console.log(data);
        console.log(data.status,data.message)

        window.localStorage.setItem('token', data.message);
        
        this.router.navigate(['/list']);
      },
    error => alert("Tên đăng nhập hoặc mật khẩu không đúng")
    )
  }
}
