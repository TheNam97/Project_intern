import { Component, ElementRef, ViewChild } from '@angular/core';

//
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { Router } from '@angular/router';
import { Data } from 'popper.js';
import { Manufacturers } from 'src/app/model/Manufacturers.model';
import { Products } from 'src/app/model/Products.model';
import { AuthService } from 'src/app/Services/auth.service';
import { ProductsService } from 'src/app/Services/products.service';
import { ProductAddComponent } from '../product-add/product-add.component';
import { ProductEditComponent } from '../product-edit/product-edit.component';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent {
  
  @ViewChild('ten') nameProductInput:ElementRef | undefined;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  public nameProduct ='test'
  public arrayProduct: Products[]=[]
  public array: Products[]=[];
  public products: Products = new Products;
  public manufacturer: Manufacturers = new Manufacturers;
  public pageSize = [5];
  ///////////
  constructor(private _dialog: MatDialog, private productService: ProductsService,private router : Router,
    private authService : AuthService, ){}
  /////////////

  ngOnInit(){
      this.getAllProduct()

  }

  public reloadList(){
    console.log('reload');
    window.location.reload();
  }

  public getAllProduct(){
    this.productService.getAllProduct().subscribe(data =>
    { 
      this.arrayProduct=data
      this.dataSource = new MatTableDataSource<Products>(this.arrayProduct);
      this.ngAfterViewInit()
      console.log(this.arrayProduct);
    },
    error => alert("Không có sản phẩm"));
  }
  
  public getProductByName(){
    if(this.nameProductInput?.nativeElement.value == '')
    {
      this.getAllProduct()
    }
    else {
      this.productService.getProductByName(this.nameProductInput?.nativeElement.value).subscribe(data =>
      { 

        this.arrayProduct=data
        this.dataSource = new MatTableDataSource<Products>(this.arrayProduct);
      },
      error => alert("Không tìm thấy sản phẩm  theo Tên"));
  }
  }
  public exportReport(){
    this.productService.report().subscribe(data=>{
      alert('Đã xuất báo cáo')
    },
    error => alert('Đã xuất báo cáo'))
  }

  openDialogAdd(){
    // const dialogConfig = new MatDialogConfig();
    // dialogConfig.disableClose = false;
    // dialogConfig.autoFocus=true
    // dialogConfig.width = "50%"
    // const dialogRef = this.dialog.open(ProductAddComponent,dialogConfig)

    const dialog = this._dialog.open(ProductAddComponent, {
      width: '40%',
      disableClose: false,
      autoFocus:true,
    });

    dialog.afterClosed().subscribe(result => {
      console.log('ADD');
      this.getAllProduct()
    });
  }
  openDialogEdit(row: Products){
    //console.log('Row clicked', row);
    const dialog = this._dialog.open(ProductEditComponent, {
      width: '40%',
      disableClose: false,
      autoFocus:true,
      data: row
    });

    dialog.afterClosed().subscribe(result => {
      console.log('EDIT');
      this.getAllProduct()
      //window.location.reload();
    });
  }

    ////////////////
    displayedColumns: string[] = ['id','productName','type','manufacturerName','description','year','price'];
    dataSource = new MatTableDataSource<Products>(this.arrayProduct);
    
    
    ngAfterViewInit() {
      this.dataSource.paginator = this.paginator;
    }
    
    clickedRows = new Set<Products>();
    //////
}
// export interface PeriodicElement {
//   id: Number
//   productName: string;
//   type: string;
//   manufacturer: string;
//   description: string;
//   year: number;
//   price: number;
// }

// const ELEMENT_DATA: PeriodicElement[] = [
//   {id: 1, productName: 'Hydrogen',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 2, productName: 'Helium',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 3, productName: 'Lithium',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 4, productName: 'Beryllium',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 5, productName: 'Boron',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 6, productName: 'Carbon',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 7, productName: 'Nitrogen',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 8, productName: 'Oxygen',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 9, productName: 'Fluorine',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 10, productName: 'Neon',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 11, productName: 'Sodium',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 12, productName: 'Magnesium',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 13, productName: 'Aluminum',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 14, productName: 'Silicon',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 15, productName: 'Phosphorus',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 16, productName: 'Sulfur',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 17, productName: 'Chlorine',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 18, productName: 'Argon',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 19, productName: 'Potassium',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200},
//   {id: 20, productName: 'Calcium',type: 'aaaa', manufacturer:'apple',description:'haha',year: 2000,price:200}
// ];