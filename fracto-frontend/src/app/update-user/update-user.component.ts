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
  
  constructor(private userService: UserService, private router: Router,
    private route: ActivatedRoute,) {  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.userService.getUserById(this.id).subscribe(data => {
      this.user = data;
    }, error => console.log(error));
  }

  // saveUser() {
  //   this.userService.createUser(this.user).subscribe( data => {
  //     console.log(data);
  //     this.goToUserList();
  //   } )
  // }

  onSubmit() {
    this.userService.updateUser(this.id, this.user).subscribe( data =>{
      this.goToUserList();
    }
    , error => console.log(error));
  }

  goToUserList() {
    this.router.navigate(['/admin']);
  }

}