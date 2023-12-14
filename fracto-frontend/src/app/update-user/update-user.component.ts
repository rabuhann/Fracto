import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { User } from '../_classes/user';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  id!: number;
  user!: User;
  selectedRole: string = '';
  roles: string[] = ["ROLE_ADMIN", "ROLE_USER"];
  
  constructor(private userService: UserService, private router: Router,
    private route: ActivatedRoute,) {  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.userService.getUserById(this.id).subscribe(data => {
      this.user = data;
    }, error => console.log(error));
  }

  onSubmit() {
    this.userService.updateUser(this.id, this.user, this.selectedRole).subscribe( data =>{
      this.goToUserList();
    }
    , error => console.log(error));
  }

  goToUserList() {
    this.router.navigate(['/admin']);
  }

}