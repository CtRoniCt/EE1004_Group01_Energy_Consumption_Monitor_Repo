
package e_Save;
import java.util.*;
import java.lang.String;
import java.time.DateTimeException;
import java.time.LocalDate;
/* Record daily usage of power . How much energy each appliance consumes per session get a summary 
 * Total costs 
 */





public class Main {
public static void main(String[] args) {
String name = new String();
Scanner input = new Scanner(System.in);
double electricityRate= 0;
int nrOfAppliances =0;
String tool;
Household household = null;

while(true) {
System.out.print("Enter your name: ");
name = input.nextLine();
if(name.matches("[a-zA-Z ]+")) {
	break;
}
else System.out.println(name); System.out.println("Name can only contain letters.");


}System.out.println(name);


while(true) {	
System.out.print("Enter electricity rate (TL/kWh): ");	
try{electricityRate = input.nextDouble();
if(electricityRate<0) {
System.out.println("Rate cannot be negative!");	
continue;}else if(electricityRate>0) {
	break;
}

}catch(NumberFormatException e) {
	System.out.println("Invalid input,");
	input.nextLine();
}catch(InputMismatchException e) {
	System.out.println("Invalid input");
	input.nextLine();
}
catch(Exception e ) {
	System.out.println("Something went wrong");
	input.nextLine();
}
}System.out.println(electricityRate);
//breaks Enter key
input.nextLine();

while(true) {
	System.out.print("How many appliances do you want to enter? ");	
try{nrOfAppliances = input.nextInt();
if(nrOfAppliances <0) {
	System.out.println("Number cannot be negative!");
}else if(nrOfAppliances == 0 ) {
	System.out.println("Number must not equal zero!");
}
else {break;}
}catch(InputMismatchException e) {
	System.out.println("Use a valid number!");
	input.nextLine();
}
catch(Exception e){
	System.out.println("Invalid output");
	input.nextLine();
}

}System.out.println(nrOfAppliances);
input.nextLine();
//Counts the exact registered appliance number
int applcount = 0;

int counter =0;
double hrUsed =0;
household = new Household(name,electricityRate);
while(nrOfAppliances>counter) {
	applcount++;
	System.out.println("Appliance "+applcount);
System.out.print("Enter type (Refrigerator/WashingMachine/AirConditioner): ");
tool = input.nextLine();
 if(!tool.equalsIgnoreCase("Refrigerator")&&!tool.equalsIgnoreCase("WashingMachine")&&!tool.equalsIgnoreCase("AirConditioner")) 	{
	 System.out.println("Unknown appliance type. Skipping...");
	 counter++;
	 System.out.println(tool);
	 continue;
 }else {
	 System.out.println(tool);
	 
	
 }
 
 while(true) {
	 System.out.print("Enter hours used: ");
	 try{ hrUsed = Double.parseDouble(input.nextLine().trim());
	 if(hrUsed<0) { 
		 System.out.println("Number cannot be negative");
		 continue;
	 }else {
		 counter++;
		 break;}
	 }catch(InputMismatchException e ) {
		 	System.out.println("Wrong input");
	 	
	 }catch(Exception e ) {
		 System.out.println("Something went wrong");
	 }
	 
	 
 }
 System.out.println(hrUsed);




Appliance appliance = null;
LocalDate date = null;
while(true) {
System.out.println("Enter date (yyyy-mm-dd): ");
try{date = LocalDate.parse(input.nextLine().trim());

}catch(DateTimeException e) {
	System.out.print("Invalid date format. Please the yyyy-mm-dd format.");
	continue;
}System.out.println(date);

if(tool.equalsIgnoreCase("Refrigerator")) {
	appliance = new Refrigerator(hrUsed,date);
	
}else if(tool.equalsIgnoreCase("WashingMachine")) {
	appliance = new WashingMachine(hrUsed,date);
}else if(tool.equalsIgnoreCase("AirConditioner")) {
	appliance = new AirConditioner(hrUsed,date);
}


household.addAppliance(appliance);


break;}


}



household.summary();





input.close();
}	
}
