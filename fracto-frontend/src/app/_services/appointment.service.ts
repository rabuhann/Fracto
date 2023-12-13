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

    public bookAppointment(userId: number, appointment: Appointment): Observable<Object[]> {
      console.log("Entered bookAppointment in the service");
      
      const params = new HttpParams().set('u_id', userId.toString());
    
      return this.httpclient.post<Object[]>(this.PATH_OF_API + '/make-appointment', appointment, { params });
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
          doctorId: null!, // Use 'null!' to assert that it's not null, modify this according to your structure
          doctorName: data[0],
          ratings: null!, // Replace with actual values
          specialization: null!, // Replace with actual values
          cityId: null! // Replace with actual values
        },
        timeslot: {
          timeslotId: null!, // Use 'null!' to assert that it's not null, modify this according to your structure
          availableDate: data[1],
          availableTime: data[2],
          status: null!, // Assuming data[3] is the status
          doctorId: null! // Use 'null!' to assert that it's not null, modify this according to your structure
        },
        userId: null!, // Use 'null!' to assert that it's not null, modify this according to your structure
        status: data[3] // Assuming data[4] is the status in the appointment
      };
    }
}
