import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root',
})
export class UserAuthService {
  private _isAuthenticated = false;

  constructor() { }

  public setAuthenticated(value: boolean) {
    console.log('Setting isAuthenticated to:', value);
    this._isAuthenticated = value;
  }

  public isAuthenticated(): boolean {
    console.log('isAuthenticated called:', this._isAuthenticated);
    return this._isAuthenticated;
  }

  public setRoles(roles: []) {
    localStorage.setItem("roles", JSON.stringify(roles));
  }
  
  public getRoles(): string[] {
    const rolesJson = localStorage.getItem("roles") ?? "[]";
    return JSON.parse(rolesJson) as string[]; // Parse as string array
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    return this.getRoles() && this.isAuthenticated();
  }
}
