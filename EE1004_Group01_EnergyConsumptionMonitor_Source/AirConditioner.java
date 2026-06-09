package e_Save;

import java.time.LocalDate;
import javax.swing.*;


public class AirConditioner extends Appliance {
	AirConditioner(double hoursUsed,LocalDate date){
		super("AirConditioner", hoursUsed,date);
		
		
	}
@Override
public double getKwhPerHour() {
	
	return 1.80;
}

@Override
public String toString() {
	return "- [AirConditioner] used "+getHours() +" hours on "+getDate()+",consumed "+ energyConsumed()+" kWh." ;
	
}
}
