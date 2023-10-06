package com.reservasApi.packages.repository;

import com.reservasApi.packages.repository.models.LabE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public interface LabRepository extends CrudRepository<LabE,Long> {

    @Query(value = "SELECT * FROM lab WHERE id_manager=:var_param",nativeQuery = true)
    ArrayList<LabE> getLabsByManagerId(@Param("var_param") long idManager);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END " +
            "FROM ManagerE m JOIN m.labs l LEFT JOIN l.bookings b JOIN b.user u " +
            "WHERE b.dateStart <= :var_time AND b.dateFinish > :var_time AND l.id = :var_lab " +
            "AND u.username = :var_username")
    Boolean isUserLabTime(@Param("var_time") LocalDateTime time, @Param("var_lab") long labId, @Param("var_username") String username);
}
