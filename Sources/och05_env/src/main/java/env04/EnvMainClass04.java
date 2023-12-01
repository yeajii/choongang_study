package env04;

import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

public class EnvMainClass04 {

	public static void main(String[] args) {
		String config = null;
		System.out.println("system을 입력하세요 ? dev or run");
		Scanner scanner = new Scanner(System.in);
		String str = scanner.next();
		
		if(str.equals("dev")) {
			config = "dev";
		}else if(str.equals("run")) {
			config = "run";
		}
		scanner.close();

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles(config);
		ctx.load("applicationCTX_dev.xml", "appliacationCTX_run.xml");
		
		ctx.refresh();
		
		ServerInfo info = ctx.getBean("serverInfo", ServerInfo.class);
		System.out.println("ip: " + info.getIpNum());
		System.out.println("port: " + info.getPortNum());
		ctx.close();
	}

}
