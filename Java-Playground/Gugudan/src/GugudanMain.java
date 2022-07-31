public class GugudanMain {
	public static void main(String[] args) {
		String dan = Gugudan.inputDan();
		String[] temp = dan.split(","); // "8,7"과 같은 문자열이 입력됨.
		int first = Integer.parseInt(temp[0]);
		int second = Integer.parseInt(temp[1]);
		Gugudan.calc(first, second);
	}
}