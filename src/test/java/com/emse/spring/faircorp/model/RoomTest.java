package com.emse.spring.faircorp.model;

import org.h2.api.UserToRolesMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    public void testRoom(){
        Room room1 = new Room(1,"room1");
        assertEquals("room1",room1.getName());


    }

}