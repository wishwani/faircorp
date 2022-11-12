package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*" )
@RestController // (1)
@RequestMapping("/api/rooms") // (2)
@Transactional // (3)
//@CrossOrigin
public class RoomController {
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private WindowDao windowDao;

    @Autowired
    private HeaterDao heaterDao;


    @GetMapping // (5)
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());  // (6)
    }

    @PutMapping(path = "/{id}/switchWindow")
    public WindowDto switchWindow(@PathVariable Long id) {
        Window window = windowDao.findById(id).orElseThrow(IllegalArgumentException::new);
        window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        return new WindowDto(window);
    }

    @PutMapping(path = "/{id}/switchHeater")
    public HeaterDto switchHeater(@PathVariable Long id) {
        Heater heater = heaterDao.findById(id).orElseThrow(IllegalArgumentException::new);
        heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);
        return new HeaterDto(heater);
    }



    @PostMapping(path = "/")
    public RoomDto create(@RequestBody RoomDto dto) {
        Building building = buildingDao.getById(dto.getBuildingId());
        Room room;
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getName(), dto.getFloor(), dto.getCurrentTemperature(), dto.getTargetTemperature(), building));
        } else {
            room = roomDao.getById(dto.getId());
            room.setFloor(dto.getFloor());
            room.setCurrentTemperature(dto.getCurrentTemperature());
            room.setName(dto.getName());
            room.setTargetTemperature(dto.getTargetTemperature());
            room.setBuilding(building);
        }

        return new RoomDto(room);
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null); // (7)
    }

    @DeleteMapping(path = "/{room_id}")
    public void delete(@PathVariable Long room_id) {
        roomDao.deleteById(room_id);
    }

}
