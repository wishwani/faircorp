package com.emse.spring.faircorp.dao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@DataJpaTest

class HeaterDaoTest {
    @Autowired
    private HeaterDao heaterDao;
    @Autowired
    private RoomDao roomDao;
    @Test
    public void shouldFindAHeater() {
        Heater heater = heaterDao.getOne(-10L);
        Assertions.assertThat(heater.getName()).isEqualTo("Heater1");
        Assertions.assertThat(heater.getHeaterStatus()).isEqualTo(HeaterStatus.ON);
    }
    @Test
    public void shouldDeleteHeaterInRoom() {
        Room room = roomDao.getReferenceById(-10L);
        List<Long> heaterIds = room.getHeaters().stream().map(Heater::getId).collect(Collectors.toList());
        Assertions.assertThat(heaterIds.size()).isEqualTo(2);
        heaterDao.deleteHeatersByRoomId(-10L);
        List<Heater> result = heaterDao.findAllById(heaterIds);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void ShouldCreateHeater() {
        Room room = roomDao.getReferenceById(-10l);
        Heater newHeater = new Heater();
        newHeater.setName("Test");
        newHeater.setPower(500L);
        newHeater.setRoom(room);
        newHeater.setHeaterStatus(HeaterStatus.ON);
        Heater heater = heaterDao.save(newHeater);
        Assertions.assertThat(heater.getName()).isEqualTo(newHeater.getName());
        Assertions.assertThat(heater.getPower()).isEqualTo(newHeater.getPower());
        Assertions.assertThat(heater.getRoom()).isEqualTo(newHeater.getRoom());
        Assertions.assertThat(heater.getHeaterStatus()).isEqualTo(newHeater.getHeaterStatus());
    }
}
