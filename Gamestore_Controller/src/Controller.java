import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//This is the basic version based off demo files. should still work
//This one is based off the files Logan shared in Discord. Work on this one

public class Controller {
	Connection connection;
	String sql;
	String DB_PATH = DB_Controller.class.getResource("GameStore.sqlite").getFile();
	
	//used for testing
	//String DB_PATH = DB_Controller.class.getResource("Chinook_Sqlite_AutoIncrementPKs.sqlite").getFile();
	
	
	public Controller() throws ClassNotFoundException, SQLException {
		//load the sqlite-JDBC driver
		Class.forName("org.sqlite.JDBC");
		
		//protocl (jdbc): subprotocol (sqlite):databaseName (store_inventory.sqlite)
		connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
	}
	
	public void run() throws SQLException {
		Scanner input = new Scanner(System.in);
		
		//now get the inputs
		System.out.print("Enter an game title (type n/a for none): ");
		String param = input.next();
		
		//generate parameterized sql
		if (param.equalsIgnoreCase("n/a")) {
			//this will fetch the Console, price, and #in stock for entered title
			//need title, platform, price, and # in stock
			sql = "SELECT tit.Title AS game_title, inv.Platform AS console_version, inv.price AS version_price, inv.Number_In_Stock AS version_stock" +
			" FROM inventory inv INNER JOIN title tit USING (ProductNo)" +
			" ORDER BY game_title, console_version, version_price, version_stock";
			
		/*
			//testing
			sql = "SELECT art.Name AS art_name, alb.Title AS alb_title" +
			" FROM album alb INNER JOIN artist art USING (ArtistId)" +
			" ORDER BY art_name, alb_title";
		*/
		
			
		} else {
			//probably just have this print a message or whole inventory
			sql = "SELECT tit.Title AS game_title, inv.Platform AS console_version, inv.price AS version_price, inv.Number_In_Stock AS version_stock" +
			" FROM inventory inv INNER JOIN title tit USING (ProductNo)" +
			"WHERE tit.Title LIKE ?" +
			" ORDER BY game_title, console_version, version_price, version_stock";
			
		/*	
			//testing
			sql = "SELECT art.Name AS art_name, alb.Title AS alb_title" +
			" FROM artist art INNER JOIN album alb USING (ArtistId)" +
			" WHERE art.Name LIKE ?" +
			" ORDER BY art_name, alb_title";
		*/
			
		}
		
		System.out.println("\nSQL: " + sql + "\n");
		
		/*
		 * prepare statement (never use string concatention to build query)
		 * to avoid SQL injection
		 * never do "Where art.Name LIKE" + param + "ORDER BY..."
		 */
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		/*
		 * bind parameter(s)
		 * In SQLite "?" is a placeholder (acts like variable)
		 * 1 is the first place holder, 2 is second, etc.
		 */
		
		if (!param.equalsIgnoreCase("n/a")) {
			stmt.setString(1, param); //this is a dummy, replace based on database
			
		}
		
		//get results
		ResultSet res = stmt.executeQuery();
		while(res.next()) {
			System.out.println("<" + res.getString("game_title") + ">" + res.getString("console_version") + " " + res.getString("version_price") + " In Stock: " +res.getString("version_stock"));
			
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Controller controller = new Controller();
		controller.run();
	}
}
