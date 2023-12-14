import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Timeslot } from '../_classes/timeslot';
import { Appointment } from '../_classes/appointment';
import { TimeslotService } from '../_services/timeslot.service';
import { AppointmentService } from '../_services/appointment.service';
import { Doctor } from '../_classes/doctor';

@Component({
  selector: 'app-create-appointment',
  templateUrl: './create-appointment.component.html',
  styleUrls: ['./create-appointment.component.css']
})
export class CreateAppointmentComponent implements OnInit {

  @ViewChild('myclient') client!: ElementRef | undefined;

  constructor(private timeslotService: TimeslotService,
    private http: HttpClient, private renderer: Renderer2, 
    private elementRef: ElementRef, private router: Router,
    private appointmentService: AppointmentService) { }

  changeColor() {
    const button = this.elementRef.nativeElement.querySelector('button');
    this.renderer.setStyle(button, 'background-color', 'red');
  }

  changeFontColor() {
    const button = this.elementRef.nativeElement.querySelector('#dpdlSite');
    this.renderer.addClass(button, 'text-danger');
  }

  openModel() {
    const modelDiv = document.getElementById('myModal');
    if (modelDiv != null) {
      modelDiv.style.display = 'block';
    }
  }

  CloseModel() {
    const modelDiv = document.getElementById('myModal');
    if (modelDiv != null) {
      modelDiv.style.display = 'none';
    }
  }

  cities: any[] = [];
  selectedCity: any = null;

  specializations: any[] = [];
  selectedSpecialization: string = '';

  ratings: number[] = [];
  selectedRating: number = 0;

  doctors: any[] = [];
  selectedDoctor: any = null;

  selectedTimeslotDate!: any;
  selectedTimeslotTime!: any;

  timeslots: any[] = [];
  timeslotDates: string[] = [];
  timeslotTimes: string[] = [];

  timeslot: Timeslot = new Timeslot;
  doctor: Doctor = new Doctor;
  appointment: Appointment = new Appointment;

  ngOnInit() {
    this.loadCities();
  }

  ngAfterViewInit() {
    if (this.client != null) {
    }
  }

  loadCities() {
    this.http.get('http://localhost:8080/api/v1/cities').subscribe((result: any) => {
      this.cities = result;
    });
  }

  getSpecializationsByCity() {
    if (this.selectedCity) {
      const selectedCityId = this.selectedCity.cityId;
      this.http.get<string[]>('http://localhost:8080/api/v1/city-specializations/' + selectedCityId)
        .subscribe((result: string[]) => {
          this.specializations = result;
        });
    }
  }

  getRatingsBySpecialization() {
    if (this.selectedSpecialization && this.selectedCity) {
      this.http.get<number[]>('http://localhost:8080/api/v1/ratings?specialization=' + this.selectedSpecialization + '&cityId=' + this.selectedCity.cityId)
        .subscribe((result: number[]) => {
          this.ratings = result;
          // Assuming the response is an array of numbers
          console.log('Ratings:', result);

          // You can further handle the ratings array as needed
        });
    }
  }

  // http://localhost:8080/api/v1/doctors?specialization=Family-Medicine&cityId=1&ratings=5

  getDoctorsBySpecializationAndCityAndRating() {
    this.http.get<any[]>('http://localhost:8080/api/v1/doctor-names?specialization=' + this.selectedSpecialization + '&cityId=' + this.selectedCity.cityId + '&ratings=' + this.selectedRating)
      .subscribe((result: any[]) => {
        console.log('Doctors:', result);
        this.doctors = result;

        // Check if there is at least one doctor
        if (this.doctors.length > 0) {
          // Set the selectedDoctor to the first doctor in the list
          this.selectedDoctor = this.doctors[0];
          // Now, call the next function
          this.getTimeslotDateByDoctor();
        } else {
          // Clear selectedDoctor if there are no doctors
          this.selectedDoctor = undefined;
        }
      });
  }

  // http://localhost:8080/api/v1/time-slots/date/?doctorId=1&status=Available

  getTimeslotDateByDoctor() {
    console.log('Doctors in getTimeslotDateByDoctor:', this.doctors);
    console.log('getTimeslotDateByDoctor Doctor:', this.selectedDoctor);
    if (this.selectedDoctor) {
      const selectedDoctorId = this.selectedDoctor.doctorId;
      console.log('getTimeslotDateByDoctor id:', selectedDoctorId);

      this.http.get<string[]>('http://localhost:8080/api/v1/time-slots/date/?doctorId=' + selectedDoctorId + '&status=Available')
        .subscribe((result: string[]) => {
          this.timeslotDates = result; // Assuming you have a timeslots array
        });
    } else {
      // Handle the case where selectedDoctor is not defined
      console.error('Selected doctor is undefined. Cannot fetch time slots.');
      // You might want to clear the timeslots array or handle it appropriately.
    }
  }

  // http://localhost:8080/api/v1/time-slots/time/?doctorId=2&status=Available&availableDate=12/12/2023
  getTimeslotTimeByDoctor() {
    console.log('Inside getTimeslotTimeByDoctor');
    if (this.selectedDoctor && this.selectedTimeslotDate) {
      const selectedDoctorId = this.selectedDoctor.doctorId;
      console.log('getTimeslotTimeByDoctor id:', selectedDoctorId);
      console.log('getTimeslotTimeByDoctor selectedTimeslotDate:', this.selectedTimeslotDate);

      this.http.get<string[]>('http://localhost:8080/api/v1/time-slots/time/?doctorId=' + selectedDoctorId + '&status=Available&availableDate=' + this.selectedTimeslotDate)
        .subscribe((result: string[]) => {
          this.timeslotTimes = result; // Assuming you have a timeslots array
        });
    } else {
      // Handle the case where selectedDoctor is not defined
      console.error('Selected doctor is undefined. Cannot fetch time slots.');
      // You might want to clear the timeslots array or handle it appropriately.
    }
  }

  // http://localhost:8080/api/v1/make-appointment?u_id=user_id
  saveAppointment() {
    console.log("Entered saveAppointment");
    if (this.selectedDoctor && this.selectedTimeslotDate && this.selectedTimeslotTime) {
      const selectedDoctorId = this.selectedDoctor.doctorId;
  
      this.timeslot.doctorId = selectedDoctorId;
      this.timeslot.availableDate = this.selectedTimeslotDate;
      this.timeslot.availableTime = this.selectedTimeslotTime;
      this.timeslot.status = "Available";
      console.log("Entered saveAppointment if statement");
  
      this.timeslotService.getTimeslotId(this.timeslot).subscribe(
        (data: number[]) => {
          // Assuming the API returns an array, extract the first element
          const timeslotId = data.length > 0 ? data[0] : -1;
  
          console.log("Timeslot ID:", timeslotId);



          // this.appointment.timeslot = this.timeslot;
          // this.appointment.timeslot.timeslotId = timeslotId;
          // this.appointment.doctor = this.doctor;
          // this.appointment.doctor.doctorId = selectedDoctorId;

          const newAppointmentPayload = {
            doctorId: selectedDoctorId,
            timeSlotId: timeslotId
          };
          

          this.appointmentService.bookAppointment(3, newAppointmentPayload);

          this.goToUserDash();
        },
        error => {
          console.error(error); // Access the error message from the response
        }
      );
    }
    // Implement your save logic here
  }
  

  goToUserDash() {
    this.router.navigate(['/user']);
  }

  onSubmit() {
    console.log("Entered onSubmit");
    this.saveAppointment();
  }
}