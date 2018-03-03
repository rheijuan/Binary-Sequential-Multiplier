import java.util.Scanner;

public class SequentialMultiplier {

	public static void main(String[] args) {
		
		String multiplicand;
		String multiplier;
		Scanner sc = new Scanner(System.in);
		
		// getting binary input for multiplicand
		do {
			System.out.print("Please enter the binary multiplicand: ");
			multiplicand = sc.nextLine();
		} while(!checkString(multiplicand));
		
		// getting binary input for multiplier
		do {
			System.out.print("Please enter the binary multiplier: ");
			multiplier = sc.nextLine();
		} while(!checkString(multiplier));
		
		long A = 0;
		long M = Long.parseLong(multiplicand, 2);
		
		long negM = 
		
	}
	
	
	
	
	public static boolean checkString(String num) {
		// check if the string is less than 16 digits
		if(num.length() <= 16) {
			// check if the number is base 2
			for(int i = 0; i < num.length(); i++) {
				if(Character.getNumericValue(num.charAt(i)) != 1 && Character.getNumericValue(num.charAt(i)) != 0) {
					System.out.println("Number not in base 2. Please try again");
					return false;
				}
			}
			
			return true;
		
		} else {
			System.out.println("Number input too long. Please input again");
			return false;
		}
	}
}