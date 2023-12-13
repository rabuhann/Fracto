import { Injectable } from '@angular/core';
import { Timeslot } from '../_classes/timeslot';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';  // Import of from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class TimeslotService {
  private PATH_OF_API = 'http://localhost:8080/api/v1';

  constructor(
    private httpclient: HttpClient
    ) { }

    public getTimeslotId(timeslot: Timeslot): Observable<number[]> {
      console.log("Entered getTimeslotId in the service");
      
      return this.httpclient.post<number[]>(this.PATH_OF_API + '/time-slots/id/', timeslot);
    }

}
