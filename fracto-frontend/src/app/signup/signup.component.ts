import { Component } from '@angular/core';
import { User } from '../_classes/user';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  user: User = new User();
  constructor(private userService: UserService, private router: Router) {  }

  ngOnInit(): void {
  }

  saveUser() {
    this.userService.signUp(this.user).subscribe(
      data => {
        console.log(data);
        this.goToLogin();
      },
      error => {
        console.error(error.error); // Access the error message from the response
      }
    );
    
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }

  onSubmit() {
    console.log(this.user);
    this.saveUser();
  }

}