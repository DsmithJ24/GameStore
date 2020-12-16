import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import java.sql.PreparedStatement;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import javax.servlet.*;	

//import javax.swing.Icon;
//import javax.swing.table.AbstractTableModel;

//GUI table requires a model to define what data appears at which row/column

public class Order  {
	
	private Connection connection;
	private PrintWriter out;
	private HttpServletRequest request;
	//these seem to be from html
	private CartItem item;
	private int productNo, version_stock, qtyOrdered;
	private String orderId,title, console;
	private double version_price;
	private CartItem inv[];
	public String nextPage;
	
	Order(Connection connection, PrintWriter out){
		this.connection = connection;
		this.out = out;
	}
	
	//for the idex.html, get tile from search
	public void getDataFromClient(HttpServletRequest request) {
		//get the title, platform, and genre from DB
		title = request.getParameter("GameTitle");
		//need drop downs
		//genre = request.getParameter("");
		//console = request.getParameter("Platform");
		//qtyOrdered= 1;
	}
/*	
	public String getItemFromClient(HttpServletRequest request) {
		return orderId;
	}
	
	public int getQtyFromClient(HttpServletRequest request) {
		return qtyOrdered;
	}
*/	
	//gets all details for a game
	public void getDetail() throws Exception {
		Statement stmt = connection.createStatement();
		String sql = "SELECT tit.Game_Icon AS game_icon, tit.Title AS game_title, tit.Rating AS rating, "
				+ "inv.Platform AS console_version, inv.Relase_Date AS date, inv.Publisher AS publisher, "
				+ "inv.price AS version_price, inv.Number_In_Stock AS version_stock, "
				+ "inf.Game_Description AS game_description, inf.Genre AS genre, inf.Developer AS developer" +
				" FROM inventory inv INNER JOIN title tit INNER JOIN info inf USING (ProductNo)";
		
		ResultSet res = stmt.executeQuery(sql);
		//now for each one with same title, store in an array
		int i = 0;
		while (res.next()) {
			
			item = new CartItem(res.getString("game_icon"),res.getString("game_title"),res.getString("rating"),
					res.getString("console_version"),res.getString("date"),res.getString("publisher"),
					res.getDouble("version_price"),res.getInt("version_stock"),
					res.getString("game_description"),res.getString("genre"),res.getString("developer"));
			inv[i] = item;
			i+=i;
		}
		//go to new page
		
	}
	
	//take title and get all info
	private void writeToPage() {
		for (int x=0; x <inv.length; x++) {
			inv[x].getTitle();
			inv[x].getGameIcon();
			inv[x].getDescription();
			inv[x].getPlatform();
			inv[x].getGenre();
		}
	}
	
	private void updateDataBase() throws Exception{
		int newQty = version_stock - qtyOrdered;
		Statement stmt = connection.createStatement();
		String sql = "UPDATE inventory SET Number_In_Stock = '"+newQty+"' WHERE ProductNo ='"+orderId+"'";
	}
	
	private void createOrderForm() {
		//look at stuff from last night
	}
	
/*	
	String[] columnNames = {"Title", "Rating", "Platform", "Price", "In_Stock", "Description"};
	
	public ArrayList<CartItem> table = new ArrayList<CartItem>();
	
	public void setTable(ArrayList<CartItem> newTable) {
		table = newTable;
	}
	
	public String getColumnName(int col) {
		return columnNames[col].toString();
	}
	
	public Class getCoulumnClass(int col) {
		if (col==1)
		{
			return Icon.class;
		}
		else {
			return getValueAt(0, col).getClass();
		}
	}
	
	public int getRowCount() {
		return table.size();
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public Object getValueAt(int row, int col) {
		//if statements for parameters in Row.java
		
		if (col == 0) {
			return table.get(row).title;
		}
		else if (col == 1) {
			return table.get(row).console;
		}
		else if (col == 2) {
			return table.get(row).price;
		}
		else {
			return table.get(row).version_stock;
		}
		
		if (col == 0) {
			return table.get(row).title;
		}
		else if (col == 1) {
			return table.get(row).rating;
		}
		else if (col == 2) {
			return table.get(row).console;
		}
		else if (col == 3) {
			return table.get(row).price;
		}
		else if (col == 4){
			return table.get(row).version_stock;
		}
		else {
			return table.get(row).game_description;
		}
	}
	
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public void setValueAt(Object value, int row, int col) {
		
	}
*/
}
