import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../_services/user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService,
    private router: Router) {
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
}