import { Component } from '@angular/core';
//
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ProductAddComponent } from '../Product/product-add/product-add.component';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-header-footer',
  templateUrl: './header-footer.component.html',
  styleUrls: ['./header-footer.component.css']
})
export class HeaderFooterComponent {
  nameUser = 'Nguyen The Nam';

  constructor(private _dialog: MatDialog ,private router:Router, private authService: AuthService){}


}
