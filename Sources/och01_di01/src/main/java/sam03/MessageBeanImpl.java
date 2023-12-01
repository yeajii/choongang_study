package sam03;

public class MessageBeanImpl implements MessageBean {
	// 맴버변수 
	private String name;
	private String greet;
	
	// 생성자 생성
	// 생성자의 this 키워드를 통해 맴버변수에 값이 들어감
	public MessageBeanImpl(String name, String greet) {
		this.name = name;
		this.greet = greet;
	}
	
	public void sayHello() {
		System.out.println(name + "님!!" + greet);
	}

}
