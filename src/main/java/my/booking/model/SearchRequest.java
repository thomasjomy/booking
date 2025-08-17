package my.booking.model;

import my.booking.utils.DateUtil;

import java.time.LocalDate;

public class SearchRequest {
    private final int passengerNum;
    private final String fromDestination;
    private final String toDestination;
    private final LocalDate departDate;


    public SearchRequest(String... args) {
        if(args.length != 4) {throw new IllegalArgumentException("There should be exactly 4 arguments provided");}
        this.passengerNum = validatePassengerNum(args[0]);
        this.departDate = validateDate(args[1]);
        this.fromDestination = isInvalidString(args[2]);
        this.toDestination = isInvalidString(args[3]);

        if(fromDestination.equalsIgnoreCase(toDestination)){
            throw new IllegalArgumentException("From and To Destinations cannot be the same. You input " + fromDestination);
        }

    }

    private LocalDate validateDate(String stringDate) {
        String date = isInvalidString(stringDate);
        return DateUtil.parseInputDate(date);
    }

    private int validatePassengerNum(String inputPassengers) {
        int passengerNum = Integer.parseInt(inputPassengers);
        if(passengerNum < 1 || passengerNum > 7){
            throw new IllegalArgumentException("The number of passengers must be between 1 and 7");
        }
        return passengerNum;
    }

    public String getFromDestination() {
        return fromDestination;
    }

    public String getToDestination() {
        return toDestination;
    }

    public LocalDate getDepartureDate() {
        return departDate;
    }

    public int getPassengerNum() {
        return passengerNum;
    }

    private String isInvalidString(String s){
        if(s == null || s.trim().isEmpty()){
            throw new IllegalArgumentException("You have provided the following argument, but it cannot be null or empty:" + s);
        }
        return s;

    }
}
