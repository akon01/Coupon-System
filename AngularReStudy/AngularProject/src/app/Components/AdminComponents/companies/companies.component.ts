import { Component, OnInit } from "@angular/core";
import { Company } from "src/app/Models/company";
import { AdminServiceService } from "src/app/Services/admin.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-companies",
  templateUrl: "./companies.component.html",
  styleUrls: ["./companies.component.css"]
})
export class CompaniesComponent implements OnInit {
  public companies: Company[] = [];

  constructor(
    private adminService: AdminServiceService,
    private router: Router
  ) {}
  /**
   * Get all current companies to show.
   */
  ngOnInit() {
    this.adminService.getAllCompanies().subscribe(
      companies => {
        this.companies = companies;
      },
      error => {
        alert(error.error);
      }
    );
  }
  /**
   *  Go to Company details.
   * @param companyId A company id to of the company to show.
   */
  showCompanyDetails(companyId: number) {
    this.router.navigate(["/showCompany/" + companyId]);
  }
}
