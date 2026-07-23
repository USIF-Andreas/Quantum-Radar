package quantumradar;

import java.util.List;

class Fine {
    String plateNumber;
    List<Violation> violations;
    int totalAmount;

    Fine(String plateNumber, List<Violation> violations) {
        this.plateNumber = plateNumber;
        this.violations = violations;
        int sum = 0;
        for (Violation v : violations) {
            sum += v.fee;
        }
        this.totalAmount = sum;
    }

    void print() {
        System.out.println("Traffic for car " + plateNumber);
        System.out.println("Total amount: " + totalAmount + " EGP");
        System.out.println("Violations:");
        for (Violation v : violations) {
            System.out.println("- " + v.description + " : " + v.fee + " EGP");
        }
    }
}
