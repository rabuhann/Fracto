import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  public setRoles(roles: []) {
    localStorage.setItem("roles", JSON.stringify(roles));
  }

  public getRoles(): string[] {
    const rolesJson = localStorage.getItem("roles") ?? "[]"; // Use an empty array as a default if null
    return JSON.parse(rolesJson);
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    return this.getRoles();
  }
}
