import { Injectable } from "@angular/core";

import { Coupon } from "../Models/coupon";
import { locateHostElement } from "@angular/core/src/render3/instructions";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { Company } from "../Models/company";

@Injectable({
  providedIn: "root"
})
export class CompanyService {
  constructor(private httpClient: HttpClient) {}
  /**
   * Create a coupon in the DB.
   * @param coupon A coupon to create
   */
  createCoupon(coupon) {
    return this.httpClient.post(
      "http://localhost:8080/SpringApp/company/CreateCoupon",
      coupon
    );
  }
  /**
   * Remove a coupon from the DB.
   * @param coupon A coupon to remove
   */
  removeCoupon(coupon: Coupon): Observable<boolean> {
    return this.httpClient.post<boolean>(
      "http://localhost:8080/SpringApp/company/RemoveCoupon",
      coupon
    );
  }
  /**
   * Update a coupn info in the DB.
   * @param coupon A coupon with updated info to update.
   */
  updateCoupon(coupon: Coupon): Observable<boolean> {
    return this.httpClient.post<boolean>(
      "http://localhost:8080/SpringApp/company/UpdateCoupon",
      coupon
    );
  }
  /**
   * Get a coupon info from the DB.
   * @param id Id of the coupon to get from the DB.
   */
  getCouponById(id: Number): Observable<Coupon> {
    const param = new HttpParams().set("couponId", id.toString());
    return this.httpClient.get<Coupon>(
      "http://localhost:8080/SpringApp/company/GetCoupon",
      { params: param }
    );
  }
  /**
   * Get all of the company's coupons from the DB.
   */
  getAllCoupons(): Observable<Coupon[]> {
    return this.httpClient.get<Coupon[]>(
      "http://localhost:8080/SpringApp/company/GetAllCoupons"
    );
  }
  /**
   * Get all the coupons with the specified type from the DB.
   * @param type A type of coupon.
   */
  getAllCouponsByType(type: string): Observable<Coupon[]> {
    const param = new HttpParams().set("type", type);
    return this.httpClient.get<Coupon[]>(
      "http://localhost:8080/SpringApp/company/GetAllCouponsByType",
      { params: param }
    );
  }
  /**
   * Get all the coupons with price up-to seleced price.
   * @param price A price to filter up-to
   */
  getAllCouponsByPrice(price: number): Observable<Coupon[]> {
    const param = new HttpParams().set("price", price.toString());
    return this.httpClient.get<Coupon[]>(
      "http://localhost:8080/SpringApp/company/GetAllCouponsByPrice",
      { params: param }
    );
  }
  /**
   * Get all the coupon with End date of up-to selected date.
   * @param date A date to filter up-to
   */
  getAllCouponsByDate(date: Date): Observable<Coupon[]> {
    const param = new HttpParams().set("date", date.toString());
    return this.httpClient.get<Coupon[]>(
      "http://localhost:8080/SpringApp/company/GetAllCouponsByDate",
      { params: param }
    );
  }
  /**
   * Get current connected company info from DB.
   */
  getCurrentCompany(): Observable<Company> {
    return this.httpClient.get<Company>(
      "http://localhost:8080/SpringApp/company/MyInfo"
    );
  }
  /**
   * Get available coupon types from the DB.
   */
  getCouponTypes(): Observable<String[]> {
    return this.httpClient.get<String[]>(
      "http://localhost:8080/SpringApp/company/GetCouponTypes"
    );
  }
}
