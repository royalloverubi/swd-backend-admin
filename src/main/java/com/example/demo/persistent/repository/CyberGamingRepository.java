package com.example.demo.persistent.repository;

import com.example.demo.persistent.entity.CyberGaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CyberGamingRepository extends JpaRepository<CyberGaming, Integer> {

    CyberGaming findByAccountId(Integer id);

    @Query("SELECT c FROM  CyberGaming c WHERE c.id = :id")
    CyberGaming findByID(@Param("id") Integer id);

    @Query("update CyberGaming c set c.deleted = true where c.id = :id")
    @Modifying
    void deleteByID(@Param("id") Integer id);

    @Query("SELECT c FROM CyberGaming c WHERE c.deleted = false")
    List<CyberGaming> getAll();

    @Query("SELECT c.name FROM CyberGaming c WHERE c.id = :id")
    Object getNameById(@Param("id") Integer id);
}
