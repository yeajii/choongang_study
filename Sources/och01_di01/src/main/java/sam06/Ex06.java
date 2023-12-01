package sam06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex06 {

	public static void main(String[] args) {
		// ClassPathXmlApplicationContext : interface인 ApplicationContext를 구현한 class이다
		// ClassPathXmlApplicationContext : classPath에 위치한 xml 파일을 읽어 설정 정보를 로딩, root로부터 경로를 지정한다 
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean06.xml");
		Vehicle vh = (Vehicle) ac.getBean("vh6");
		vh.ride();

	}

}
