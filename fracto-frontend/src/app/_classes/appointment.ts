import { Doctor } from './doctor';
import { Timeslot } from './timeslot';

export class Appointment {
    appointmentId!: number;
    status!: string;
    doctor!: Doctor;
    timeslot!: Timeslot;
    userId!: number;
}