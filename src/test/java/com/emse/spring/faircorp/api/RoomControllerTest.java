package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()

public class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private HeaterDao heaterDao;

    Building building;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldCreateRoom() throws Exception {
        mockMvc.perform(post("/api/rooms/").with(csrf()).contentType(APPLICATION_JSON)
                        .content("{\n" +
                                "  \"buildingId\": 1,\n" +
                                "  \"currentTemperature\": 20,\n" +
                                "  \"floor\": 2,\n" +
                                "  \"name\": \"Test Room\",\n" +
                                "  \"targetTemperature\": 20\n" +
                                "}").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buildingId").value(1L))
                .andExpect(jsonPath("$.currentTemperature").value(20))
                .andExpect(jsonPath("$.floor").value(2))
                .andExpect(jsonPath("$.name").value("Test Room"))
                .andExpect(jsonPath("$.targetTemperature").value(20));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldLoadARoomAndReturnNull() throws Exception {
        mockMvc.perform(get("/api/rooms/999").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldLoadARoomAndReturnValue() throws Exception {
        mockMvc.perform(get("/api/rooms/-9").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(-9L))
                .andExpect(jsonPath("$.name").value("Room2"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldLoadAllRooms() throws Exception {
        mockMvc.perform(get("/api/rooms").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldDeleteRoom() throws Exception {
        mockMvc.perform(delete("/api/rooms/-8").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldSwitchHeaterStatus() throws Exception {
        Optional<Room> optRoom = roomDao.findById(-10L);
        Room room = optRoom.get();
        mockMvc.perform(put("/api/rooms/-10/switchHeater").with(csrf()).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id").value(-10L))
                .andExpect(jsonPath("$.heaterStatus").value(room.getHeaters().get(1).getHeaterStatus()== HeaterStatus.ON ? "OFF" : "ON"));
    }

    /*@Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldSwitchWindowStatus() throws Exception {
        Optional<Room> optRoom = roomDao.findById(-10L);
        Room room = optRoom.get();
        mockMvc.perform(put("/api/rooms/-10/switchWindow").with(csrf()).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id").value(-10L))
                .andExpect(jsonPath("$.windowStatus").value(room.getWindows().get(3).getWindowStatus()== WindowStatus.OPEN ? "CLOSE" : "OPEN"));
    }*/

}
