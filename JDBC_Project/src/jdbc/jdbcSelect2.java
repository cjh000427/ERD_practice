package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class jdbcSelect2 {

	public static void main(String[] args) {

		/*
		 사용자에게 Scanner를 이용하여 job_id를 입력받습니다.
		 입력받은 job_id에 해당하는 사람들의 first_name, salary를
		 콘솔창에 출력해 주세요. (employees 테이블 사용.)
		 조회된 내용이 없다면 조회 결과가 없다고 출력해 주세요.
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("조회하실 job_id를 입력하세요: ");
		String id = sc.next();
		
		String sql = "SELECT first_name, salary FROM employees WHERE job_id = ?";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "hr";
		String upw = "hr";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // SELECT 문에서만 사용하는 객체.
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				String name = rs.getString("first_name");
				int salary = rs.getInt("salary");
				count++;
				System.out.printf("조회결과 \n %s \n $%d \n", name, salary);
				System.out.println("----------------------------------");
			}
			if(count == 0) {
				System.out.println("조회 결과가 없습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
				sc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}

}
