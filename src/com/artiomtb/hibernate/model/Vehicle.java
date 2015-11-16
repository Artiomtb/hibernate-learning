package com.artiomtb.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "vehicles_hibernate")
public class Vehicle {

    @Id
    @Column(name = "vehicle_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_id_generator")
    @SequenceGenerator(name = "vehicle_id_generator", sequenceName = "vehicles_vehicle_id_seq", allocationSize = 1)
    private int vehicleID;

    @Column(name = "vehicle_name")
    private String vehicleName;

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}