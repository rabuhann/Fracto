import { Injectable } from '@angular/core';
import { Appointment } from '../_classes/appointment';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private PATH_OF_API = 'http://localhost:8080/api/v1';

  constructor(
    private httpclient: HttpClient
    ) { }

    public bookAppointment(userId: number, payload: any): Observable<Object[]> {
      console.log("Entered bookAppointment in the service");
      console.log(payload);
      
      const params = new HttpParams().set('u_id', userId.toString());
    
      return this.httpclient.post<Object[]>(this.PATH_OF_API + '/make-appointment', payload, { params });
    }

    getAppointmentsList(userId: number): Observable<Appointment[]> {
      return this.httpclient.get<any[]>(this.PATH_OF_API + '/appointments?userId=' + userId)
        .pipe(
          map(data => data.map(appointmentData => this.mapAppointment(appointmentData)))
        );
    }
  
    private mapAppointment(data: any): Appointment {
      return {
        appointmentId: null!,
        doctor: {
          doctorId: null!,
          doctorName: data[0],
          ratings: null!,
          specialization: null!,
          cityId: null!
        },
        timeslot: {
          timeslotId: null!,
          availableDate: data[1],
          availableTime: data[2],
          status: null!,
          doctorId: null!
        },
        userId: null!,
        status: data[3]
      };
    }
}
