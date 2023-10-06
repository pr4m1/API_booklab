package com.reservasApi.packages.service.mapper.impl;

import com.reservasApi.packages.controller.models.Booking;
import com.reservasApi.packages.controller.models.common.StructIdName;
import com.reservasApi.packages.repository.models.BookingE;
import com.reservasApi.packages.service.mapper.BookingServiceMapper;

import java.util.ArrayList;

public class BookingServiceMapperImpl implements BookingServiceMapper {
    public BookingServiceMapperImpl(){}
    @Override
    public Booking toController(BookingE bookingE){
        Booking booking = new Booking();
        StructIdName lab = new StructIdName();
        lab.setId(bookingE.getLab().getId());
        lab.setName(bookingE.getLab().getName());
        booking.setId(bookingE.getId());
        booking.setDateStart(bookingE.getDateStart());
        booking.setLab(lab);
        booking.setUsername(bookingE.getUser().getUsername());
        return booking;
    }
    @Override
    public ArrayList<Booking> toController(ArrayList<BookingE> bookingEs){
        ArrayList<Booking> bookings = new ArrayList<>();
        for(BookingE bookingE: bookingEs){
            bookings.add(toController(bookingE));
        }
        return bookings;
    }
    @Override
    public BookingE toRepository(Booking booking){
        BookingE bookingE = new BookingE();
        bookingE.setDateStart(booking.getDateStart());
        return bookingE;
    }
}
