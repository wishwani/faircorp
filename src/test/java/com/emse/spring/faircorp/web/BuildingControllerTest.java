package com.emse.spring.faircorp.web;

import com.emse.spring.faircorp.api.BuildingController;
import com.emse.spring.faircorp.api.HeaterController;
import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.BuildingDto;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BuildingController.class)
public class BuildingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HeaterDao heaterDao;

    @MockBean
    private RoomDao roomDao;

    @MockBean
    private BuildingDao buildingDao;
    Long id;

    @Test
        //@WithMockUser(username = "admin", roles = "ADMIN")
    void shouldLoadBuildings() throws Exception {
        given(buildingDao.findAll()).willReturn(List.of(
                createBuilding("building 1"),
                createBuilding("building 2")
        ));

        mockMvc.perform(get("/api/buildings").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(jsonPath("[*].name").value(containsInAnyOrder("building 1", "building 2")));
    }

    @Test
        //@WithMockUser(username = "admin", roles = "ADMIN")
    void shouldLoadABuildingAndReturnNullIfNotFound() throws Exception {
        given(buildingDao.findById(999L)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/buildings/999").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(content().string(""));
    }

    @Test
        //@WithMockUser(username = "admin", roles = "ADMIN")
    void shouldLoadABuilding() throws Exception {
        given(buildingDao.findById(999L)).willReturn(Optional.of(createBuilding("building 1")));

        mockMvc.perform(get("/api/buildings/999").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(jsonPath("$.name").value("building 1"));
    }

    @Test
        //@WithMockUser(username = "admin", roles = "ADMIN")
    void shouldUpdateBuilding() throws Exception {
        Building expectedBuilding = createBuilding("building 1");
        expectedBuilding.setId(1L);
        String json = objectMapper.writeValueAsString(new BuildingDto(expectedBuilding));

        //given(roomDao.getReferenceById(anyLong())).willReturn(expectedBuilding.getRoom());
        given(buildingDao.getReferenceById(anyLong())).willReturn(expectedBuilding);

        mockMvc.perform(post("/api/buildings").content(json).contentType(APPLICATION_JSON_VALUE))
                // check the HTTP response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("building 1"))
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
        //@WithMockUser(username = "admin", roles = "ADMIN")
    void shouldCreateBuilding() throws Exception {
        Building expectedBuilding = createBuilding("building 1");
        expectedBuilding.setId(null);
        String json = objectMapper.writeValueAsString(new BuildingDto(expectedBuilding));

        //given(buildingDao.getReferenceById(anyLong())).willReturn(expectedBuilding.getBuilding());
        given(buildingDao.save(any())).willReturn(expectedBuilding);

        mockMvc.perform(post("/api/buildings").content(json).contentType(APPLICATION_JSON_VALUE))
                // check the HTTP response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("building 1"));
    }

    @Test
        //@WithMockUser(username = "admin", roles = "ADMIN")
    void shouldDeleteBuilding() throws Exception {
        mockMvc.perform(delete("/api/buildings/999"))
                .andExpect(status().isOk());
    }

    private Building createBuilding(String name) {
        //Room room = new Room("S1", 1, building);
        return new Building(name);
    }
}


