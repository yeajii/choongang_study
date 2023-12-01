package sam06;
// 생성자 방식과 setter 방식 혼합
// 생성자에 name만 넣고, 나머지는 setter에 적용 

public class VehicleImpl implements Vehicle {
	private String name;
	private String rider;
	private int    speed;
	
	// 생성자
	public VehicleImpl(String name) {
		this.name = name;
	}
	
	// setter 
	public void setRider(String rider) {
		this.rider = rider;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	// 인터페이스 Vehicle의 추상 메소드
	public void ride() {
		// TODO Auto-generated method stub
		System.out.println(name + "님은(는)" + rider + "를 이용 " + speed + "km 속도로 탄다");

	}

}
