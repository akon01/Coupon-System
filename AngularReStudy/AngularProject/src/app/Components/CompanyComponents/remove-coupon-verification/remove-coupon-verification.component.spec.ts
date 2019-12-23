import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveCouponVerificationComponent } from './remove-coupon-verification.component';

describe('RemoveCouponVerificationComponent', () => {
  let component: RemoveCouponVerificationComponent;
  let fixture: ComponentFixture<RemoveCouponVerificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemoveCouponVerificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemoveCouponVerificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
