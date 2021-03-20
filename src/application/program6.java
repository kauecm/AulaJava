package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

// aula de transações 
public class program6 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			
			int rows1 = st.executeUpdate("update Seller set BaseSalary = 2090 where DepartmentId = 4");
		
			int rows2 = st.executeUpdate("update Seller set BaseSalary = 3090 where DepartmentId = 3");
			
			conn.commit();
			
			System.out.println("rows1 " + rows1 + " rows2 " + rows2);
			
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transation Rolled back! Caused by:  " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error tryng to Rollback! " + e.getMessage());			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
