import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  PATH_OF_API = 'http://localhost:8080/api/v1';

  constructor(
    private httpclient: HttpClient,
    private userAuthService: UserAuthService
  ) {}

  public login(loginData: any) {
    return this.httpclient.post(this.PATH_OF_API + '/login', loginData, {
    });
  }

  public roleMatch(allowedRoles: string[]): boolean {
    const userRoles: any = this.userAuthService.getRoles();
  
    if (userRoles != null && userRoles) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (userRoles[i].name === allowedRoles[j]) {
            return true;  // Found a match, no need to continue checking
          }
        }
      }
    }
  
    return false;  // No match found
  }
  
}