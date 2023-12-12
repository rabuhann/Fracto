import { Component } from '@angular/core';
import { User, Role } from '../user';
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
  selectedRole: Role | null = null;
  roles: Role[] = [{ id: 1, name: "ROLE_ADMIN" }, { id: 2, name: "ROLE_USER" }];

  constructor(private userService: UserService, private router: Router) { }

  saveUser() {
    console.log("selected Role", this.selectedRole);
    if (this.selectedRole) {
      this.selectedRoles.push(this.selectedRole);
    }
  
    console.log(this.selectedRoles);
    this.user.roles = this.selectedRoles;
  
    this.userService.createUser(this.user).subscribe(
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