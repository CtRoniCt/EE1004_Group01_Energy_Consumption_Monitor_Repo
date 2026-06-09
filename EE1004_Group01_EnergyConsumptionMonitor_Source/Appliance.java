package e_Save;

import java.time.LocalDate;

public abstract class Appliance {
 private String name ="Electricity User";
 private double hoursUsed;
 private LocalDate date;
 public Appliance(String name, double hoursUsed,LocalDate date) {
	 this.name =name;
	 this.hoursUsed = hoursUsed;
	 this.date = date;
 }
 abstract double getKwhPerHour();
 
 //Concrete method
 public double getEnergyConsumed() {
	 return Math.round(hoursUsed * getKwhPerHour()*100.0)/100.0;
 }
 
 public String energyConsumed() {
	 return String.format("%.2f",getEnergyConsumed());
 }
 public abstract String toString();
 public String getName() {return name;}
 public double getHours() {return hoursUsed;}
 public LocalDate getDate() {return date;}
 
}
