import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root',
})
export class UserAuthService {
  private _isAuthenticated = false;
  private user_id!: number;

  constructor() {}

  /*** */
  public setUserId(value: any){
    this.user_id = value;
    localStorage.setItem('user_id', value);
  }
  public getUserId():any{
    //eturn this.user_email;
    return localStorage.getItem('user_id');
  }
/*** */

  public setAuthenticated(value: boolean) {
    this._isAuthenticated = value;
  }

  public isAuthenticated(): boolean {
    return this._isAuthenticated;
  }
  
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
    return this.getRoles()&& this.isAuthenticated();
  }
}
