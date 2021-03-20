package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

//aula para impedir o conflito de integridade de dados 
public class program5 {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"delete from Department where Id = ?");
			
			
			
			st.setInt(1, 2);
			
			int rowsAffecteds = st.executeUpdate();
			
			System.out.println("Done! rowsAffected " + rowsAffecteds);
		}
		catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

}
