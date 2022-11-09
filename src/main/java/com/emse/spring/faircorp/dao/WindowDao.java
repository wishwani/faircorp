package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WindowDao extends JpaRepository<Window,Long>, WindowDaoCustom {

    @Modifying
    @Query("delete from Window w where w.room.id =:roomId")
    void deleteByRoom(@Param("roomId") Long id);



    //List<Window> findRoomOpenWindows(long l);
}
