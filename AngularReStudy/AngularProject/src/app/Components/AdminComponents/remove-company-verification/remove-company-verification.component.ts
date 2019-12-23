import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { AdminServiceService } from "src/app/Services/admin.service";
import { Company } from "src/app/Models/company";

@Component({
  selector: "app-remove-company-verification",
  templateUrl: "./remove-company-verification.component.html",
  styleUrls: ["./remove-company-verification.component.css"]
})
export class RemoveCompanyVerificationComponent implements OnInit {
  public company = new Company();

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private adminService: AdminServiceService
  ) {}

  /**
   * get Selected company info from Db.
   */
  ngOnInit() {
    this.adminService
      .getCompanyById(this.activatedRoute.snapshot.params.id)
      .subscribe(company => {
        this.company = company;
      });
  }
  /**
   * Go to all companies.
   */
  goBackToAllCompanies() {
    this.router.navigate(["/companies"]);
  }
  /**
   * Delete the selected company.
   */
  deleteCompany() {
    this.adminService.removeCompany(this.company).subscribe(
      wasRemoved => {
        if (wasRemoved) {
          alert("Company " + this.company.compName + " was deleted");
        } else {
          alert("Company was not deleted");
        }
        this.router.navigate(["/companies"]);
      },
      error => {
        alert(error.error);
      }
    );
  }
}
