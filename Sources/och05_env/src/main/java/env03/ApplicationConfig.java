package env03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration   
public class ApplicationConfig {
	@Value("${admin.id}")
	private String adminId;
	@Value("${admin.pw}")
	private String adminPW;
	@Value("${sub_admin.id}")
	private String sub_adminId;
	@Value("${sub_admin.pw}")
	private String sub_adminPW;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
	// PropertySourcesPlaceholderConfigurer : 환경변수에 개발자의 환경 변수를 append 한다 	
	// 환경변수는 객체가 실행될 때 가장 먼저 실행된다
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		System.out.println("2. Properties Run");

	// Resource: classpath Resource 또는 파일 등을 추상적으로 묘사한 인터페이스	
		Resource[] locations = new Resource[2];
		locations[0] = new ClassPathResource("admin3.properties");
		locations[1] = new ClassPathResource("sub_admin3.properties");
		configurer.setLocations(locations);
		return configurer;
	}
	 
	@Bean
	public AdminConnection adminConnection() {
		AdminConnection adminConnection = new AdminConnection();
		System.out.println("3. adminConfig Run");
		adminConnection.setAdminId(adminId);
		adminConnection.setAdminPw(adminPW);
		adminConnection.setSub_adminId(sub_adminId);
		adminConnection.setSub_adminPw(sub_adminPW);
		
		return adminConnection;
	}

}
