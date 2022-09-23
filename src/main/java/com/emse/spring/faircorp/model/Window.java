package com.emse.spring.faircorp.model;

import javax.persistence.*;

@Entity // (1).
@Table(name = "RWINDOW") // (2).
public class Window {
    // (3)
    @Id
    @Column(nullable=false)
    private Long id;

    // (4)
    private String name;

    // (5)
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private WindowStatus windowStatus;

    @ManyToOne
    private Room room;

    public Window() {
    }

    public Window(String name, WindowStatus status, Room room) {
        this.windowStatus = status;
        this.name = name;
        this.room = room;
    }

    //public Long getId() {
       // return this.id;
    //}

    //public void setId(Long id) {
        //this.id = id;
    //}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
