package com.example.demo.persistent.repository;

import com.example.demo.persistent.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Integer> {

    @Query("SELECT s FROM ServiceRequest s WHERE s.id = :id")
    ServiceRequest getByID(@Param("id") Integer id);

    @Query("UPDATE ServiceRequest s SET s.deleted= true WHERE s.id = :id")
    @Modifying
    void deleteByID(@Param("id") Integer id);

    @Query("SELECT s FROM ServiceRequest s WHERE s.deleted = false AND s.approved = false AND s.cyberGamingId = :cyberId ORDER BY s.dateRequest DESC ")
    List<ServiceRequest> getListNeedToAproving(@Param("cyberId") Integer cyberId);

    @Query("UPDATE ServiceRequest s SET s.approved = true WHERE s.id = :id")
    @Modifying
    void approveRequest(@Param("id") Integer id);

    @Query("UPDATE ServiceRequest s SET s.done = true WHERE s.id = :id")
    @Modifying
    void completeRequest(@Param("id") Integer id);

    @Query("SELECT s FROM ServiceRequest s WHERE s.deleted = false AND s.approved = true AND s.done = false AND s.cyberGamingId = :cyberId ORDER BY s.dateRequest DESC")
    List<ServiceRequest> getListNeedToDone(@Param("cyberId") Integer cyberId);
}
