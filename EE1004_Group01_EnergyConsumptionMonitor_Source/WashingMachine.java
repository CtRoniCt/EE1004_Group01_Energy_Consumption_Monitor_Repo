package e_Save;

import java.time.LocalDate;

public class WashingMachine extends Appliance {

public	WashingMachine(double hoursUsed,LocalDate date){
		super("WashingMachine", hoursUsed,date);
		
		
	}
	
	
	
	
@Override 
public double getKwhPerHour() {
	
	return 2.30;
}
@Override
public String toString() {
	return "- [WashingMachine] used "+getHours() +" hours on "+getDate()+",consumed "+ energyConsumed()+" kWh" ;
	
}
// example of how it needs to look like" - [Refrigerator] used 12.0 hours on 2025-10-16, consumed 1.80 kWh."																							
}
