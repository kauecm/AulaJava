package application;
// aula que da insert nos dados 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class pragram2 {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("insert into seller " + "(Name, Email, Birthdate, BaseSalary, DepartmentId) "
					+ "VALUES " + "(?,?,?,?,?)",
					
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, "Kaue Costa");
			st.setString(2, "kaue@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("06/11/1996").getTime()));
			st.setDouble(4, 3500.0);
			st.setInt(5, 3);
			
			int rowsAffecteds = st.executeUpdate();

			if(rowsAffecteds > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! ID: " + id);
				}
			}else {
				System.out.println("No RowsAffecteds");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
