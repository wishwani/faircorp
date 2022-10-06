package com.emse.spring.faircorp.model;

import com.emse.spring.faircorp.dao.RoomDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest

class RoomDaoTest {
    @Autowired
    private RoomDao roomDao;
    @Test
    public void shouldFindARoom() {
        Room room = roomDao.getOne(-10L);
        Assertions.assertThat(room.getName()).isEqualTo("Room1");
        Assertions.assertThat(room.getFloor()).isEqualTo(1);
        Assertions.assertThat(room.getCurrentTemperature()).isEqualTo(22.3);
        Assertions.assertThat(room.getTargetTemperature()).isEqualTo(20.0);

    }
}
