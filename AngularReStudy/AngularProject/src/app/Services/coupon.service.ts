import { Injectable } from "@angular/core";
import { Coupon } from "../Models/coupon";

@Injectable({
  providedIn: "root"
})
export class CouponServiceService {
  constructor() {}

  /**
   * Get a coupon from a list of coupons by its id.
   * @param id THe id of the requested coupon.
   * @param coupons All coupons to select from.
   */
  getCouponById(id: number, coupons: Coupon[]): Coupon {
    return coupons.find(coupon => coupon.id == id);
  }
  /**
   * Get all types from a list of coupons
   * @param coupons Coupons to get types from
   */
  getAllCouponsTypes(coupons: Coupon[]) {
    const types: string[] = [];
    for (const coupon of coupons) {
      if (types.length == 0) {
        types.push(coupon.type);
      } else {
        for (const type of types) {
          if (type == coupon.type) {
            break;
          }
          types.push(coupon.type);
        }
      }
    }
    return types;
  }
}
