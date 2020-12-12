import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.BorderLayout;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class DB_Controller implements KeyListener, TableModelListener {
	//globals for window
	public final static int XPOS = 600;
	public final static int YPOS = 200;
	public final static int WIDTH = 750;
	public final static int LENGTH = 600;
	
	//connect to Database
	Connection connection;
	String sql;
	String DB_PATH = DB_Controller.class.getResource("GameStore.sqlite").getFile();
	
	//GUI objects
	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JScrollPane scrollPane;
	
	// Connect database with table
	Database_Table tableModel = new Database_Table();
	
	public DB_Controller() throws ClassNotFoundException, SQLException {
		//load the sqlite-JDBC driver
		Class.forName("org.sqlite.JDBC");
				
		//protocl (jdbc): subprotocol (sqlite):databaseName (store_inventory.sqlite)
		connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);	
		
		//initialize GUI
		initializeGUI();

		// Initialize event handlers
		initializeListeners();
	}
	
	private void initializeGUI() throws ClassNotFoundException, SQLException {
		//set up the main window
		frame = new JFrame();
		frame.setBounds(XPOS, YPOS, WIDTH, LENGTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//place search box at the top
		textField = new JTextField();
		textField.setToolTipText("Search by Title");
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		// Place the table in a scroll pane in the center
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}
	
	// Listeners (event handlers) define what to do in response to events.
	// GUIs use on event handlers to bind user actions with computation.
	private void initializeListeners() {
		//when user types something, tell table to update itself
		textField.addKeyListener(this);
		
		// Define what to do when the table needs to be updated
		// That is, run queries on user input
		// Registering event handler to DatabaseTableModel
		// addTableModelListener need TableModelListener as an argument - used as an Innerclass		
		tableModel.addTableModelListener(this);
		
		// Load the table on startup
		tableModel.fireTableDataChanged();
	}
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	
	public void keyReleased(KeyEvent e) {
		// fireTableDataChanged() is a method in AbstractTableModel
		// This method calls tableChanged method
		// This is defined in swing
		// We implement database query to update the table in the tableChanged() method
		tableModel.fireTableDataChanged();
	}			
		
	public void tableChanged(TableModelEvent e) {	
		String param = textField.getText();
		
		// generate parameterized sql
		if (param.equalsIgnoreCase("")) {
			sql = "SELECT tit.Title AS game_title, inv.Platform AS console_version, inv.price AS version_price, inv.Number_In_Stock AS version_stock" +
			" FROM inventory inv INNER JOIN title tit USING (ProductNo)" +
			" ORDER BY game_title, console_version, version_price, version_stock";			
		}
		
		else {
			sql = "SELECT tit.Title AS game_title, inv.Platform AS console_version, inv.price AS version_price, inv.Number_In_Stock AS version_stock" +
			" FROM inventory inv INNER JOIN title tit USING (ProductNo)" +
			"WHERE tit.Title LIKE ?" +
			" ORDER BY game_title, console_version, version_price, version_stock";
		}
		
		// System.out.println("\nSQL: " + sql + "\n");
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			if(!param.equalsIgnoreCase("")) {
				stmt.setString(1, "%" + param + "%");
			}
			
			//get results
			ResultSet res = stmt.executeQuery();
			
			//transfer data from database to GUI
			ArrayList<Row> table = new ArrayList<Row>();
			
			while(res.next()) {
				table.add(new Row(res.getString("game_title"),res.getString("console_version"),res.getString("version_price"),res.getString("version_stock")));
			}
			tableModel.setTable(table);
		} 
		
		catch(Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DB_Controller window = new DB_Controller();
		window.frame.setVisible(true);
	}
	
}