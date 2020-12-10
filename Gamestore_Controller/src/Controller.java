import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//This one is really wierd and maybe not what we want, but I kept it incase
public class Controller {
	private Connection connect = null;
	private Statement statement =null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public void readDataBase() throws Exception{
		try {
			//will load MySQL driver, each DB has own driver
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://loaclhost/feedback?"
					+ "user=sqluser&password=sqluserpw");
			
			statement = connect.createStatement();
			
			//result set get result of SQL query
			resultSet = statement.executeQuery("select * from feedback.comments");
			//writeResultSet(resultSet);
			
			//PreparedStatements can use variables and are more eff
			preparedStatement = connect
					.prepareStatement("insert into feedback.comments values (default, ?, ?, ?, ?, ?, ?)");
			
			//preparedStatement.setInt(1, x);
			preparedStatement.setString(2, "TestTitle");
			preparedStatement.setString(3, "TestPlatform");
			preparedStatement.setString(4, "TestGenre");
			preparedStatement.setString(5, "TestDate");
			preparedStatement.setString(6, "TestRating");
			preparedStatement.setString(7, "TestDeveloper");
			preparedStatement.setString(8, "TestPubisher");
			//preparedStatement.setInt(9, x);
			//preparedStatement.setDouble(10, x);
			preparedStatement.setString(11, "TestIcon");
			preparedStatement.setString(12, "TestDescription");
			
			preparedStatement = connect.prepareStatement("");
			resultSet = preparedStatement.executeQuery();
			//writeResultSet(resultSet);
			
			//removes stuff?
			
			
		} catch(Exception e) {
			throw e;
		} finally {
			close();
		}
		
	}
	
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (connect != null) {
				connect.close();
			}
		} catch(Exception e) {
			
		}
	}
	
}
