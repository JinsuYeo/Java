public class Gugudan {
	public static void main(String[] args) {
		for(int i = 1; i <= 9; i++) {
			int[] result = new int[9];
			for(int j = 0; j < result.length; j++) {
				result[j] = i * (j+1); 
			}
			System.out.print(i + "단: ");
			for(int j = 0; j < result.length; j++) {
				System.out.print(result[j] + " ");
			}
			System.out.println("\n");
		}
	}
}
