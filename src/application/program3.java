package application;
// aula que da update nos dados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class program3 {

	public static void main(String[] args) {
		
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"update seller " +
					"set BaseSalary = BaseSalary + ? " +
					"where DepartmentId = ?");
			
			st.setDouble(1, 300);
			st.setInt(2, 2);
			
			int rowsAffecteds = st.executeUpdate();
			
			System.out.println("Done! rowsAffected " + rowsAffecteds);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		

	}

}
