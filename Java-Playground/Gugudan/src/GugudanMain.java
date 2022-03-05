public class GugudanMain {
	public static void main(String[] args) {
		for(int i = 2; i <= 9; i++) {
			int[] result = Gugudan.calc(i);
			System.out.print(i + "단: ");
			Gugudan.print(result);
		}
	}
}