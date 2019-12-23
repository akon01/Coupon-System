import { Component, OnInit } from "@angular/core";
import { Company } from "src/app/Models/company";
import { AdminServiceService } from "src/app/Services/admin.service";
import { Router } from "@angular/router";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: "app-create-company",
  templateUrl: "./create-company.component.html",
  styleUrls: ["./create-company.component.css"]
})
export class CreateCompanyComponent implements OnInit {
  public newCompany = new Company();

  constructor(
    private adminService: AdminServiceService,
    private router: Router
  ) {}

  ngOnInit() {}

  /**
   * Add a new company to the DB.
   */
  addNewCompany() {
    this.adminService.createCompany(this.newCompany).subscribe(
      wasCreated => {
        if (wasCreated) {
          alert(
            "Company " + this.newCompany.compName + " was created sucssesfully"
          );
        } else {
          alert("Company was not created!");
        }
        this.router.navigate(["/companies"]);
      },
      error => {
        alert(error.error);
      }
    );
  }
}
