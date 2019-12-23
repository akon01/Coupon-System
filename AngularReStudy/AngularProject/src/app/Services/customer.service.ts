import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Coupon } from "../Models/coupon";
import { Customer } from "../Models/customer";

@Injectable({
  providedIn: "root"
})
export class CustomerService {
  constructor(private httpClient: HttpClient) {}

  /**
   * Purchase a coupon.
   * @param coupon A coupn to purchase
   */
  purchaseCoupon(coupon: Coupon) {
    return this.httpClient.post(
      "http://localhost:8080/SpringApp/customer/purchase",
      coupon
    );
  }
  /**
   * Get all of the connected customer purchased coupons.
   */
  getAllPurchasedCoupons() {
    return this.httpClient.get<Coupon[]>(
      "http://localhost:8080/SpringApp/customer/MyCoupons"
    );
  }
  /**
   * Get all coupons of the specified type.
   * @param type A type of the requested coupns.
   */
  getAllCouponsByType(type: string) {
    const param = new HttpParams().set("type", type);
    return this.httpClient.get<Coupon[]>(
      "http://localhost:8080/SpringApp/customer/MyCouponsByType",
      { params: param }
    );
  }
  /**
   * Get all coupons filtered up-to a specified price.
   * @param price A price of the coupons to filter up-to.
   */
  getAllCouponsByPrice(price: number) {
    const param = new HttpParams().set("price", price.toString());
    return this.httpClient.get<Coupon[]>(
      "http://localhost:8080/SpringApp/customer/MyCouponsByPrice",
      { params: param }
    );
  }
  /**
   * Get all available coupons from the DB.
   */
  getAvailableCoupon() {
    return this.httpClient.get<Coupon[]>(
      "http://localhost:8080/SpringApp/customer/AvailableCoupons"
    );
  }
  /**
   * Get connected customer info from DB.
   */
  getCurrentCustomer() {
    return this.httpClient.get<Customer>(
      "http://localhost:8080/SpringApp/customer/MyInfo"
    );
  }
}
