import { Component, OnInit } from "@angular/core";
import { Coupon } from "src/app/Models/coupon";

import { CompanyService } from "src/app/Services/company.service";

@Component({
  selector: "app-create-coupon",
  templateUrl: "./create-coupon.component.html",
  styleUrls: ["./create-coupon.component.css"]
})
export class CreateCouponComponent implements OnInit {
  private minDate;
  private newCoupon = new Coupon();
  public types = [];

  constructor(private companyService: CompanyService) {}
  /**
   * get available coupon types from the DB.
   */
  ngOnInit() {
    this.minDate = new Date();
    this.companyService.getCouponTypes().subscribe(
      types => {
        this.types = types;
      },
      error => {
        alert(error.error);
      }
    );
  }

  /**
   * Create a new coupon by the connected comapny.
   */
  addNewCoupon() {
    this.newCoupon.startDate = new Date();
    this.companyService.createCoupon(this.newCoupon).subscribe(
      wasCreated => {
        if (wasCreated) {
          alert("Coupon " + this.newCoupon.title + " was created sucssesfully");
        }
      },
      error => {
        alert(error.error);
      }
    );
  }
}
