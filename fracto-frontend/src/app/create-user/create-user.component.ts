import { Component } from '@angular/core';
import { User } from '../user';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent {

  user: User = new User();
  constructor(private userService: UserService, private router: Router) {  }

  ngOnInit(): void {
  }

  saveUser() {
    this.userService.signUp(this.user).subscribe(
      data => {
        console.log(data);
        this.goToUserList();
      },
      error => {
        console.error(error.error); // Access the error message from the response
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