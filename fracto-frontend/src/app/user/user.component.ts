import { Component } from '@angular/core';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  public email = '';
  constructor(
    private userAuthService: UserAuthService
  ) {
    this.email = this.userAuthService.getUserEmail();
   }
 

}
