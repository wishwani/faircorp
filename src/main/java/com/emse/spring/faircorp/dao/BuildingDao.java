package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface BuildingDao extends JpaRepository<Building,Long> {

    Optional<Building> findBuildingByName(String name);


    @Query("select w from Window w where w.name=:name")
    Building findByName(@Param("name") String name);

}
