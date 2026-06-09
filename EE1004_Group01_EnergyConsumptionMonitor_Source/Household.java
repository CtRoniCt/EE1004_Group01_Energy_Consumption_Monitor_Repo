package e_Save;
import java.util.*;



public class Household {
private String ownerName;
private double electricityRate=0; // kwh to TL

 public Household(String ownerName,double electricityRate){
	 this.ownerName = ownerName;
	 this.electricityRate = electricityRate;
	 this.appliances = new ArrayList<>();
 }
 private List<Appliance> appliances;
public void addAppliance(Appliance appliance) {
	appliances.add(appliance);
}

public double totalEnergyConsumed() {
double	energy=0;
	for(Appliance a: appliances) {
		energy += a.getEnergyConsumed();
	}
	return Math.round(energy*100.0)/100.0;
}
public double totalCost() {
	return Math.round(totalEnergyConsumed() * electricityRate*100.0)/100.0;	
	
}


public void summary() {
	System.out.println("========== Energy Report for " + ownerName+ " ==========");
	for(Appliance n: appliances) {
		System.out.println(n.toString());
	}
	System.out.printf("Total Energy Consumed: %.2f kWh%n",totalEnergyConsumed());
	System.out.printf("Total Cost: %.2f TL%n",totalCost());
	
}

public String getOwnerName() {
	return ownerName;
}
}


