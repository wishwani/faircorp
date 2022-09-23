package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;


@Entity // (1).
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private int floor;

    @Column(nullable=false)
    private String name;

    private double currentTemperature;

    private double targetTemperature;

    @OneToMany(mappedBy = "room")
    private Set<Heater> heaters;
    @OneToMany(mappedBy = "room")
    private Set<Window> windows;


    public Room() {
    }

    public Room( int floor, String name) {
        //this.currentTemperature = currentTemperature;
        this.floor = floor;
        this.name = name;
        //this.targetTemperature = targetTemperature;
        //this.heaters = heaters;
        //this.windows = windows;
    }



    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public Set<Heater> getHeaters(){
        return heaters;
    }

    public void setHeaters() {
        this.heaters = heaters;
    }

    public Set<Window> getWindows(){return windows;}

    public void setWindows(){this.windows = windows;}

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
