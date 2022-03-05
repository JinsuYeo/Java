import java.util.*;

public class Gugudan {
	public static int inputDan() {
		Scanner scanner = new Scanner(System.in);
		int dan = scanner.nextInt();
		
		return dan;
	}
	
	public static int[] calc(int dan) {
		int[] result = new int[dan];
		for(int i = 2; i <= result.length; i++) {
			for(int j = 0; j < result.length; j++) {
				result[j] = i * (j+1);
			}
			print(result);
		}
		return result;
	}
	
	public static void print(int[] result) {
		for(int j = 0; j < result.length; j++) {
			System.out.print(result[j] + " ");
		}
		System.out.println("\n");
	}
	
	
}