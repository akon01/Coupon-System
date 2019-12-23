import { Component, OnInit } from "@angular/core";
import { Coupon } from "src/app/Models/coupon";
import { ActivatedRoute, Router } from "@angular/router";
import { CouponServiceService } from "src/app/Services/coupon.service";
import { CompanyService } from "src/app/Services/company.service";

@Component({
  selector: "app-update-coupon",
  templateUrl: "./update-coupon.component.html",
  styleUrls: ["./update-coupon.component.css"]
})
export class UpdateCouponComponent implements OnInit {
  private coupon: Coupon = new Coupon();
  private id;

  constructor(
    private activatedRoute: ActivatedRoute,
    private companyService: CompanyService,
    private router: Router
  ) {}
  /**
   * Get coupon info form DB.
   */
  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params.id;
    this.companyService.getCouponById(this.id).subscribe(
      coupon => {
        this.coupon = coupon;
      },
      error => {
        alert(error.error);
      }
    );
  }
  /**
   * Update selected coupon.
   */
  updateCoupon() {
    this.companyService.updateCoupon(this.coupon).subscribe(
      wasUpdated => {
        if (wasUpdated) {
          alert("Coupon " + this.coupon.title + " was updated!");
          this.router.navigate(["/getAllCoupons"]);
        } else {
          alert("Coupon was not updated!");
        }
      },
      error => {
        alert(error.error);
      }
    );
  }
}
