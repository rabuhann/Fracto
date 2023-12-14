// role.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserAuthService } from '../_services/user-auth.service';


@Injectable({
  providedIn: 'root',
})
export class RoleGuard implements CanActivate {
  constructor(private authService: UserAuthService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const allowedRoles = next.data['roles'] as string[];
    
    if (allowedRoles && allowedRoles.length > 0) {
        const userRoles = this.authService.getRoles();

      // Check if the user has at least one of the required roles
      if (userRoles.some(role => allowedRoles.includes(role))) {
        console.log(userRoles);
        return true;
      } else {
        // Redirect to unauthorized page or handle unauthorized access
        //this.router.navigate(['/login']);
        return false;
      }
    }

    // If no roles are specified for the route, allow access
    return true;
  }
}