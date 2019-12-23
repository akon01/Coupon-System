import { Component, OnInit } from "@angular/core";
import { Coupon } from "src/app/Models/coupon";
import { ActivatedRoute } from "@angular/router";
import { CompanyService } from "src/app/Services/company.service";
import { CustomerService } from "src/app/Services/customer.service";

@Component({
  selector: "app-customer-coupon-preview",
  templateUrl: "./customer-coupon-preview.component.html",
  styleUrls: ["./customer-coupon-preview.component.css"]
})
export class CustomerCouponPreviewComponent implements OnInit {
  public coupon: Coupon = new Coupon();

  public coupons: Coupon[] = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    private customerService: CustomerService
  ) {}
  /**
   * Get selected coupon info to preview.
   */
  ngOnInit() {
    let id = this.activatedRoute.snapshot.params.id;
    this.customerService.getAllPurchasedCoupons().subscribe(
      coupons => {
        this.coupons = coupons;
        for (let i = 0; i < this.coupons.length; i++) {
          const coupon = this.coupons[i];
          if (coupon.id == id) {
            this.coupon = coupon;
          }
        }
      },
      error => {
        alert(error.error);
      }
    );
  }
}
