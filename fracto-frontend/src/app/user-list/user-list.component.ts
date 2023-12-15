import { Component, OnInit } from '@angular/core';
import { User } from '../_classes/user';
import { UserService } from '../_services/user.service';
import { Router } from '@angular/router';
import { Appointment } from '../_classes/appointment';
import { AppointmentService } from '../_services/appointment.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];
  appointments: Appointment[];

  constructor(private userService: UserService, private appointmentService: AppointmentService,
    private router: Router) {
    this.appointments = [];
    this.users = [];
  }

  ngOnInit(): void {
    this.getUsers();
  }

  private getUsers() {
    this.userService.getUsersList().subscribe((data: User[]) => {
      console.log(data);  // Log data to console
      this.users = data;
    });
  }

  userDetails(id: number) {
    this.router.navigate(['user-details', id]);
  }

  updateUser(id: number) {
    this.router.navigate(['update-user', id]);
  }

  deleteUser(id: number){
    this.userService.deleteUser(id).subscribe( (data: any) => {
      console.log(data);
      this.getUsers();
    })
  }

  getAppointments(id: number) {
    this.router.navigate(['user-appointments-details', id]);
  }
}