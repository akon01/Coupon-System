import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";

import { HeaderComponent } from "./Components/header/header.component";
import { FooterComponent } from "./Components/footer/footer.component";
import { LayoutComponent } from "./Components/layout/layout.component";
import { MenuComponent } from "./Components/menu/menu.component";
import { HomeComponent } from "./Components/home/home.component";
import { FormsModule } from "@angular/forms";
import { CreateCouponComponent } from "./Components/CompanyComponents/create-coupon/create-coupon.component";
import { GetAllCouponsComponent } from "./Components/CompanyComponents/get-all-coupons/get-all-coupons.component";

import { HttpClientModule } from "@angular/common/http";
import { RemoveCouponVerificationComponent } from "./Components/CompanyComponents/remove-coupon-verification/remove-coupon-verification.component";
import { UpdateCouponComponent } from "./Components/CompanyComponents/update-coupon/update-coupon.component";
import { MyInfoComponent } from "./Components/CompanyComponents/my-info/my-info.component";
import { PurchaseCouponComponent } from "./Components/CustomerComponents/purchase-coupon/purchase-coupon.component";
import { MyPurchasedCouponsComponent } from "./Components/CustomerComponents/my-purchased-coupons/my-purchased-coupons.component";
import { CompaniesComponent } from "./Components/AdminComponents/companies/companies.component";
import { ShowCompanyComponent } from "./Components/AdminComponents/show-company/show-company.component";
import { UpdateCompanyComponent } from "./Components/AdminComponents/update-company/update-company.component";
import { RemoveCompanyVerificationComponent } from "./Components/AdminComponents/remove-company-verification/remove-company-verification.component";
import { CustomersComponent } from "./Components/AdminComponents/customers/customers.component";
import { ShowCustomerComponent } from "./Components/AdminComponents/show-customer/show-customer.component";
import { RemoveCustomerVerificationComponent } from "./Components/AdminComponents/remove-customer-verification/remove-customer-verification.component";
import { UpdateCustomerComponent } from "./Components/AdminComponents/update-customer/update-customer.component";
import { LoginComponent } from "./Components/login/login.component";
import { LoginHomeComponent } from "./Components/login-home/login-home.component";
import { CreateCompanyComponent } from "./Components/AdminComponents/create-company/create-company.component";
import { CreateCustomerComponent } from "./Components/AdminComponents/create-customer/create-customer.component";
import { CustomerCouponPreviewComponent } from "./Components/CustomerComponents/customer-coupon-preview/customer-coupon-preview.component";
import { AdminCouponPreviewComponent } from "./Components/AdminComponents/admin-coupon-preview/admin-coupon-preview.component";
import { CouponPreviewComponent } from "./Components/CompanyComponents/company-coupon-preview/coupon-preview.component";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { DateValidatorDirective } from "./date-validator.directive";

@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    LayoutComponent,
    MenuComponent,
    HomeComponent,
    CreateCouponComponent,
    GetAllCouponsComponent,
    CouponPreviewComponent,
    RemoveCouponVerificationComponent,
    UpdateCouponComponent,
    MyInfoComponent,
    PurchaseCouponComponent,
    MyPurchasedCouponsComponent,
    CompaniesComponent,
    ShowCompanyComponent,
    UpdateCompanyComponent,
    RemoveCompanyVerificationComponent,
    CustomersComponent,
    ShowCustomerComponent,
    RemoveCustomerVerificationComponent,
    UpdateCustomerComponent,
    LoginComponent,
    LoginHomeComponent,
    CreateCompanyComponent,
    CreateCustomerComponent,
    CustomerCouponPreviewComponent,
    AdminCouponPreviewComponent,
    DateValidatorDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [LayoutComponent]
})
export class AppModule {}
