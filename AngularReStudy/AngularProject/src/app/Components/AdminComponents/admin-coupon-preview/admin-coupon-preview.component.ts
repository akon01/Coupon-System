import { Component, OnInit } from "@angular/core";
import { AdminServiceService } from "src/app/Services/admin.service";
import { Coupon } from "src/app/Models/coupon";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-admin-coupon-preview",
  templateUrl: "./admin-coupon-preview.component.html",
  styleUrls: ["./admin-coupon-preview.component.css"]
})
export class AdminCouponPreviewComponent implements OnInit {
  public coupon: Coupon = new Coupon();

  public coupons: Coupon[] = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    private adminService: AdminServiceService
  ) {}

  /**
   * Get current coupon to preview
   */
  ngOnInit() {
    let id = this.activatedRoute.snapshot.params.id;
    this.adminService.getAllCoupons().subscribe(
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
