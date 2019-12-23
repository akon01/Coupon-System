import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { AdminServiceService } from "src/app/Services/admin.service";
import { Customer } from "src/app/Models/customer";
import { isRegExp } from "util";

@Component({
  selector: "app-remove-customer-verification",
  templateUrl: "./remove-customer-verification.component.html",
  styleUrls: ["./remove-customer-verification.component.css"]
})
export class RemoveCustomerVerificationComponent implements OnInit {
  public customer: Customer = new Customer();

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private adminService: AdminServiceService
  ) {}

  ngOnInit() {
    this.adminService
      .getCustomerById(this.activatedRoute.snapshot.params.id)
      .subscribe(customer => {
        this.customer = customer;
      });
  }

  goBackToAllCustomers() {
    this.router.navigate(["/customers"]);
  }

  deleteCustomer() {
    let wasCustomerRemoved;
    this.adminService.removeCustomer(this.customer).subscribe(
      wasRemoved => {
        wasCustomerRemoved = wasRemoved;
        if (wasCustomerRemoved)
          alert(
            "Customer " + this.customer.custName + " was removed sucssesfully"
          );
        this.router.navigate(["/customers"]);
      },
      error => {
        alert(error.error);
      }
    );
    //if(wasCustomerRemoved){
    //  console.log('Customer '+this.customer.custName+' was removed sucssesfully')
    //} else {
    //  console.log('No customer was deleted');

    //}
    //this.router.navigate(["/customers"]);
  }
}
