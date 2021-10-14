package com.flight.schedule.resource;

import com.flight.schedule.dto.FlightScheduleData;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JourneyPlanner {
    private final static String COMMA_DELIMITER = ",";
    public static void main(String[] args) {
    JourneyPlanner jp = new JourneyPlanner();
    jp.userInputDate();
    }

    private  void searchAvailableFlightOnDay(String day) {
        List<FlightScheduleData> flightScheduleDataList = intializeFlightData();
        List<FlightScheduleData> result = flightScheduleDataList.stream()
                .filter(hasFlight(day)).sorted(Comparator.comparing(FlightScheduleData::getDepartureTime))
                .collect(Collectors.toList());
        String[] headerList =new String[] {"DepartureTime","Destination","DestinationAirportIATA", "FlightNo"};
        for (int i=0 ; i<headerList.length; i++){
            System.out.printf("%-30s", headerList[i]);
        }
        System.out.println("");
        for (FlightScheduleData flightData: result) {
            System.out.printf("%-30s", flightData.getDepartureTime());
            System.out.printf("%-30s", flightData.getDestination());
            System.out.printf("%-30s", flightData.getDestinationAirportIATA());
            System.out.printf("%-30s", flightData.getFlightNo());
            System.out.println("");
        }
    }



    private Predicate<FlightScheduleData> hasFlight(final String day){
        return flightScheduleData -> Arrays.stream(flightScheduleData.getDayOfWeek())
                .anyMatch(dayOfWeek -> (null != dayOfWeek) && (dayOfWeek.name().equals(day)));
    }

    /**
     * This method Assumes the USER will ennter PROPER YEAR, MONTH, DATE
     * NO VALIDATION is done for simplicity
     * @return
     */
    private void userInputDate() {
        // Prompt the user to enter year
        Scanner scanner = new Scanner(System.in);
        int year,month,theDay =0;
        do{
            // Prompt the user to enter year
            System.out.print("Enter year (e.g.1999): ");
             year = scanner.nextInt();
            // Prompt the user to enter month
            System.out.print("Enter month (1-12): ");
             month = scanner.nextInt();
            // Prompt the user to enter Date
            System.out.println("Enter the date (1-31): ");
             theDay = scanner.nextInt();
             if(year !=0 && month !=0 && theDay !=0 ){
                 String day = LocalDate.of(year, month, theDay).getDayOfWeek().name();
                 System.out.println("Finding ALL flights which Runs on: "+day);
                 searchAvailableFlightOnDay(day);
             }

            System.out.println(" Exit Search? Enter 0:");
        }while(theDay >0 && year >0 && month >0);
        scanner.close();

    }

    public List<FlightScheduleData> intializeFlightData(){
        List<FlightScheduleData> result = new ArrayList<>();
        try{
             result = Files.readAllLines(Paths.get("/home/senthil/projects/FlightDisplay/src/main/resources/flights.csv"))
                    .stream().skip(1)
                    .map(mapLinesToFlightDTO)
                    .collect(Collectors.toList());

        }catch (Exception e){
            System.out.println("Exception Occured!!!!");
            e.printStackTrace();
        }
    return result;
    }

    private Function<String, FlightScheduleData> mapLinesToFlightDTO = (line) -> {
        // a CSV has comma separated lines
        // limit -1 ensures it will not skip when the value is empty
        String[] flightLine = line.split(COMMA_DELIMITER,-1);
        FlightScheduleData flightScheduleData = new FlightScheduleData();
        flightScheduleData.setDepartureTime(LocalTime.parse(flightLine[0].trim()));
        flightScheduleData.setDestination(flightLine[1].trim());
        flightScheduleData.setDestinationAirportIATA(flightLine[2].trim());
        flightScheduleData.setFlightNo(flightLine[3].trim());
        DayOfWeek[] scheduledDay =new DayOfWeek[7];
        if(null != flightLine[4] && !"".equals(flightLine[4])){
            scheduledDay[0] = DayOfWeek.SUNDAY;
        }

        if(null != flightLine[5] && !"".equals(flightLine[5])){
            scheduledDay[1] = DayOfWeek.MONDAY;
        }

        if(null != flightLine[6] && !"".equals(flightLine[6])){
            scheduledDay[2] = DayOfWeek.TUESDAY;
        }

        if(null != flightLine[7] && !"".equals(flightLine[7])){
            scheduledDay[3] = DayOfWeek.WEDNESDAY;
        }

        if(null != flightLine[8] && !"".equals(flightLine[8])){
            scheduledDay[4] = DayOfWeek.THURSDAY;
        }

        if(null != flightLine[9] && !"".equals(flightLine[9])){
            scheduledDay[5] = DayOfWeek.FRIDAY;
        }

        if(null != flightLine[10] && !"".equals(flightLine[10])){
            scheduledDay[6] = DayOfWeek.SATURDAY;
        }

        flightScheduleData.setDayOfWeek(scheduledDay);

        return flightScheduleData;
    };

}
