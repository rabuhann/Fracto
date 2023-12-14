import { Component } from '@angular/core';
import { Appointment } from '../_classes/appointment';
import { AppointmentService } from '../_services/appointment.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-appointments',
  templateUrl: './user-appointments.component.html',
  styleUrls: ['./user-appointments.component.css']
})

export class UserAppointmentsComponent {

  appointments: Appointment[];

  constructor(private appointmentService: AppointmentService,
    private router: Router) {
    this.appointments = [];
  }

  ngOnInit(): void {
    this.getAppointments();
  }

  private getAppointments() {
    this.appointmentService.getAppointmentsList(1).subscribe((data: Appointment[]) => {
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
