import { Component, OnInit } from "@angular/core";
import { Customer } from "src/app/Models/customer";
import { Coupon } from "src/app/Models/coupon";
import { AdminServiceService } from "src/app/Services/admin.service";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-show-customer",
  templateUrl: "./show-customer.component.html",
  styleUrls: ["./show-customer.component.css"]
})
export class ShowCustomerComponent implements OnInit {
  public customer: Customer = new Customer();
  public customerCoupons: Coupon[] = [];

  constructor(
    private adminService: AdminServiceService,
    private activateRoute: ActivatedRoute
  ) {}

  /**
   * Get selected customer to show.
   */
  ngOnInit() {
    this.adminService
      .getCustomerById(this.activateRoute.snapshot.params.id)
      .subscribe(
        customer => {
          this.customer = customer;
          this.customerCoupons = this.customer.coupons;
        },
        error => {
          alert(error.error);
        }
      );
  }
}
