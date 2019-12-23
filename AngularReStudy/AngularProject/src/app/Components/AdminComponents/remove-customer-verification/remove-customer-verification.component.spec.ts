import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveCustomerVerificationComponent } from './remove-customer-verification.component';

describe('RemoveCustomerVerificationComponent', () => {
  let component: RemoveCustomerVerificationComponent;
  let fixture: ComponentFixture<RemoveCustomerVerificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemoveCustomerVerificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemoveCustomerVerificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
