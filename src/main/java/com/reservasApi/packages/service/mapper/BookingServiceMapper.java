package com.reservasApi.packages.service.mapper;

import com.reservasApi.packages.controller.models.Booking;
import com.reservasApi.packages.controller.models.common.StructIdName;
import com.reservasApi.packages.repository.models.BookingE;

import java.util.ArrayList;

public interface BookingServiceMapper {
    Booking toController(BookingE bookingE);
    ArrayList<Booking> toController(ArrayList<BookingE> bookingEs);
    BookingE toRepository(Booking booking);
}
