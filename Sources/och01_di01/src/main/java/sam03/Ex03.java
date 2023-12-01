package sam03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex03 {

	public static void main(String[] args) {
		// Classic
		// 메소드 안에서 맴버변수 공유하므로 값이 출력됨
		MessageBean cmb = new MessageBeanImpl("황인정", "전통적 안녕");
		cmb.sayHello();
		
		// DI 방식의 호출 객체 
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean03.xml");
		MessageBean mb = (MessageBean) ac.getBean("mb3");
		mb.sayHello();
	}

}
