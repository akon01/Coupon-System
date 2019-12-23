import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { LoginRequest } from "../Models/login-request";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class LoginService {
  constructor(private httpClient: HttpClient) {}
  /**
   * Send a login request to the server.
   * @param loginRequest A login request with name,pass and type.
   */
  login(loginRequest: LoginRequest): Observable<boolean> {
    let isLoggedIn = this.httpClient.post<boolean>(
      "http://localhost:8080/SpringApp/login",
      loginRequest
    );
    return isLoggedIn;
  }
}
