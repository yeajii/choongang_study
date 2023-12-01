package sam07;

public class MessageBeanImpl implements MessageBean {
	private String name;
	private String greet;
	private Outputter outPutter;  // 객체도 맴버변수로 사용 가능 !, 인터페이스 = Outputter
	
	public void setName(String name) {
		this.name = name;
	}

	public void setGreet(String greet) {
		this.greet = greet;
	}

	public void setOutPutter(Outputter outPutter) {
		this.outPutter = outPutter;
	}

	public void sayHello() {
		// TODO Auto-generated method stub
		String msg = name + "님!! " + greet;
		System.out.println(msg);
		if(outPutter != null) outPutter.output(msg);  
		// 인터페이스인 Outputter가 null이 아니면, 여기서 msg를 사용한다는 의미  
	}

}
