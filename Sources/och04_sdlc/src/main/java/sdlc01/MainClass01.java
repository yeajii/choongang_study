package sdlc01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass01 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		System.out.println("1. ctx.load before");
		ctx.load("classpath:applicationCTX01.xml");  // 생명주기를 보기 위해 
		System.out.println("2. ctx.load after");
		
		// 실제 bean 생성
		ctx.refresh();
		System.out.println("3. ctx.refresh after");
		ctx.close();
		System.out.println("4. ctx.close after");
	}

}
