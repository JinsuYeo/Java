public class GugudanMain {
	public static void main(String[] args) {
		int dan = Gugudan.inputDan();
		int[] result = Gugudan.calc(dan);
		Gugudan.print(result);
	}
}