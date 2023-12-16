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
  msg:string="";
  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router
  ) { }

  ngOnInit(): void { }

  login(loginForm: NgForm) {
    this.userService.login(loginForm.value).subscribe(
      (response: any) => {
        console.log(response.message);
        if(response.user !=null){
          this.userAuthService.setRoles(response.user.roles);
        /* */
        this.userAuthService.setUserId(response.user.id);
        //console.log(this.userService.getToken());
        /* */
          const role = response.user.roles[0].name;
        //console.log(role)
        if (role === "ROLE_ADMIN") {
          this.router.navigate(['/admin']);
          this.userAuthService.setAuthenticated(true);
        } else {
          this.router.navigate(['/user']);
          this.userAuthService.setAuthenticated(true);
          }
        } else{
          console.log(response.message);
          this.msg = response.message;
        }
        
      },
      (error) => {
        console.log(error);
      }
    );
  }
}