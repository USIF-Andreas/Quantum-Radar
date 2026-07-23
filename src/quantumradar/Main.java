package quantumradar;

import java.time.LocalDate;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        QuRadar radar = new QuRadar();

        radar.addRule(new Rule() {
            public Violation check(TrafficObservation o) {
                if (o.carType == CarType.TRUCK && o.speed > 60) {
                    return new Violation("speed of " + o.speed + " exceeded max allowed 60", 300);
                }
                return null;
            }
        });

        radar.addRule(new Rule() {
            public Violation check(TrafficObservation o) {
                if (o.carType == CarType.PRIVATE && o.speed > 80) {
                    return new Violation("speed of " + o.speed + " exceeded max allowed 80", 300);
                }
                return null;
            }
        });

        radar.addRule(new Rule() {
            public Violation check(TrafficObservation o) {
                if (!o.seatbeltFastened) {
                    return new Violation("Seatbelt not fastned", 100);
                }
                return null;
            }
        });

        radar.observe(new TrafficObservation("ABC1234", LocalDate.now(), CarType.PRIVATE, 94, false));
        radar.observe(new TrafficObservation("XYZ5678", LocalDate.now(), CarType.TRUCK, 55, true));
        radar.observe(new TrafficObservation("DEF9012", LocalDate.now(), CarType.TRUCK, 70, false));

        System.out.println("=== Issued Fines ===");
        for (Fine fine : radar.getFines()) {
            fine.print();
            System.out.println();
        }

        System.out.println("=== All Possible Fines (plate -> total) ===");
        for (Map.Entry<String, Integer> entry : radar.getAllPossibleFines().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " EGP");
        }

        System.out.println("\n=== Violated Rules with Count ===");
        for (Map.Entry<String, Integer> entry : radar.getAllViolatedRulesWithCount().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
