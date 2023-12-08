import { Component } from '@angular/core';
import { User, Role } from '../user';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent {

  id!: number;
  user!: User
  role!: Role
  constructor(private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.user = new User();
    this.userService.getUserById(this.id).subscribe( data => {
      this.user = data;
    });

    this.role = new Role();
    this.userService.getRole(this.id).subscribe( data => {
      this.role = data;
    });
  }

}
