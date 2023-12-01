package DI03;

public class StudentInfo {
	private Student student;   // 클래스를 인스턴스화 함
	
	// 생성자
	public StudentInfo(Student student) {
		this.student = student;
	}
	
	// 메소드
	public void getStudentInfo() {
		if(student != null) {
			System.out.println("이름: " + student.getName());
			System.out.println("나이: " + student.getAge());
			System.out.println("학년: " + student.getGradeNum());
			System.out.println("반: " + student.getClassNum());
			System.out.println("------------------------------------");
		}
	}

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	

}
