package sam07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutputter implements Outputter {
	private String fileName;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void output(String msg) {
		// TODO Auto-generated method stub
		System.out.println("fileName :" + fileName);
		
		try {
			// FileWriter : 텍스트 데이터를 파일에 저장
			FileWriter fw = new FileWriter(new File(fileName));  // 파일을 열고
			fw.write(msg);  									 // 파일에 msg 저장
			fw.close();     
		} catch (IOException e) {
			System.out.println("e.getMessage()-> " + e.getMessage());
		}

	}

}
