import { Component, OnInit } from '@angular/core';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-test',
  template: `
    <div>
      <p>Is Authenticated: {{ isAuthenticated }}</p>
      <p>Roles: {{ roles | json }}</p>
    </div>
  `,
})
export class TestComponent implements OnInit {
  isAuthenticated = false;
  roles: string[] = [];

  constructor(private userAuthService: UserAuthService) {}

  ngOnInit() {
    this.isAuthenticated = this.userAuthService.isAuthenticated();
    this.roles = this.userAuthService.getRoles();
  }
}