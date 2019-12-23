import { Coupon } from "./coupon";

export class Company {
  public id: number;
  public compName: string;
  public password: string;
  public email: string;
  public coupons: Coupon[] = [];

  constructor(id?: number, name?: string, password?: string, email?: string, coupons?: Coupon[]) {
    this.id = id;
    this.compName = name;
    this.password = password;
    this.email = email;
    this.coupons = coupons;
  }
}
