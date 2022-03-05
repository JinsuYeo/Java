public class Gugudan {
	public static int[] calc(int dan) {
		int[] result = new int[9];
		for(int i = 0; i < result.length; i++) {
			result[i] = dan * (i+1);
		}
		return result;
	}
	
	public static void print(int[] result) {
		for(int j = 0; j < result.length; j++) {
			System.out.print(result[j] + " ");
		}
		System.out.println("\n");
	}
	
	public static void main(String[] args) {
		for(int i = 2; i <= 9; i++) {
			int[] result = calc(i);
			System.out.print(i + "단: ");
			print(result);
		}
	}
}
