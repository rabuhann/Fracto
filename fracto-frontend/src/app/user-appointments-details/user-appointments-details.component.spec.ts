import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAppointmentsDetailsComponent } from './user-appointments-details.component';

describe('UserAppointmentsDetailsComponent', () => {
  let component: UserAppointmentsDetailsComponent;
  let fixture: ComponentFixture<UserAppointmentsDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserAppointmentsDetailsComponent]
    });
    fixture = TestBed.createComponent(UserAppointmentsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
