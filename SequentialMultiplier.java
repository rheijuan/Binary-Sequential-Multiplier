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

		int i = 0;
		StringBuilder builder = new StringBuilder();
		while (i < multiplicand.length()){
			builder.append('0');
			i++;
		}
		String a = builder.toString();
		String mneg = convertTo2sComplement(multiplicand);
		String qneg = "0";

		performOperation(a, multiplicand, mneg, multiplier, qneg);
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

	public static String convertTo2sComplement(String input){
		StringBuilder builder = new StringBuilder(input);
		boolean firstFound = false;

		for (int i = input.length() - 1; i >= 0; i--){
			if (!firstFound && input.charAt(i) == '1') {
				firstFound = true;
				continue;
			}

			if (firstFound){
				if (input.charAt(i) == '0')
					builder.setCharAt(i, '1');
				else
					builder.setCharAt(i, '0');
			}
		}
		input = builder.toString();
		return input;
	}

	public static String add(String a, String m){
		String carry = "0";
		StringBuilder builder = new StringBuilder(a);

		for (int i = a.length() - 1; i >= 0; i--){
			// if i-th bit of a is 1
			if (a.charAt(i) == '1'){

				// if i-th bit of m is 1
				if (m.charAt(i) == '1') {

					//if carry is 1
					if (carry.charAt(0) == '1')
						builder.setCharAt(i, '1');
					else
						builder.setCharAt(i, '0');

					carry = "1";
				}
				else{
					if (carry.charAt(0) == '1') {
						builder.setCharAt(i, '0');
						carry = "1";
					}
					else
						builder.setCharAt(i, '1');
				}
			} //end of if block of if least significant bit of a is 1
			else{ //meaning i-th bit of a is 0
				if (m.charAt(i) == '1'){
					if (carry.charAt(0) == '1'){
						builder.setCharAt(i, '0');
						carry = "1";
					}
					else{
						builder.setCharAt(i, '1');
					}
				}
				else{
					if (carry.charAt(0) == '1'){
						builder.setCharAt(i, '1');
					}
					else{
						builder.setCharAt(i, '0');
					}
				}
			} //end of else block of i-th bit of a
		} // end of for loop
		return builder.toString();
	}

	public static void performOperation(String a, String m, String mneg, String q, String qneg){
		for(int i = 0; i < q.length(); i++) {
			StringBuilder builder = new StringBuilder(q);
			if (q.charAt(q.length() - 1) == '1') { // get the last character of q and check if it is 1
				if (qneg.charAt(0) == '0') {
					a = add(a, mneg);
				}
			}
			else {
				if (qneg.charAt(0) == '1') {
					a = add(a, m);
				}
			}

			//Arithmetic Shift Right algo
			//qneg will take the last character of Q
			qneg = Character.toString(q.charAt(q.length() - 1));

			//shift all bits of q to the right
			for (int j = q.length() - 1; j >= 1; j--){
				builder.setCharAt(j, q.charAt(j-1));
			}

			//first character of q will take last character of a
			builder.setCharAt(0, a.charAt(a.length() - 1));

			// q will be replaced with the new string
			q = builder.toString();

			//builder will now have the string in a
			builder = new StringBuilder(a);

			//shift all bits of a to the right
			for (int j = a.length() - 1; j >= 1; j--){
				builder.setCharAt(j, a.charAt(j-1));
			}

			// first character of a will take whatever is next to it
			builder.setCharAt(0, builder.charAt(1));

			// let a take the new string
			a = builder.toString();

			// display results for this iteration
			System.out.println("Loop " + (i+1));
			System.out.print("A: " + a);
			System.out.print(" Q: " + q);
			System.out.println(" Q-1: " + qneg);
		}

		//display final result
		System.out.println("Final result: " + a + q);
		}
	}
}