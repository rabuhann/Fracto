CREATE DATABASE IF NOT EXISTS fracto;

use fracto;

-- Create User table
CREATE TABLE User (
    UserID INT PRIMARY KEY,
    Username VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL
);

-- Create Doctor table
CREATE TABLE Doctor (
    DoctorID INT PRIMARY KEY,
    DoctorName VARCHAR(255) NOT NULL,
    Specialization ENUM('Cardiology', 'Dermatology', 'Emergency Medicine', 'Family Medicine', 'Psychiatry', 'Surgery') NOT NULL,
    City VARCHAR(255),
    Ratings DECIMAL(3, 2)
);

-- Create Appointment table
CREATE TABLE Appointment (
    AppointmentID INT PRIMARY KEY,
    UserID INT,
    DoctorID INT,
    AppointmentDate DATE,
    TimeSlot TIME,
    City VARCHAR(255),
    Status ENUM('Booked', 'Confirmed', 'Cancelled') NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID)
);
