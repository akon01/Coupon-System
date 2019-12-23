import { Injectable } from "@angular/core";
import { Company } from "../Models/company";
import { Coupon } from "../Models/coupon";
import { Customer } from "../Models/customer";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class AdminServiceService {
  private companies: Company[] = [];

  public customers: Customer[] = [];

  constructor(private httpClient: HttpClient) {}
  /**
   * Send a logout request to Server.
   */
  logout() {
    return this.httpClient.get("http://localhost:8080/SpringApp/logout");
  }
  /**
   * Send a company to create in the DB.
   * @param company A company to create.
   */
  createCompany(company: Company) {
    return this.httpClient.post<boolean>(
      "http://localhost:8080/SpringApp/admin/CreateCompany",
      company
    );
  }

  /**
   * Get all coupons from DB.
   */
  getAllCoupons() {
    return this.httpClient.get<Coupon[]>(
      "http://localhost:8080/SpringApp/admin/GetAllCoupons"
    );
  }
  /**
   * Remove a company from the DB.
   * @param company A company to remove
   */
  removeCompany(company: Company): Observable<boolean> {
    return this.httpClient.post<boolean>(
      "http://localhost:8080/SpringApp/admin/RemoveCompany",
      company
    );
  }
  /**
   * Update a company in the DB.
   * @param company A company to update with updated info.
   */
  updateCompany(company: Company): Observable<boolean> {
    return this.httpClient.post<boolean>(
      "http://localhost:8080/SpringApp/admin/UpdateCompany",
      company
    );
  }
  /**
   * Get a company info from the DB.
   * @param id The id of the company to get from the DB.
   */
  getCompanyById(id: Number): Observable<Company> {
    const param = new HttpParams().set("id", id.toString());
    return this.httpClient.get<Company>(
      "http://localhost:8080/SpringApp/admin/GetCompany",
      { params: param }
    );
  }
  /**
   * Get all companies from the DB.
   */
  getAllCompanies(): Observable<Company[]> {
    return this.httpClient.get<Company[]>(
      "http://localhost:8080/SpringApp/admin/GetAllCompanies"
    );
  }
  /**
   * Create a new customer in the DB.
   * @param customer A customer to create.
   */
  createCustomer(customer: Customer): Observable<boolean> {
    return this.httpClient.post<boolean>(
      "http://localhost:8080/SpringApp/admin/CreateCustomer",
      customer
    );
  }
  /**
   * Removes a customer from the DB.
   * @param customer A customer to remove.
   */
  removeCustomer(customer: Customer): Observable<boolean> {
    return this.httpClient.post<boolean>(
      "http://localhost:8080/SpringApp/admin/RemoveCustomer",
      customer
    );
  }
  /**
   * Update a customer info in the DB.
   * @param customer A customer with updated info to update.
   */
  updateCustomer(customer: Customer): Observable<boolean> {
    return this.httpClient.post<boolean>(
      "http://localhost:8080/SpringApp/admin/UpdateCustomer",
      customer
    );
  }
  /**
   * Get a customer info from the DB.
   * @param id Id of the customer to get from the DB.
   */
  getCustomerById(id: Number): Observable<Customer> {
    const param = new HttpParams().set("id", id.toString());
    return this.httpClient.get<Customer>(
      "http://localhost:8080/SpringApp/admin/GetCustomer",
      { params: param }
    );
  }
  /**
   * Get all customers from the DB.
   */
  getAllCustomers(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(
      "http://localhost:8080/SpringApp/admin/GetAllCustomers"
    );
  }
}
