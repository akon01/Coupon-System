import { Component, OnInit } from "@angular/core";
import { Customer } from "src/app/Models/customer";
import { ActivatedRoute } from "@angular/router";
import { AdminServiceService } from "src/app/Services/admin.service";

@Component({
  selector: "app-update-customer",
  templateUrl: "./update-customer.component.html",
  styleUrls: ["./update-customer.component.css"]
})
export class UpdateCustomerComponent implements OnInit {
  public customer: Customer = new Customer();

  constructor(
    private activatedRoute: ActivatedRoute,
    private adminService: AdminServiceService
  ) {}
  /**
   * Get selected customer from DB.
   */
  ngOnInit() {
    this.adminService
      .getCustomerById(this.activatedRoute.snapshot.params.id)
      .subscribe(
        customer => {
          this.customer = customer;
        },
        error => {
          alert(error.error);
        }
      );
  }
  /**
   * Update selected customer.
   */
  updateCustomer() {
    this.adminService.updateCustomer(this.customer).subscribe(
      wasUpdated => {
        if (wasUpdated) {
          alert("Customer " + this.customer.custName + " was updated");
        } else {
          alert("Company " + this.customer.custName + " was not updated");
        }
      },
      error => {
        alert(error.error);
      }
    );
  }
}
