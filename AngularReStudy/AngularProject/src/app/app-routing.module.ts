import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateCouponComponent } from './Components/CompanyComponents/create-coupon/create-coupon.component';
import { GetAllCouponsComponent } from './Components/CompanyComponents/get-all-coupons/get-all-coupons.component';
import { RemoveCouponVerificationComponent } from './Components/CompanyComponents/remove-coupon-verification/remove-coupon-verification.component';
import { UpdateCouponComponent } from './Components/CompanyComponents/update-coupon/update-coupon.component';
import { MyInfoComponent } from './Components/CompanyComponents/my-info/my-info.component';
import { PurchaseCouponComponent } from './Components/CustomerComponents/purchase-coupon/purchase-coupon.component';
import { MyPurchasedCouponsComponent } from './Components/CustomerComponents/my-purchased-coupons/my-purchased-coupons.component';
import { CompaniesComponent } from './Components/AdminComponents/companies/companies.component';
import { ShowCompanyComponent } from './Components/AdminComponents/show-company/show-company.component';
import { RemoveCompanyVerificationComponent } from './Components/AdminComponents/remove-company-verification/remove-company-verification.component';
import { UpdateCompanyComponent } from './Components/AdminComponents/update-company/update-company.component';
import { ShowCustomerComponent } from './Components/AdminComponents/show-customer/show-customer.component';
import { RemoveCustomerVerificationComponent } from './Components/AdminComponents/remove-customer-verification/remove-customer-verification.component';
import { UpdateCustomerComponent } from './Components/AdminComponents/update-customer/update-customer.component';
import { CustomersComponent } from './Components/AdminComponents/customers/customers.component';
import { LoginComponent } from './Components/login/login.component';
import { LoginHomeComponent } from './Components/login-home/login-home.component';
import { CreateCompanyComponent } from './Components/AdminComponents/create-company/create-company.component';
import { CreateCustomerComponent } from './Components/AdminComponents/create-customer/create-customer.component';
import { CustomerCouponPreviewComponent } from './Components/CustomerComponents/customer-coupon-preview/customer-coupon-preview.component';
import { CouponPreviewComponent } from './Components/CompanyComponents/company-coupon-preview/coupon-preview.component';
import { AdminCouponPreviewComponent } from './Components/AdminComponents/admin-coupon-preview/admin-coupon-preview.component';



const routes: Routes = [ 
  { path: 'createCoupon', component: CreateCouponComponent },
  { path: 'login', component: LoginHomeComponent },
  { path: 'logout', component: LoginHomeComponent },
  { path: 'getAllCoupons', component:  GetAllCouponsComponent},
  { path: 'myInfo', component:  MyInfoComponent},
  { path: 'purchaseCoupon', component:  PurchaseCouponComponent},
  { path: 'myPurchasedCoupons', component:  MyPurchasedCouponsComponent},
  { path: 'companies', component:  CompaniesComponent},
  { path: 'createCompany', component:  CreateCompanyComponent},
  { path: 'createCustomer', component:  CreateCustomerComponent},
  { path: 'customers', component:  CustomersComponent},
  { path: 'showCompany/:id', component:  ShowCompanyComponent},
  { path: 'showCoupon/:id', component:  CouponPreviewComponent},
  { path: 'adminShowCoupon/:id', component:  AdminCouponPreviewComponent},
  { path: 'getCoupon/:id', component:  CustomerCouponPreviewComponent},
  { path: 'removeCompany/:id', component:  RemoveCompanyVerificationComponent},
  { path: 'updateCompany/:id', component:  UpdateCompanyComponent},
  { path: 'showCustomer/:id', component:  ShowCustomerComponent},
  { path: 'removeCustomer/:id', component: RemoveCustomerVerificationComponent},
  { path: 'updateCustomer/:id', component:  UpdateCustomerComponent},
  { path: 'removeCoupon/:id', component:  RemoveCouponVerificationComponent},
  { path: 'updateCoupon/:id', component:  UpdateCouponComponent},
  { path: '', redirectTo: '/login',pathMatch: 'full'},
  { path: '**', redirectTo: '/login',pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
