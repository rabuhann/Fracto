import { Component } from '@angular/core';
import { Appointment } from '../_classes/appointment';
import { AppointmentService } from '../_services/appointment.service';
import { Router } from '@angular/router';
import { User, Role } from '../_classes/user';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-user-appointments-details',
  templateUrl: './user-appointments-details.component.html',
  styleUrls: ['./user-appointments-details.component.css']
})
export class UserAppointmentsDetailsComponent {

  id!: number;
  appointments: Appointment[];
  user!: User
  role!: Role

  constructor(private route: ActivatedRoute, private appointmentService: AppointmentService,
    private router: Router, private userService: UserService) {
    this.appointments = [];
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.user = new User();

    this.getAppointments(this.id);
  }

  private getAppointments(id: number) {
    this.appointmentService.getAppointmentsList(id).subscribe((data: Appointment[]) => {
      console.log(data);  // Log data to console
      this.appointments = data;
    });
  }

  cancelAppointment(id: number){
    // console.log(id);
    // this.appointmentService.cancelAppointment(id).subscribe( (data: any) => {
    //   console.log(data);
    //   this.getAppointments();
    // })
  }

}
