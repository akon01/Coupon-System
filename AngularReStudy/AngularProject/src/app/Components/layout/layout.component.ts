import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-layout",
  templateUrl: "./layout.component.html",
  styleUrls: ["./layout.component.css"]
})
export class LayoutComponent implements OnInit {
  public loginType: string = "guest";
  public loggedIn: boolean = false;

  constructor() {}

  ngOnInit() {}

  /**
   * Sets the login.
   * @param loginType Client type to change to.
   */
  changeLogin(loginType) {
    this.loggedIn = true;
    this.loginType = loginType;
  }
  /**
   * Logut of the system.
   */
  logout() {
    this.loggedIn = false;
    this.loginType = "guest";
  }
}
