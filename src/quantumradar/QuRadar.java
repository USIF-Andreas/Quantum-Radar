package quantumradar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
  Quantum Radar system
  takes info from radar: plate number, date, car type (private, truck, bus), speed, seatbelt
  checks rules and makes fines if violations found
  AI model: (YOLO 13) uses pattern matching to detect violations
  you can add new rules without changing this class
 */
public class QuRadar {
  private List<Rule> rules;
  private List<Fine> fines;

  public QuRadar() {
   rules = new ArrayList<>();
      fines = new ArrayList<>();
  }

 public void addRule(Rule rule) {
        rules.add(rule);
  }

  public void observe(TrafficObservation observation) {
   List<Violation> violations = new ArrayList<>();

  for (Rule rule : rules) {
   Violation v = rule.check(observation);
   if (v != null) {
    violations.add(v);
     }
   }

  if (violations.size() > 0) {
   Fine f = new Fine(observation.plateNumber, violations);
            fines.add(f);
    }
 }

   public Map<String, Integer> getAllPossibleFines() {
  Map<String, Integer> result = new LinkedHashMap<>();
  int total = 0;

   for (int i = 0; i < fines.size(); i++) {
    Fine f = fines.get(i);
    String plate = f.plateNumber;
    int amount = f.totalAmount;

     if (result.containsKey(plate) == true) {
      int old = result.get(plate);
   
     result.put(plate, old + amount);
    } else {
     result.put(plate, amount);
    }
     }
  return result;
 }

    public Map<String, Integer> getAllViolatedRulesWithCount() {
  Map<String, Integer> result = new LinkedHashMap<>();

  for (Fine f : fines) {
   
   List<Violation> vv = f.violations;
   
    for (int j = 0; j < vv.size(); j++) {
   
    Violation v = vv.get(j);
    String desc = v.description;

    if (result.containsKey(desc)) {
   
       int c = result.get(desc);
   
      result.put(desc, c + 1);
    } else {
   
      result.put(desc, 1);
       }
     }
   }

        return result;
 }

   public List<Fine> getFines() {
  return fines;
   }
}
