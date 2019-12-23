import { Component, OnInit, Input } from "@angular/core";
import { Coupon } from "src/app/Models/coupon";
import { ActivatedRoute } from "@angular/router";
import { CouponServiceService } from "src/app/Services/coupon.service";
import { CompanyService } from "src/app/Services/company.service";

@Component({
  selector: "app-coupon-preview",
  templateUrl: "./coupon-preview.component.html",
  styleUrls: ["./coupon-preview.component.css"]
})
export class CouponPreviewComponent implements OnInit {
  public coupon: Coupon = new Coupon();

  public coupons: Coupon[] = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    private companyService: CompanyService
  ) {}
  /**
   * Get selected coupon info to preview.
   */
  ngOnInit() {
    let id = this.activatedRoute.snapshot.params.id;
    this.companyService.getAllCoupons().subscribe(
      coupons => {
        this.coupons = coupons;
        for (const coupon of this.coupons) {
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
