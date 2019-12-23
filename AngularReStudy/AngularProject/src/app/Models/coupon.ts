export class Coupon {
  public id: number;
  public title: string;
  public message: string;
  public image: string;
  public startDate: Date;
  public endDate: Date;
  public amount: number;
  public price: number;
  public type: string;

  public constructor(
    id?: number,
    title?: string,
    message?: string,
    image?: string,
    startDate?: Date,
    endDate?: Date,
    amount?: number,
    price?: number,
    type?: string
  ) {
    this.id = id;
    this.title = title;
    this.amount = amount;
    this.endDate = endDate;
    this.image = image;
    this.price = price;
    this.startDate = startDate;
    this.type = type;
    this.message = message;
  }
}
