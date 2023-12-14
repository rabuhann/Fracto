import { Component } from '@angular/core';
import { User, Role } from '../_classes/user';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent {

  user: User = new User();
  selectedRoles: Role[] = [];
  selectedRole: string = '';
  roles: string[] = ["ROLE_ADMIN", "ROLE_USER"];

  constructor(private userService: UserService, private router: Router) { }

  saveUser() {
    console.log("selected Role", this.selectedRole);
    console.log(this.selectedRoles);
  
    this.userService.createUser(this.user, this.selectedRole).subscribe(
      data => {
        console.log(data);
        this.goToUserList();
      },
      error => {
        console.error(error.error);
      }
    );
  }
  

  goToUserList() {
    this.router.navigate(['/admin']);
  }

  onSubmit() {
    console.log(this.user);
    this.saveUser();
  }
}