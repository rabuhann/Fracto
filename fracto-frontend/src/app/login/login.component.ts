import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router
  ) { }

  ngOnInit(): void { }

  login(loginForm: NgForm) {
    this.userService.login(loginForm.value).subscribe(
      (response: any) => {
        this.userAuthService.setRoles(response.user.roles);
        const role = response.user.roles[0].name;
        if (role === 'ROLE_ADMIN') {
          this.router.navigate(['/admin']);
          this.userAuthService.setAuthenticated(true);
        } else {
          this.router.navigate(['/user']);
          this.userAuthService.setAuthenticated(true);
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }
}