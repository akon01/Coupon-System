import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCouponPreviewComponent } from './admin-coupon-preview.component';

describe('AdminCouponPreviewComponent', () => {
  let component: AdminCouponPreviewComponent;
  let fixture: ComponentFixture<AdminCouponPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminCouponPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCouponPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
