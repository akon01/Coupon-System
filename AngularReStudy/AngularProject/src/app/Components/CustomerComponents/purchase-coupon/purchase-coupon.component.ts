import { Component, OnInit } from "@angular/core";
import { CouponServiceService } from "src/app/Services/coupon.service";
import { Coupon } from "src/app/Models/coupon";
import { Router } from "@angular/router";
import { CustomerService } from "src/app/Services/customer.service";

@Component({
  selector: "app-purchase-coupon",
  templateUrl: "./purchase-coupon.component.html",
  styleUrls: ["./purchase-coupon.component.css"]
})
export class PurchaseCouponComponent implements OnInit {
  public coupons = [];

  constructor(
    private customerService: CustomerService,
    private router: Router
  ) {}
  /**
   * Get all available coupons for purchase.
   */
  ngOnInit() {
    this.customerService.getAvailableCoupon().subscribe(
      availableCoupons => {
        this.coupons = availableCoupons;
      },
      error => {
        alert(error.error);
      }
    );
  }
  /**
   * Purchase a selected coupon
   * @param coupon A coupon to purchase.
   */
  buyCoupon(coupon: Coupon) {
    this.customerService.purchaseCoupon(coupon).subscribe(
      wasPurchased => {
        if (wasPurchased) {
          alert("Coupon " + coupon.title + " was purchased!");
        } else {
          alert("Coupon " + coupon.title + " was not purchased!");
        }
      },
      error => {
        alert(error.error);
      }
    );
  }
}
