import { Component } from '@angular/core';
import { Products } from 'src/app/model/Products.model';
import { ManufacturerService } from 'src/app/Services/manufacturer.service';
import { ProductsService } from 'src/app/Services/products.service';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent {
  public manuSelect=[]
  public typeSelect=['Tablet','Smartphone']
  public product: Products = new Products()

  constructor(private productService: ProductsService, private manuService: ManufacturerService) {
    
  }
  ngOnInit(){
    this.getNameManu()
  }
  public insertProduct(){
    console.log(this.product);
    this.productService.insertProduct(this.product).subscribe(data =>{
      console.log(data);
      alert('Thêm sản phẩm thành công')
    },
    error => alert('Lỗi! không thêm được sản phẩm'))
  }
  public getNameManu(){
    this.manuService.getNameManu().subscribe(data => {
      this.manuSelect=data
      console.log(this.manuSelect);
    })
  }
}
