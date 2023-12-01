package sam04;

public class MessgeBeanImpl implements MessageBean {
	private String name;
	private String greet;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGreet() {
		return greet;
	}

	public void setGreet(String greet) {
		this.greet = greet;
	}

	// 자동으로 생기는 것임
	public void sayHello() {
		System.out.println(name + "님 " + greet + "!!");
	}

}
