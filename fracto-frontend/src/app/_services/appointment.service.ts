import { Injectable } from '@angular/core';
import { Appointment } from '../_classes/appointment';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { HttpParams } from '@angular/common/http';
import { AppointmentDetails } from '../_classes/appointment-details';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private PATH_OF_API = 'http://localhost:8080/api/v1';

  constructor(
    private httpclient: HttpClient
    ) { }

    public bookAppointment(userId: number, appointment: AppointmentDetails): Observable<AppointmentDetails[]> {
      console.log("Entered bookAppointment in the service");
      
      const params = new HttpParams().set('u_id', userId.toString());

      console.log(appointment);
    
      return this.httpclient.post<AppointmentDetails[]>(this.PATH_OF_API + '/make-appointment?' + params, appointment);
    }

    cancelAppointment(id: number): Observable<Object> {
      return this.httpclient.put(this.PATH_OF_API + '/appointment/' + id, {});
    }    

    getAppointmentsList(userId: number): Observable<Appointment[]> {
      return this.httpclient.get<any[]>(this.PATH_OF_API + '/appointments?userId=' + userId)
        .pipe(
          map(data => data.map(appointmentData => this.mapAppointment(appointmentData)))
        );
    }
  
    private mapAppointment(data: any): Appointment {
      return {
        appointmentId: data[0]!,
        doctor: {
          doctorId: null!,
          doctorName: data[1],
          ratings: null!,
          specialization: null!,
          cityId: null!
        },
        timeslot: {
          timeslotId: null!,
          availableDate: data[2],
          availableTime: data[3],
          status: null!,
          doctorId: null!
        },
        userId: null!,
        status: data[4]
      };
    }
}
