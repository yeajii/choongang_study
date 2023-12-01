package sam04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex04 {

	public static void main(String[] args) {
		// Classic
		MessgeBeanImpl mb4 = new MessgeBeanImpl();
		mb4.setName("조형택");
		mb4.setGreet("Goog by");
		
		mb4.sayHello();
		
		// DI 방식
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean05.xml");
		MessageBean mb = (MessageBean) ac.getBean("mb5");
		mb.sayHello();
	}

}
