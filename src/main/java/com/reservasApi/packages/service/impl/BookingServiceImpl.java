package com.reservasApi.packages.service.impl;

import com.reservasApi.packages.controller.models.Booking;
import com.reservasApi.packages.exception.ApiException;
import com.reservasApi.packages.repository.UserRepository;
import com.reservasApi.packages.repository.models.BookingE;
import com.reservasApi.packages.repository.models.LabE;
import com.reservasApi.packages.repository.LabRepository;
import com.reservasApi.packages.repository.BookingRepository;
import com.reservasApi.packages.repository.models.UserE;
import com.reservasApi.packages.security.SecurityConstants;
import com.reservasApi.packages.service.BookingService;
import com.reservasApi.packages.service.UserService;
import com.reservasApi.packages.service.mapper.BookingServiceMapper;
import com.reservasApi.packages.service.mapper.impl.BookingServiceMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private LabRepository labRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    final private BookingServiceMapper bookingServiceMapper;

    public BookingServiceImpl(){
        bookingServiceMapper=new BookingServiceMapperImpl();
    }

    @Override
    public ArrayList<Booking> getBookingsOrderByDate(){
        ArrayList<BookingE> bookingEs = bookingRepository.getBookingsOrderByDateStart();
        bookingEs.removeIf(this::deleteFinishedBooking);
        return bookingServiceMapper.toController(bookingEs);
    }

    @Override
    public ArrayList<Booking> getUsernameBookings() {
        String username = userService.getUsername();
        ArrayList<BookingE> bookingEs = bookingRepository.getBookingsOrderByDateStart();
        bookingEs.removeIf(bookingE -> deleteFinishedBooking(bookingE) || !bookingE.getUser().getUsername().equals(username));
        return bookingServiceMapper.toController(bookingEs);
    }

    @Override
    public void saveBooking(Booking booking) {
        deleteFinishedBookings();
        LabE labE = labRepository.findById(booking.getLab().getId()).orElseThrow(() -> new ApiException("Lab does not exist"));
        UserE userE = userRepository.findByUsername(userService.getUsername()).orElseThrow(() -> new ApiException("User problem!"));
        checkProperties(booking,userE.getId());
        BookingE bookingE = bookingServiceMapper.toRepository(booking);
        bookingE.setLab(labE);
        bookingE.setUser(userE);
        LocalDateTime dateFinish = booking.getDateStart().plusMinutes(labE.getManager().getDuration());
        bookingE.setDateFinish(dateFinish);

        bookingRepository.save(bookingE);
    }

    @Override
    public Booking getBookingByIdCheckUser(long id) {
        BookingE bookingE = bookingRepository.findById(id).orElseThrow(()->new ApiException("Not booking with id '" + id + "' exists."));
        if(!bookingE.getUser().getUsername().equals(userService.getUsername()) && !userService.getAuthority().equals(SecurityConstants.ADMIN)){
            throw new ApiException("You don't have access!");
        }
        return bookingServiceMapper.toController(bookingE);
    }
    @Override
    public void deleteBookingById(long id) {
        Booking booking = getBookingByIdCheckUser(id);
        bookingRepository.deleteById(booking.getId());
    }

    @Override
    public ArrayList<String> getDisabledTimeSlots(LocalDate date, long id_lab) {
        ArrayList<Timestamp> results = bookingRepository.getDisabledSlotsTime(date,id_lab);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        ArrayList<String> disabledSlotsTimeString= new ArrayList<>();
        for (Timestamp timestamp : results) {
            disabledSlotsTimeString.add(formatter.format(timestamp));
        }
        return disabledSlotsTimeString;
    }

    private void deleteBooking(long id) {
        BookingE booking = bookingRepository.findById(id).orElseThrow(()->new ApiException("Not booking with id '" + id + "' exists."));
        bookingRepository.deleteById(booking.getId());
    }
    private void deleteFinishedBookings(){
        ArrayList<BookingE> bookingEs = (ArrayList<BookingE>) bookingRepository.findAll();
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        int resultFinishedBooking;
        for (BookingE bookingE: bookingEs) {
            resultFinishedBooking = bookingE.getDateFinish().compareTo(now);
            if(resultFinishedBooking <= 0) {
                deleteBooking(bookingE.getId());
            }
        }
    }
    private boolean deleteFinishedBooking(BookingE bookingE){
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        int resultFinishedBooking = bookingE.getDateFinish().compareTo(now);
        if(resultFinishedBooking <= 0) {
            deleteBooking(bookingE.getId());
            return true;
        }
        return false;
    }

    private void checkProperties(Booking booking,Long userId) {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime dateStart = booking.getDateStart();
        long idLab = booking.getLab().getId();
        if(dateStart.compareTo(now)<=0){
            throw new ApiException("Not valid date time!");
        }
        if(bookingRepository.existsBookingByDateStartLab(dateStart,idLab)){
            throw new ApiException("There is already a booking for this time");
        }
        if(bookingRepository.overMaxNumBookingsDay(dateStart,idLab,userId)) {
            throw new ApiException("Max number of bookings per DAY reached!");
        }
        if(bookingRepository.overMaxNumBookingsWeek(dateStart,idLab,userId)){
            throw new ApiException("Max number of bookings per WEEK reached!");
        }
        if(bookingRepository.overMaxNumBookingsTotal(idLab,userId)){
            throw new ApiException("Max number of TOTAL bookings reached!");
        }
    }
}
