import { Component, OnInit } from "@angular/core";
import { Customer } from "src/app/Models/customer";
import { AdminServiceService } from "src/app/Services/admin.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-create-customer",
  templateUrl: "./create-customer.component.html",
  styleUrls: ["./create-customer.component.css"]
})
export class CreateCustomerComponent implements OnInit {
  public newCustomer: Customer = new Customer();

  constructor(
    private adminService: AdminServiceService,
    private router: Router
  ) { }

  ngOnInit() { }

  /**
   * Adds a new customer to the DB.
   */
  addNewcustomer() {
    this.adminService.createCustomer(this.newCustomer).subscribe(
      wasCreated => {
        if (wasCreated) {
          alert("Customer was created sucssesfully");
        }
        this.router.navigate(["/customers"]);
      },
      error => {
        alert(error.error);
      }
    );
  }
}
