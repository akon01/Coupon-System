import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveCompanyVerificationComponent } from './remove-company-verification.component';

describe('RemoveCompanyVerificationComponent', () => {
  let component: RemoveCompanyVerificationComponent;
  let fixture: ComponentFixture<RemoveCompanyVerificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemoveCompanyVerificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemoveCompanyVerificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
