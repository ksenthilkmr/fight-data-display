package com.flight.schedule.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class FlightDataDTO {

    private final LocalTime departureTime;
    private final String destination;
    private final String destinationAirport;
    private final String IATA;
    private final String flightNo;
    private final DayOfWeek[] dayOfWeek;

    public FlightDataDTO(FlightDataBuilder flightDataBuilder){
        this.departureTime = flightDataBuilder.departureTime;
        this.destination = flightDataBuilder.destination;
        this.destinationAirport = flightDataBuilder.destinationAirport;
        this.IATA = flightDataBuilder.IATA;
        this.flightNo = flightDataBuilder.flightNo;
        this.dayOfWeek = flightDataBuilder.dayOfWeek;
    }



    public static class FlightDataBuilder{
        private LocalTime departureTime;
        private String destination;
        private String destinationAirport;
        private String IATA;
        private String flightNo;
        private  DayOfWeek[] dayOfWeek;

        public static FlightDataBuilder newInstance()
        {
            return new FlightDataBuilder();
        }

        private FlightDataBuilder() {}

        public FlightDataBuilder setDepartureTime(LocalTime departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        public FlightDataBuilder setDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public FlightDataBuilder setDestinationAirport(String destinationAirport) {
            this.destinationAirport = destinationAirport;
            return this;
        }

        public FlightDataBuilder setIATA(String IATA) {
            this.IATA = IATA;
            return this;
        }

        public FlightDataBuilder setFlightNo(String flightNo) {
            this.flightNo = flightNo;
            return this;
        }

        public FlightDataBuilder setDayOfWeek(DayOfWeek[] dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
            return this;
        }

        public FlightDataDTO build()
        {
            return new FlightDataDTO(this);
        }
    }
}
