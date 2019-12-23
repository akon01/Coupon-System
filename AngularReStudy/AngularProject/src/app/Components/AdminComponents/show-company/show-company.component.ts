import { Component, OnInit } from "@angular/core";
import { Company } from "src/app/Models/company";
import { AdminServiceService } from "src/app/Services/admin.service";
import { ActivatedRoute } from "@angular/router";
import { Coupon } from "src/app/Models/coupon";

@Component({
  selector: "app-show-company",
  templateUrl: "./show-company.component.html",
  styleUrls: ["./show-company.component.css"]
})
export class ShowCompanyComponent implements OnInit {
  public company: Company = new Company(0, "", "", "");
  public companyCoupons: Coupon[];

  constructor(
    private adminService: AdminServiceService,
    private activateRoute: ActivatedRoute
  ) {}

  /**
   * Get selected company to show.
   */
  ngOnInit() {
    this.adminService
      .getCompanyById(this.activateRoute.snapshot.params.id)
      .subscribe(
        company => {
          this.company = company;
          this.companyCoupons = this.company.coupons;
        },
        error => {
          alert(error.error);
        }
      );
  }
}
