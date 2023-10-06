package com.reservasApi.packages.service;

import com.reservasApi.packages.controller.models.Booking;

import java.time.LocalDate;
import java.util.ArrayList;

public interface BookingService {
    ArrayList<Booking> getBookingsOrderByDate();
    ArrayList<Booking> getUsernameBookings();
    void saveBooking(Booking booking);
    Booking getBookingByIdCheckUser(long id);
    void deleteBookingById(long id);
    ArrayList<String> getDisabledTimeSlots(LocalDate date, long id_lab);

}
