package e_Save;

import java.time.LocalDate;

public class Refrigerator extends Appliance{

	Refrigerator(double hoursUsed,LocalDate date){
		super("Refrigerator", hoursUsed,date);
		
		
	}	
	
	
	@Override
	public double getKwhPerHour() {
	return 0.15;	 	
		
	}
	
	@Override
	public String toString() {
		return "- [Refrigerator] used "+getHours() +" hours on "+getDate()+", consumed "+ energyConsumed()+" kWh." ;
		
		
	}

}
