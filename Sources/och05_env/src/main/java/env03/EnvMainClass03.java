package env03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EnvMainClass03 {

	public static void main(String[] args) {
		System.out.println("1. EnvMainClass03 Run...");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		System.out.println("4. EnvMainClass03 adminConfig before run");
		
		AdminConnection connection = ctx.getBean("adminConnection", AdminConnection.class);
		System.out.println("5. EnvMainClass03 adminConnection after run");
		
		System.out.println("connection.getAdminId : " + connection.getAdminId());
		System.out.println("connection.getAdminPw : " + connection.getAdminPw());
		System.out.println("connection.getSub_adminId : " + connection.getSub_adminId());
		System.out.println("connection.getSub_adminPw : " + connection.getSub_adminPw());
		ctx.close();
	}

}
