import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerCouponPreviewComponent } from './customer-coupon-preview.component';

describe('CustomerCouponPreviewComponent', () => {
  let component: CustomerCouponPreviewComponent;
  let fixture: ComponentFixture<CustomerCouponPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerCouponPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerCouponPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
