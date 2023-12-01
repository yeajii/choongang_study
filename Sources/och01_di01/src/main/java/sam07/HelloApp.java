package sam07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		// bean.xml은 resources에 두어야 하는데, 그렇게 하기 싫고 bean 파일을 같은 폴더에 두고 싶을 때  루트를 지정해서 사용 가능
		// 보통 이렇게 하기보다 resources에 둔다 (헷갈리지 않기 위해)
		ApplicationContext ac = new ClassPathXmlApplicationContext("/sam07/bean07.xml");
		MessageBean mb = (MessageBean) ac.getBean("mb7");
		mb.sayHello();
	}

}
