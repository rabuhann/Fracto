import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
   selector: 'app-create-appointment',
   templateUrl: './create-appointment.component.html',
   styleUrls: ['./create-appointment.component.css']
})
export class CreateAppointmentComponent implements OnInit {

  @ViewChild('myclient') client!: ElementRef | undefined;

  constructor(private http: HttpClient, private renderer: Renderer2, private elementRef: ElementRef) { }

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

  specializations: string = '';
  selectedSpecialization: string = '';

  doctors: any[] = [];
  selectedDoctor: any;

  selectedTimeslot!: any; // Adjust the type accordingly based on the structure of your timeslot object

   timeslots: any[] = [];

   ngOnInit() {
      this.loadSpecializations();
   }

  ngAfterViewInit() {
    if (this.client != null) {
    }
  }

  loadSpecializations() {
    this.http.get('http://localhost:8080/api/v1/specializations').subscribe((result: any) => {
      this.specializations = result;
    });
  }

  getDoctorsBySpecialization() {
    this.http.get<any[]>('http://localhost:8080/api/v1/specialization/' + this.selectedSpecialization)
      .subscribe((result: any[]) => {
        this.doctors = result;
        console.log('Doctors:', this.doctors);
  
        // Check if there is at least one doctor
        if (this.doctors.length > 0) {
          // Set the selectedDoctor to the first doctor in the list
          this.selectedDoctor = this.doctors[0];
          console.log('Selected Doctor:', this.selectedDoctor);
        } else {
          // Clear selectedDoctor if there are no doctors
          this.selectedDoctor = undefined;
        }
      });
  }   

  getTimeslotByDoctor() {
    if (this.selectedDoctor) {
      const selectedDoctorId = this.selectedDoctor.doctorId;
      this.http.get<any[]>('http://localhost:8080/api/v1/time-slots/doctor/' + selectedDoctorId)
        .subscribe((result: any[]) => {
          this.timeslots = result; // Assuming you have a timeslots array
        });
    }
  }  

 saveAppointment() {
    if (this.selectedTimeslot) {
       // Here you can use this.selectedTimeslot.availableDateTime or any other properties
       console.log('Selected Timeslot:', this.selectedTimeslot);
    }
    // Implement your save logic here
 }

 refreshData() {
    // Implement your refresh logic here
 }
}