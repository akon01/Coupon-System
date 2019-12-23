import { Coupon } from './coupon';

export class Customer {
  public id: any;
  public custName: string;
  public password: any;
  public coupons: Coupon[];

  constructor(id?: undefined, name?: undefined, password?: undefined, coupons?: Coupon[]) {
    this.id = id;
    this.custName = name;
    this.password = password;
    this.coupons = coupons;
  }
}
