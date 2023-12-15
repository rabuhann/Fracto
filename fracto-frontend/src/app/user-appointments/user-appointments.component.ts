import { Component } from '@angular/core';
import { Appointment } from '../_classes/appointment';
import { AppointmentService } from '../_services/appointment.service';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-user-appointments',
  templateUrl: './user-appointments.component.html',
  styleUrls: ['./user-appointments.component.css']
})

export class UserAppointmentsComponent {

  appointments: Appointment[];

  constructor(private appointmentService: AppointmentService, private userAuthService:UserAuthService,
    private router: Router) {
    this.appointments = [];
  }

  ngOnInit(): void {
    // console.log(this.userAuthService.getUserId());
    this.getAppointments();
  }

  private getAppointments() {
    console.log('user id ', this.userAuthService.getUserId());
    this.appointmentService.getAppointmentsList(this.userAuthService.getUserId()).subscribe((data: Appointment[]) => {
      console.log(data);  // Log data to console
      this.appointments = data;
    });
  }

  cancelAppointment(id: number){
    console.log(id);
    this.appointmentService.cancelAppointment(id).subscribe( (data: any) => {
      console.log(data);
      this.getAppointments();
    })
  }

}
