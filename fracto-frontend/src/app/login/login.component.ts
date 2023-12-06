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
    console.log('Entering login method...');
    this.userService.login(loginForm.value).subscribe(
      (response: any) => {
        console.log('Login successful. Response:', response);
        console.log('Roles received:', response.user.roles);
        this.userAuthService.setAuthenticated(true);
  
        const role = response.user.roles[0].name; // Ensure role is defined
        this.userAuthService.setRoles(response.user.roles);  
        
        console.log('Navigating to:', role === 'ROLE_ADMIN' ? '/admin' : '/user');
        if (role === 'ROLE_ADMIN') {
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/user']);
        }
      },
      (error) => {
        console.log('Login error:', error);
      }
    );
  }
}