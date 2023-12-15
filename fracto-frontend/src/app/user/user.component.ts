import { Component } from '@angular/core';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  public user_id!:number;
  constructor(
    private userAuthService: UserAuthService
  ) {
    this.user_id = this.userAuthService.getUserId();
    console.log(this.user_id)+"user id";
   }
 

}
