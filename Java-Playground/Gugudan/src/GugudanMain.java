public class GugudanMain {
	public static void main(String[] args) {
		String dan = Gugudan.inputDan();
		int first = dan.charAt(0) - '0'; // "8,7"과 같은 문자열이 입력됨.
		int second = dan.charAt(2) - '0';
		Gugudan.calc(first, second);
	}
}