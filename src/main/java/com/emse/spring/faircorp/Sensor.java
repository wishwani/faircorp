package com.emse.spring.faircorp;

import javax.persistence.*;
@Entity
@Table(name="SP_SENSOR")
public class Sensor {
    @Id // (3).
    @GeneratedValue
    private Long id;

    @Column(nullable=false, length=255)  // (4).
    private String name;

    private String description;

    @Column(name = "power") // (4).
    private Integer defaultPowerInWatt;

    @Transient // (5).
    private Integer notImportant;

    @Enumerated(EnumType.STRING) // (6).
    private PowerSource powerSource;

    public Sensor() { // (7).
    }

    public Sensor(String name) { // (8).
        this.name = name;
    }

    public Long getId() { // (9).
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDefaultPowerInWatt() {
        return defaultPowerInWatt;
    }

    public void setDefaultPowerInWatt(Integer defaultPowerInWatt) {
        this.defaultPowerInWatt = defaultPowerInWatt;
    }

    public Integer getNotImportant() {
        return notImportant;
    }

    public void setNotImportant(Integer notImportant) {
        this.notImportant = notImportant;
    }

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public void setPowerSource(PowerSource powerSource) {
        this.powerSource = powerSource;
    }

}
