import { Component, OnInit } from "@angular/core";
import { Customer } from "src/app/Models/customer";
import { AdminServiceService } from "src/app/Services/admin.service";

@Component({
  selector: "app-customers",
  templateUrl: "./customers.component.html",
  styleUrls: ["./customers.component.css"]
})
export class CustomersComponent implements OnInit {
  public customers: Customer[] = [];

  constructor(private adminService: AdminServiceService) {}

  /**
   * Get all current customers to show.
   */
  ngOnInit() {
    this.adminService.getAllCustomers().subscribe(
      customers => {
        this.customers = customers;
      },
      error => {
        alert(error.error);
      }
    );
  }
}
