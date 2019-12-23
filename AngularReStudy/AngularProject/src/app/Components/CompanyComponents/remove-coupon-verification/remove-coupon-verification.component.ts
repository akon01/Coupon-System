import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { CouponServiceService } from "src/app/Services/coupon.service";
import { CompanyService } from "src/app/Services/company.service";
import { Coupon } from "src/app/Models/coupon";

@Component({
  selector: "app-remove-coupon-verification",
  templateUrl: "./remove-coupon-verification.component.html",
  styleUrls: ["./remove-coupon-verification.component.css"]
})
export class RemoveCouponVerificationComponent implements OnInit {
  private id;
  private coupon: Coupon = new Coupon();

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private companyService: CompanyService
  ) {}

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params.id;
  }
  /**
   * Go to all the company's coupons.
   */
  goBackToAllCoupons() {
    this.router.navigate(["/getAllCoupons"]);
  }
  /**
   * Delete selected coupon from the DB.
   */
  deleteCoupon() {
    this.companyService.getCouponById(this.id).subscribe(
      coupon => {
        this.coupon = coupon;
        this.companyService.removeCoupon(coupon).subscribe(
          wasRemoved => {
            if (wasRemoved) {
              alert("Coupon " + this.coupon.title + " was removed");
              this.router.navigate(["/getAllCoupons"]);
            } else {
              alert("Coupon was not removed!");
            }
          },
          error => {
            alert(error.error);
          }
        );
      },
      error => {
        alert(error.error);
      }
    );
  }
}
