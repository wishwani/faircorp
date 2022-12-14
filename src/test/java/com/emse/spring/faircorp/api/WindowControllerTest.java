package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@WebMvcTest(WindowController.class)

public class WindowControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private WindowDao windowDao;

    @MockBean
    private RoomDao roomDao;
    Building building;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldLoadWindows() throws Exception {
        given(windowDao.findAll()).willReturn(List.of(
                createWindow("window 1"),
                createWindow("window 2")
        ));

        mockMvc.perform(get("/api/windows").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(jsonPath("[*].name").value(containsInAnyOrder("window 1", "window 2")));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldLoadAWindowAndReturnNullIfNotFound() throws Exception {
       given(windowDao.findById(999L)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/windows/999").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldLoadAWindow() throws Exception {
        given(windowDao.findById(999L)).willReturn(Optional.of(createWindow("window 1")));

        mockMvc.perform(get("/api/windows/999").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(jsonPath("$.name").value("window 1"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldSwitchWindow() throws Exception {
        Window expectedWindow = createWindow("window 1");
        Assertions.assertThat(expectedWindow.getWindowStatus()).isEqualTo(WindowStatus.OPEN);

        given(windowDao.findById(999L)).willReturn(Optional.of(expectedWindow));

        mockMvc.perform(put("/api/windows/999/switch").with(csrf()).accept(APPLICATION_JSON))

                // check the HTTP response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("window 1"))
                .andExpect(jsonPath("$.windowStatus").value("CLOSED"));
    }



    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldCreateWindow() throws Exception {
        Window expectedWindow = createWindow("window 3");
        expectedWindow.setId(null);
        String json = objectMapper.writeValueAsString(new WindowDto(expectedWindow));

        given(roomDao.getReferenceById(anyLong())).willReturn(expectedWindow.getRoom());
        given(windowDao.save(any())).willReturn(expectedWindow);

        mockMvc.perform(post("/api/windows").with(csrf()).content(json).contentType(APPLICATION_JSON_VALUE))
                // check the HTTP response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("window 3"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldDeleteWindow() throws Exception {
        mockMvc.perform(delete("/api/windows/999").with(csrf()))
                .andExpect(status().isOk());
    }

    private Window createWindow(String name) {

       Room room = new Room("S1", 1, 1, 1, building);
        return new Window(name, WindowStatus.OPEN, room);
    }


}
