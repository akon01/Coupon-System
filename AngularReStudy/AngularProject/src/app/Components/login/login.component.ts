import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { LoginRequest } from "src/app/Models/login-request";
import { LoginService } from "src/app/Services/login.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
  private types: string[] = ["Admin", "Company", "Customer"];

  private loginRequest: LoginRequest = new LoginRequest();

  // tslint:disable-next-line: no-output-rename
  @Output("loginType")
  loginType: EventEmitter<string> = new EventEmitter();

  constructor(private loginService: LoginService, private router: Router) {}

  ngOnInit() {}
  /**
   * Try to login as one of the clients.
   */
  tryLogin() {
    let isLoggedIn = false;
    this.loginService.login(this.loginRequest).subscribe(
      isLogged => {
        if (isLogged) {
          this.router.navigate(["home"]);
          this.loginType.emit(this.loginRequest.getType());
        }
      },
      error => {
        alert(error.error);
      }
    );
  }
}
