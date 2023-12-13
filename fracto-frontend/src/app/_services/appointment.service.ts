import { Injectable } from '@angular/core';
import { Appointment } from '../_classes/appointment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private PATH_OF_API = 'http://localhost:8080/api/v1';

  constructor(
    private httpclient: HttpClient
    ) { }

    public bookAppointment(userId: number, appointment: Appointment): Observable<Object[]> {
      console.log("Entered bookAppointment in the service");
      
      const params = new HttpParams().set('u_id', userId.toString());
    
      return this.httpclient.post<Object[]>(this.PATH_OF_API + '/make-appointment', appointment, { params });
    }

    getAppointmentsList(): Observable<Appointment[]> {
      return this.httpclient.get<Appointment[]>(this.PATH_OF_API + '/appointments');
    }
    
}
