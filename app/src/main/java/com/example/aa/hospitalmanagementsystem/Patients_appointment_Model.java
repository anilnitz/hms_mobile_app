package com.example.aa.hospitalmanagementsystem;

/**
 * Created by AA on 05-Jul-19.
 */

class Patients_appointment_Model {
    String Type,doctorId,appointmentDate;

    public Patients_appointment_Model() {
    }

    public Patients_appointment_Model(String type, String doctorId, String appointmentDate) {
        Type = type;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
    }

    public String getType() {
        return Type;
    }

    public String getUserId() {
        return doctorId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }
}
