import { Component, OnInit } from "@angular/core";
import { Company } from "src/app/Models/company";
import { AdminServiceService } from "src/app/Services/admin.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: "app-update-company",
  templateUrl: "./update-company.component.html",
  styleUrls: ["./update-company.component.css"]
})
export class UpdateCompanyComponent implements OnInit {
  public company: Company = new Company();

  constructor(
    private activatedRoute: ActivatedRoute,
    private adminService: AdminServiceService,
    private router: Router
  ) {}

  /**
   * Get selected company info from DB.
   */
  ngOnInit() {
    this.adminService
      .getCompanyById(this.activatedRoute.snapshot.params.id)
      .subscribe(
        company => {
          this.company = company;
        },
        error => {
          alert(error.error);
        }
      );
  }
  /**
   * Update selected comapny.
   */
  updateCompany() {
    this.adminService.updateCompany(this.company).subscribe(
      wasUpdated => {
        if (wasUpdated) {
          alert("Company " + this.company.compName + " was updated");
        } else {
          alert("Company " + this.company.compName + " was not updated");
        }
        this.router.navigate(["/companies"]);
      },
      error => {
        alert(error.error);
      }
    );
  }
}
