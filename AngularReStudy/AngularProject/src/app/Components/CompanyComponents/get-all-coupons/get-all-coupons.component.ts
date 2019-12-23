import { Component, OnInit } from "@angular/core";
import { Coupon } from "src/app/Models/coupon";

import { CouponServiceService } from "src/app/Services/coupon.service";
import { CompanyService } from "src/app/Services/company.service";

@Component({
  selector: "app-get-all-coupons",
  templateUrl: "./get-all-coupons.component.html",
  styleUrls: ["./get-all-coupons.component.css"]
})
export class GetAllCouponsComponent implements OnInit {
  public coupons: Coupon[] = [];
  public filterElement: HTMLSelectElement;
  public filterType: string;
  public couponTypes: string[] = [];

  private filterDiv;
  private filterCheck: boolean;

  constructor(
    private companyService: CompanyService,
    private couponService: CouponServiceService
  ) {}
  /**
   * Get all coupons to show.
   */
  ngOnInit() {
    this.filterElement = document.getElementById(
      "filterType"
    ) as HTMLSelectElement;
    this.filterDiv = document.getElementById("filterDiv") as HTMLDivElement;

    this.companyService.getAllCoupons().subscribe(
      coupons => {
        this.coupons = coupons;
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
   * Change the filter type.
   */
  changeFilter() {
    this.filterType = this.filterElement.value;
  }

  /**
   * Get coupons filtered by selection.
   */
  filter() {
    let allCoupons;
    this.companyService.getAllCoupons().subscribe(
      coupons => {
        allCoupons = coupons;
        this.coupons = [];
        const numberFilter = document.getElementById(
          "numberFilter"
        ) as HTMLSelectElement;
        const typeFilter = document.getElementById(
          "typeFilter"
        ) as HTMLSelectElement;
        const dateFilter = document.getElementById(
          "dateFilter"
        ) as HTMLInputElement;
        switch (this.filterType) {
          case "id":
            const couponById = this.couponService.getCouponById(
              Number.parseInt(numberFilter.value, 10),
              allCoupons
            );
            this.coupons.push(couponById);
            break;
          case "type":
            this.companyService.getAllCouponsByType(typeFilter.value).subscribe(
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
              this.companyService
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
          case "date":
            if (new Date(dateFilter.value) != null) {
              this.companyService
                .getAllCouponsByDate(new Date(dateFilter.value))
                .subscribe(
                  couponsByDate => {
                    this.coupons = couponsByDate;
                  },
                  error => {
                    alert(error.error);
                  }
                );
            } else {
              this.coupons = allCoupons;
              alert("Please choose a date to filter up-to");
            }
            break;
          default:
            this.companyService.getAllCoupons().subscribe(
              coupons => {
                this.coupons = coupons;
              },
              error => {
                alert(error.error);
              }
            );
            break;
        }
      },
      error => {
        alert(error.error);
      }
    );
  }
}
