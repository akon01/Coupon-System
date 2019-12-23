import { Component, OnInit } from "@angular/core";
import { Coupon } from "src/app/Models/coupon";
import { CouponServiceService } from "src/app/Services/coupon.service";
import { CustomerService } from "src/app/Services/customer.service";

@Component({
  selector: "app-my-purchased-coupons",
  templateUrl: "./my-purchased-coupons.component.html",
  styleUrls: ["./my-purchased-coupons.component.css"]
})
export class MyPurchasedCouponsComponent implements OnInit {
  public coupons: Coupon[] = [];
  public filterElement: HTMLSelectElement;
  public filterType: string;
  public couponTypes;
  private filterCheck: boolean;
  private filterDiv;

  constructor(
    private customerService: CustomerService,
    private couponService: CouponServiceService
  ) {}
  /**
   * Get all purchased coupons from the DB.
   */
  ngOnInit() {
    this.filterElement = document.getElementById(
      "filterType"
    ) as HTMLSelectElement;
    this.filterDiv = document.getElementById("filterDiv") as HTMLDivElement;
    this.filterType = this.filterElement.value;
    this.customerService.getAllPurchasedCoupons().subscribe(
      purchasedCoupons => {
        this.coupons = purchasedCoupons;
        this.couponTypes = this.couponService.getAllCouponsTypes(this.coupons);
      },
      error => {
        alert(error.error);
      }
    );
  }

  /**
   * Toggle filter option.
   */
  toggleFilter() {
    if (!this.filterCheck) {
      this.filterDiv.hidden = false;
      this.filterType = this.filterElement.value;
    } else {
      this.filterDiv.hidden = true;
      this.filterType = null;
      this.filter();
    }
  }
  /**
   * Change filter type.
   */
  changeFilter() {
    this.filterType = this.filterElement.value;
  }
  /**
   * Filter all purchased coupons by selection.
   */
  filter() {
    let allCoupons;
    this.customerService.getAllPurchasedCoupons().subscribe(
      purchasedCoupons => {
        allCoupons = purchasedCoupons;
        this.coupons = [];
        const numberFilter = document.getElementById(
          "numberFilter"
        ) as HTMLSelectElement;
        const typeFilter = document.getElementById(
          "typeFilter"
        ) as HTMLSelectElement;
        switch (this.filterType) {
          case "type":
            this.customerService
              .getAllCouponsByType(typeFilter.value)
              .subscribe(
                couponsByType => {
                  this.coupons = couponsByType;
                },
                error => {
                  alert(error.error);
                }
              );
            break;
          case "price":
            if (!isNaN(Number.parseInt(numberFilter.value, 10))) {
              this.customerService
                .getAllCouponsByPrice(Number.parseInt(numberFilter.value, 10))
                .subscribe(
                  couponsByPrice => {
                    this.coupons = couponsByPrice;
                  },
                  error => {
                    alert(error.error);
                  }
                );
            } else {
              this.coupons = allCoupons;
              alert("Please choose a price to filter up-to");
            }
            break;
          default:
            break;
        }
      },
      error => {
        alert(error.error);
      }
    );
  }
}
