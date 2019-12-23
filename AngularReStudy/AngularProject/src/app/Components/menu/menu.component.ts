import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { AdminServiceService } from "src/app/Services/admin.service";
import { Route } from "@angular/compiler/src/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-menu",
  templateUrl: "./menu.component.html",
  styleUrls: ["./menu.component.css"]
})
export class MenuComponent implements OnInit {
  public companyLoggedIn: boolean = false;
  public customerLoggedIn: boolean = false;
  public adminLoggedIn: boolean = false;

  public loginType: string = "";

  @Input("loginType") set LoginType(type: string) {
    this.loginType = type;
    this.checkType();
  }

  // tslint:disable-next-line: no-output-rename
  @Output("logout")
  logoutEvent: EventEmitter<boolean> = new EventEmitter();

  constructor(
    private adminService: AdminServiceService,
    private router: Router
  ) {}
  /**
   * Checks the type of the login.
   */
  checkType() {
    switch (this.loginType) {
      case "Admin":
        this.adminLoggedIn = true;
        this.companyLoggedIn = false;
        this.customerLoggedIn = false;
        break;
      case "Company":
        this.adminLoggedIn = false;
        this.companyLoggedIn = true;
        this.customerLoggedIn = false;
        break;
      case "Customer":
        this.adminLoggedIn = false;
        this.companyLoggedIn = false;
        this.customerLoggedIn = true;
        break;

      default:
        break;
    }
  }
  /**
   * Logout of the system.
   */
  logout() {
    this.adminLoggedIn = false;
    this.companyLoggedIn = false;
    this.customerLoggedIn = false;
    this.adminService.logout().subscribe(isLoggedOut => {
      this.router.navigate(["/login"]);
      this.logoutEvent.emit(true);
    });
  }

  ngOnInit() {
    this.adminLoggedIn = false;
    this.companyLoggedIn = false;
    this.customerLoggedIn = false;
  }
}
