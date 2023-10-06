package com.reservasApi.packages.controller;


import com.reservasApi.packages.controller.models.Booking;
import com.reservasApi.packages.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @GetMapping("/all")
    public ArrayList<Booking> getAllBookings(){
        return bookingService.getBookingsOrderByDate();
    }

    @GetMapping("/my")
    public ArrayList<Booking> getUsernameBooking() {
        return bookingService.getUsernameBookings();
    }

    @PostMapping("/save")
    public void saveBooking(@Valid @RequestBody Booking booking){
        bookingService.saveBooking(booking);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteBookingById(@PathVariable("id") long id){
        bookingService.deleteBookingById(id);
    }

    @GetMapping("/disabled/{lab}/{date}")
    public ArrayList<String> getDisabledTimeSlots(@PathVariable("lab") long lab,@PathVariable("date") String dateString){
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").parseStrict().toFormatter();
        LocalDate date = LocalDate.parse(dateString, formatter);
        return bookingService.getDisabledTimeSlots(date,lab);
    };
}
