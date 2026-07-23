package quantumradar;

import java.time.LocalDate;

enum CarType {
    PRIVATE, TRUCK, BUS
}

public class TrafficObservation {
    String plateNumber;
    LocalDate date;
    CarType carType;
    int speed;
    boolean seatbeltFastened;

    TrafficObservation(String plateNumber, LocalDate date, CarType carType, int speed, boolean seatbeltFastened) {
        this.plateNumber = plateNumber;
        this.date = date;
        this.carType = carType;
        this.speed = speed;
        this.seatbeltFastened = seatbeltFastened;
    }
}
