import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserAuthService } from './user-auth.service';
import { User, Role } from '../_classes/user';
import { Observable, of } from 'rxjs';  // Import of from 'rxjs'
import { catchError, map, tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private PATH_OF_API = 'http://localhost:8080/api/v1';
  private baseURL = "http://localhost:8080/api/v1/users";
  private tokenKey = 'auth_token';

  constructor(
    private httpclient: HttpClient,
    private userAuthService: UserAuthService
  ) { }

/* */
public login(loginData: any) {
  return this.httpclient.post<any>(this.PATH_OF_API + '/login', loginData, {
  }).pipe(tap(response => this.setToken(response.token)));
}

private setToken(token: string): void {
  localStorage.setItem(this.tokenKey, token);
}

public getToken(): string | null {
  return localStorage.getItem(this.tokenKey);
}

public removeToken(): void {
  localStorage.removeItem(this.tokenKey);
  localStorage.removeItem('user_email');
}

logout() {
  this.removeToken();
}

isLoggedIn(): boolean {
  return !!this.getToken();
}


/* */
  

  public signUp(user: User): Observable<Object> {
    return this.httpclient.post(this.PATH_OF_API + '/signup', user, {
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

  getRoles(): any {
    return this.userAuthService.getRoles();
  }

  getUsersList(): Observable<User[]> {
    return this.httpclient.get<User[]>(this.baseURL);
  }

  createUser(user: User, role: string): Observable<Object> {
    return this.httpclient.post(this.baseURL + '?role=' + role, user);
  }

  getUserById(id: number): Observable<User> {
    return this.httpclient.get<User>(this.baseURL + '/' + id)
  }

  updateUser(id: number, user: User, role: string): Observable<Object> {
    return this.httpclient.put(this.baseURL + '/' + id + '?role=' + role, user);
  }

  deleteUser(id: number): Observable<Object> {
    return this.httpclient.delete(this.baseURL + '/' + id);
  }

  getRole(id: number): Observable<Role> {
    return this.httpclient.get<User>(`${this.baseURL}/${id}`).pipe(
      map((user: User) => {
        return user.roles[0];  // Return the name of the first role
      })
    );
  }

}
