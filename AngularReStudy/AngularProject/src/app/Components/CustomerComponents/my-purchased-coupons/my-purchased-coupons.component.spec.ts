import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyPurchasedCouponsComponent } from './my-purchased-coupons.component';

describe('MyPurchasedCouponsComponent', () => {
  let component: MyPurchasedCouponsComponent;
  let fixture: ComponentFixture<MyPurchasedCouponsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyPurchasedCouponsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyPurchasedCouponsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
