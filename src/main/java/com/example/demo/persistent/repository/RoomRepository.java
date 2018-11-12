package com.example.demo.persistent.repository;

import com.example.demo.persistent.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.deleted = false ")
    List<Room> getALL();

    @Query("SELECT r FROM Room r WHERE r.id = :id")
    Room findByID(@Param("id") Integer id);

    @Query("update Room r SET r.deleted = true WHERE r.id = :id")
    @Modifying
    void deleteByID(@Param("id") Integer id);

    @Query("SELECT r.name FROM Room r WHERE r.id = :id")
    Object getNameById(@Param("id") Integer id);

    @Query("SELECT r.price FROM Room r WHERE r.id = :id")
    Object getPriceById(@Param("id") Integer id);

    @Query("SELECT r FROM Room r WHERE r.cyberGamingId = :cyberGamingId AND r.deleted = false ")
    List<Room> getByCyberGamingId(@Param("cyberGamingId") Integer cyberGamingId);
}
