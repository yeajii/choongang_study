package DI01;

public class MyCalculator {
	Calculator calculator;  // 클래스인 Calculator를 인스턴스화 함
	private int firstNum;
	private int secondNum;
	
	// 클래스인 Calculator를 인스턴스화 했으므로, 사용가능하다
	public void add() {
		calculator.addition(firstNum, secondNum);
	}
	public void sub() {
		calculator.subtraction(firstNum, secondNum);
	}
	public void mul() {
		calculator.multiplication(firstNum, secondNum);
	}
	public void div() {
		calculator.division(firstNum, secondNum);
	}
	
	
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	public void setFirstNum(int firstNum) {
		this.firstNum = firstNum;
	}
	public void setSecondNum(int secondNum) {
		this.secondNum = secondNum;
	}
	
	// 생성자
	public MyCalculator() {
		
	}
	
	
	

}
