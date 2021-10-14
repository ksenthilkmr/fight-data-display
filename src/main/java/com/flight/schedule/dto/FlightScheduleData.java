package com.flight.schedule.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;

public class FlightScheduleData {

    private LocalTime departureTime;
    private String destination;
    private String destinationAirportIATA;
    private String flightNo;
    private  DayOfWeek[] dayOfWeek;

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public DayOfWeek[] getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek[] dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDestinationAirportIATA() {
        return destinationAirportIATA;
    }

    public void setDestinationAirportIATA(String destinationAirportIATA) {
        this.destinationAirportIATA = destinationAirportIATA;
    }

    @Override
    public String toString() {
        return "FlightScheduleData{" +
                "departureTime=" + departureTime +
                ", destination='" + destination + '\'' +
                ", destinationAirportIATA='" + destinationAirportIATA + '\'' +
                ", flightNo='" + flightNo + '\'' +
                ", dayOfWeek=" + Arrays.toString(dayOfWeek) +
                '}';
    }
}
