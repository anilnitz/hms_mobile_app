package com.example.aa.hospitalmanagementsystem;

/**
 * Created by AA on 29-Jun-19.
 */

public class Doctor_appointment_Model {

    String Type,userId,appointmentDate;

    public Doctor_appointment_Model() {
    }

    public Doctor_appointment_Model(String type, String userId, String appointmentDate) {
        Type = type;
        this.userId = userId;
        this.appointmentDate = appointmentDate;
    }

    public String getType() {
        return Type;
    }

    public String getUserId() {
        return userId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }
}
