package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.HeaterStatus;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081" )
@RestController // (1)
@RequestMapping("/api/heaters") // (2)
@Transactional // (3)

public class HeaterController {
    private final HeaterDao heaterDao;
    private final RoomDao roomDao;

    public HeaterController(HeaterDao heaterDao, RoomDao roomDao) { // (4)
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }

    @GetMapping // (5)
    public List<HeaterDto> findAll() {
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());  // (6)
    }

    @GetMapping(path = "/{id}")
    public HeaterDto findById(@PathVariable Long id) {
        return heaterDao.findById(id).map(HeaterDto::new).orElse(null); // (7)
    }

    @PutMapping(path = "/{id}/switch")
    public HeaterDto switchStatus(@PathVariable Long id) {
        Heater heater = heaterDao.findById(id).orElseThrow(IllegalArgumentException::new);
        heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);
        return new HeaterDto(heater);
    }


    @PostMapping // (8)
    public HeaterDto create(@RequestBody HeaterDto dto) {
        // HeaterDto must always contain the window room
        Room room = roomDao.getById(dto.getRoomId());
        Heater heater = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            heater = heaterDao.save(new Heater(dto.getName(), room, dto.getHeaterStatus()));
        }
        else {
            heater = heaterDao.getById(dto.getId());  // (9)
            heater.setHeaterStatus(dto.getHeaterStatus());
            heater.setName(dto.getName());
            heater.setPower(dto.getPower());
            heater.setRoom(room);
        }
        return new HeaterDto(heater);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        heaterDao.deleteById(id);
    }
}

