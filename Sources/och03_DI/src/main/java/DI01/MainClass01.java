package DI01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass01 {

	public static void main(String[] args) {
		String configlocation = "classpath:applicationCTX01.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configlocation);
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class);
		// getBean할 때 Ex06.java처럼 캐스팅할 수 있지만, 
		// MyCalculator.class을 사용해서 캐스팅 하지 않고 myCalculator(객체)으로 사용 가능
		
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
		
//		System.out.println("----------- Classic -----------");
//		MyCalculator myCalculator03 = new MyCalculator();
//		Calculator   calculator03   = new Calculator(); 
		
//		myCalculator03.setCalculator(calculator03);
//		myCalculator03.setFirstNum(50);
//		myCalculator03.setSecondNum(5);
		
//		myCalculator03.add();
//		myCalculator03.sub();
//		myCalculator03.mul();
//		myCalculator03.div();

	}

}
