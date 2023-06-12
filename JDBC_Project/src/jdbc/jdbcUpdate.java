package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class jdbcUpdate {

	public static void main(String[] args) {

		//ID를 입력받으세요.
		//수정할 이름과 나이도 입력 받으세요.
		//지목한 ID의 이름과 나이를 새로운 값으로 수정해 보세요.
		//(ID는 존재하는 ID를 넣어 주셔야 수정이 가능합니다.)
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("*** 회원 정보 수정 페이지 ***");
		
		System.out.print("수정할 회원ID를 입력하세요: ");
		String id = sc.next();
		
		System.out.print("수정할 이름을 입력하세요: ");
		String newName = sc.next();
		
		System.out.print("수정할 나이를 입력하세요: ");
		int newAge = sc.nextInt();
		
		// SQL문 작성할 때 띄어쓰기 신경써서 작성해야함!
		String sql = "UPDATE members SET " 
					+ "mem_name = ?, mem_age = ? "
					+ "WHERE mem_id = ?";
		
		//DB 사용자 계정명, 암호, DB url 등 초기 데이터 변수를 설정.
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "hr";
		String upw = "hr";
		
		//사용할 객체의 변수를 미리 선언(finally에서도 사용해야 하니까)
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//JDBC 커넥터 드라이버 호출
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//DB 연동을 위한 클래스들의 객체를 생성
			conn = DriverManager.getConnection(url, uid, upw);	
			//PreparedStatement 객체 : SQL문을 실행하기 위한 객체.
			pstmt = conn.prepareStatement(sql);
			
			//수정할 값 ?에 넣어주기
			pstmt.setString(1, newName);
			pstmt.setInt(2, newAge);
			pstmt.setString(3, id);
			
			int rn = pstmt.executeUpdate();
			
			if(rn == 1) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// DB 연동 객체들 자원 반납
			try {
				pstmt.close();
				conn.close();
				sc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
	}

}




