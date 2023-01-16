import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HeaderFooterComponent } from './header-footer/header-footer.component';
import { LoginComponent } from './login/login.component';
import { ProductAddComponent } from './Product/product-add/product-add.component';
import { ProductListComponent } from './Product/product-list/product-list.component';

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'header-footer', component: HeaderFooterComponent},
  {path: 'list', component: ProductListComponent},
//  {path: '', component: AppComponent},
//     // Thêm children[] là các đường dẫn con /home/login/products....
// { path: 'list', component: ProductListComponent }, // url nao thi vao component do
// { path: 'add', component: ProductAddComponent },
// { path: 'header-footer', component: HeaderFooterComponent },
// //{ path: 'edit', component: ProductEditComponent },// nếu mặc định không chọn sẽ là trang chủ mặc định
// { path: '', component: ProductListComponent },
// //{ path: '**', component: PageNotFoundComponent }// url sai sẽ vào báo lỗi( tạo riêng 1 component hiển thị báo lỗi)
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
