import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { SignupComponent } from './signup/signup.component';
import { CreateAppointmentComponent } from './create-appointment/create-appointment.component';
import { AuthGuard } from './guards/auth.guard';
import { RoleGuard } from './guards/role.guard';
import { LandingPageComponent } from './landingpage/landing-page.component';

const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'home', component: HomeComponent },
  { path: 'admin', component: AdminComponent , canActivate: [AuthGuard, RoleGuard],  data: { roles: ['ROLE_ADMIN'] }},
  { path: 'user', component: UserComponent , canActivate: [AuthGuard, RoleGuard],  data: { roles: ['ROLE_USER'] }},
  { path: 'login', component: LoginComponent },
  { path: 'forbidden', component: ForbiddenComponent },
  { path: 'create-user', component: CreateUserComponent , canActivate: [AuthGuard, RoleGuard],  data: { roles: ['ROLE_ADMIN'] }},
  { path: 'update-user/:id', component: UpdateUserComponent , canActivate: [AuthGuard, RoleGuard],  data: { roles: ['ROLE_ADMIN'] }},
  { path: 'user-details/:id', component: UserDetailsComponent , canActivate: [AuthGuard, RoleGuard],  data: { roles: ['ROLE_ADMIN'] }},
  { path: 'app-signup', component: SignupComponent },
  { path: 'book-app', component: CreateAppointmentComponent , canActivate: [AuthGuard, RoleGuard],  data: { roles: ['ROLE_USER'] }},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }