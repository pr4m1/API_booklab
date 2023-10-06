package com.reservasApi.packages.repository;


import com.reservasApi.packages.repository.models.BookingE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public interface BookingRepository extends CrudRepository<BookingE,Long> {

    @Query(value = "SELECT * FROM booking ORDER BY date_start ASC",nativeQuery = true)
    ArrayList<BookingE> getBookingsOrderByDateStart();

    @Query("SELECT CASE WHEN EXISTS (SELECT b FROM BookingE b WHERE b.dateStart = :var_date AND b.lab.id = :var_lab) THEN TRUE ELSE FALSE END")
    Boolean existsBookingByDateStartLab(@Param("var_date") LocalDateTime date, @Param("var_lab") long labId);

    @Query("SELECT CASE WHEN COUNT(b) >= m.numberTimeSlotsDay THEN TRUE ELSE FALSE END " +
            "FROM ManagerE m JOIN m.labs l LEFT JOIN l.bookings b JOIN b.user u " +
            "WHERE DATE(b.dateStart) = DATE(:var_dateStart) AND l.id = :var_lab " +
            "AND u.id = :var_user")
    Boolean overMaxNumBookingsDay(@Param("var_dateStart") LocalDateTime dateStart, @Param("var_lab") long labId, @Param("var_user") Long userId);

    @Query("SELECT CASE WHEN COUNT(b) >= m.numberTimeSlotsWeek THEN TRUE ELSE FALSE END " +
            "FROM ManagerE m JOIN m.labs l LEFT JOIN l.bookings b JOIN b.user u " +
            "WHERE YEARWEEK(b.dateStart) = YEARWEEK(:var_dateStart) AND l.id = :var_lab " +
            "AND u.id = :var_user")
    Boolean overMaxNumBookingsWeek(@Param("var_dateStart") LocalDateTime dateStart, @Param("var_lab") long labId, @Param("var_user") Long userId);

    @Query("SELECT CASE WHEN COUNT(b) >= m.numberTimeSlotsTotal THEN TRUE ELSE FALSE END " +
            "FROM ManagerE m JOIN m.labs l LEFT JOIN l.bookings b JOIN b.user u " +
            "WHERE l.id = :var_lab " +
            "AND u.id = :var_user")
    Boolean overMaxNumBookingsTotal(@Param("var_lab") long labId, @Param("var_user") Long userId);


    @Query(value = "SELECT date_start FROM booking WHERE DATE(date_start) BETWEEN DATE_SUB(:var_date, INTERVAL 1 DAY) AND DATE_ADD(:var_date, INTERVAL 1 DAY) AND id_lab=:var_lab",nativeQuery = true)
    ArrayList<Timestamp> getDisabledSlotsTime(@Param("var_date") LocalDate date, @Param("var_lab") long labId);


}
