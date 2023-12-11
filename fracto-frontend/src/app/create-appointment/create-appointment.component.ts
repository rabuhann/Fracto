import { AfterViewInit, Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-create-appointment',
  templateUrl: './create-appointment.component.html',
  styleUrls: ['./create-appointment.component.css']
})
export class CreateAppointmentComponent implements OnInit, AfterViewInit {


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
  doctorId: number = 0;
  doctorName: string = '';
  specialization: string = '';
  timeslotId: number = 0;
  availableDateTime: string = '';

  buildingId: string = '';
  floorId: string = ''; clients: any[] = []; sites: any[] = [];
  doctors: any[] = []; floors: any[] = [];

  ngOnInit() {
    this.loadSpecializations();

  }
  ngAfterViewInit() {
    if (this.client != null) {
    }
  }

  loadSpecializations() {
    this.http.get('localhost:8080/api/v1/specializations').subscribe((result: any) => {
      this.specializations = result;
    })
  }
  
  // getDoctorsBySpecialization() {
  //   this.http.get('localhost:8080/api/v1/specialization').subscribe((result: any) => {
  //     this.doctorId = result.data;
  //   })
  // }
  // getTimeslotByDoctor() {
  //   this.http.get('localhost:8080/api/v1/time-slots/doctor/' + this.doctorId).subscribe((result: any) => {
  //     this.doctors = result.data;
  //   })
  // }

}