import { Component, OnInit } from "@angular/core";
import { CompanyService } from "src/app/Services/company.service";
import { Company } from "src/app/Models/company";

@Component({
  selector: "app-my-info",
  templateUrl: "./my-info.component.html",
  styleUrls: ["./my-info.component.css"]
})
export class MyInfoComponent implements OnInit {
  private company: Company = new Company();

  constructor(private companyService: CompanyService) {}
  /**
   * Get the connected company info.
   */
  ngOnInit() {
    this.companyService.getCurrentCompany().subscribe(
      currentCompany => {
        this.company = currentCompany;
      },
      error => {
        alert(error.error);
      }
    );
  }
}
