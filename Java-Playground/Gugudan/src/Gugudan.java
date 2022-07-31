import java.util.*;

public class Gugudan {
	public static String inputDan() {
		Scanner scanner = new Scanner(System.in);
		String dan = scanner.nextLine();
		scanner.close();

		return dan;
	}

	public static void calc(int first, int second) {
		int[] result = new int[second];
		for (int i = 2; i <= first; i++) {
			for (int j = 0; j < result.length; j++) {
				result[j] = i * (j + 1);
			}
			print(result);
		}
	}

	public static void print(int[] result) {
		for (int j = 0; j < result.length; j++) {
			System.out.print(result[j] + " ");
		}
		System.out.println("\n");
	}

}