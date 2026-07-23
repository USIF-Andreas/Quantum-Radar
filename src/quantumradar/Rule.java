package quantumradar;

interface Rule {
    Violation check(TrafficObservation observation);
}
