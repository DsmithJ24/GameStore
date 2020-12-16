
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import java.awt.BorderLayout;



import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import javax.swing.JTable;
import javax.swing.JScrollPane;

public class DB_Controller extends HttpServlet {
/*	
//public class DB_Controller implements KeyListener, TableModelListener {	
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
	DBTable tableModel = new DBTable();
	
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
		frame = new JFrame("Inventory");
		frame.setBounds(XPOS, YPOS, WIDTH, LENGTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//place search box at the top
		textField = new JTextField();
		textField.setToolTipText("Search by Title");
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		// Place the table in a scroll pane in the center
		table = new JTable(tableModel);
		
		TableColumn column;
		for(int i= 0; i < 5; i++)
		{
			column = table.getColumnModel().getColumn(i);
			
			if (i == 0)
			{
				column.setPreferredWidth(40);
			}
			
			else if (i == 2)
			{
				column.setPreferredWidth(100);
			}
			
			else if (i == 3)
			{
				column.setPreferredWidth(50);
			}
			
			else if (i == 4)
			{
				column.setPreferredWidth(60);
			}
			
			else
			{
				column.setPreferredWidth(200);
			}
			
		}

	//	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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
			" Order by game_title, console_version, version_price, version_stock";
			
			sql = "SELECT tit.Title AS game_title, tit.Rating AS rating, inv.Platform AS console_version, inv.price AS version_price, inv.Number_In_Stock AS version_stock, inf.Game_Description AS game_description" +
			" FROM inventory inv INNER JOIN title tit INNER JOIN info inf USING (ProductNo)" +
			" ORDER BY game_title, rating, console_version, version_price, version_stock, game_description";
						
		}
		
		else {
			sql = "SELECT tit.Title AS game_title, inv.Platform AS console_version, inv.price AS version_price, inv.Number_In_Stock AS version_stock" +
			" FROM inventory inv INNER JOIN title tit USING (ProductNo)" +
			" Where tit.Title Like ?" +
			" Order by game_title, console_version, version_price, version_stock";
			
			sql = "SELECT tit.Title AS game_title, tit.Rating AS rating, inv.Platform AS console_version, inv.price AS version_price, inv.Number_In_Stock AS version_stock, inf.Game_Description AS game_description" +
			" FROM inventory inv INNER JOIN title tit INNER JOIN info inf USING (ProductNo)" +
			"WHERE tit.Title LIKE ?" +
			" ORDER BY game_title, rating, console_version, version_price, version_stock, game_description";
			
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
				//table.add(new Row(res.getString("game_title"),res.getString("rating"),res.getString("console_version"),res.getString("version_price"),res.getString("version_stock"),res.getString("game_description")));
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
	
*/
	
	
	//make this the info controller? needs to take game title, genre, and platform
	
	private static final long serialVersionUID = 1L;
	
	//connect to Database
	private Connection connection;
	private PrintWriter out;
	String sql;
	String DB_PATH = DB_Controller.class.getResource("GameStore.sqlite").getFile();

	
	public void init(ServletConfig conf) throws ServletException {
		//load the sqlite-JDBC driver
		try {
			Class.forName("org.sqlite.JDBC");
			//protocl (jdbc): subprotocol (sqlite):databaseName (store_inventory.sqlite)
			connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);	
		} 
		
		catch (SQLException ex){
			out.println ("<h1>SQL Exception.</h1>");
		}
		catch (ClassNotFoundException cex) {
			out.println ("<h1>Class Not Found Exception.</h1>");
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		//doPost(request, response);
		
		try {
			out = response.getWriter();
			Order order = new Order(connection, out);
			order.getDataFromClient (request);
        	order.getDetail();
        	
        	request.setAttribute(arg0, arg1);
        	connection.close();
		}
		
		catch (IOException ex){
			out.println ("<h1>IO Exception.</h1>");
		}
		catch (ServletException se) {
			out.println ("<h1>Servlet Exception.</h1>");
		}
		catch (Exception e) {
			out.println ("<h1>Database connection exception.</h1>");
		}
		
	}
	
	private void createHeader(String title)
    {
         out.println ("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.0 Transitional//EN'>");
         out.println ("<HTML>");
         out.println ("<head>");
         out.println ("<title>" + title + "</title>");
         out.println ("</head>");
         out.println ("<body>");
    }
	
	 private void createFoot() {
		 out.println ("</body></html>"); 
	 }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get current session or a new one
		HttpSession session = request.getSession(true);
		
		
		//get shopping cart
		@SuppressWarnings("unchecked")
		List<CartItem> theCart = (ArrayList<CartItem>) session.getAttribute("cart");
		
		//next page
		String nextPage = "";
		
		//this should be renamed based on html code
		String todo = request.getParameter("todo");
		
		if (todo == null) {
			//1st access - redirect to html, order i think
			nextPage = "/cart.html";
		}
		
		else if (todo.equals("add")) {
			//use order instead?
			out = response.getWriter();
			Order order = new Order(connection, out);
			order.getDataFromClient (request);
			
			CartItem newCartItem = new CartItem(order.getItemFromClient(request), order.getQtyFromClient(request));
			
			if (theCart == null) {
				theCart = new ArrayList<>();
				theCart.add(newCartItem);
				session.setAttribute("cart", theCart);
			}
			else {
				boolean found = false;
				Iterator iter = theCart.iterator();
				while(!found && iter.hasNext()) {
					CartItem aCartItem = (CartItem)iter.next();
					if (aCartItem.getTitle() == newCartItem.getTitle() && aCartItem.getPlatform() == newCartItem.getPlatform()) {
						//need to set quantity ordered
						//aCartItem.setQtyOrdered()
						found = true;
					}
				}
				
				if (!found) {// add it to cart
					theCart.add(newCartItem);
				}
			}
			
			//back to page to order more
			nextPage = "/index.html";
		}
		
		else if (todo.equals("remove")) {
			int cartIndex = Integer.parseInt(request.getParameter("cartIndex"));
			theCart.remove(cartIndex);
			//back to page for more
			nextPage = "/index.html";
		}
		
		else if (todo.equals("checkout")) {
			//submit by index.html?
			//compute total price for all items in cart
			double totalPrice = 0;
			int totalQtyOrdered = 0;
			for (CartItem item: theCart) {
				double price = item.getPrice();
				int qtyOrdered = item.getQtyOrdered();
				totalPrice += price* qtyOrdered;
				totalQtyOrdered += qtyOrdered;
			}
			//convert price in a float?
			StringBuilder sb = new StringBuilder();
			Formatter formatter = new Formatter(sb);
			formatter.format("%.2f", totalPrice);
			
			//put total price and qty in request header
			request.setAttribute("totalPrice", sb.toString());
			request.setAttribute("totalQtyOrdered", totalQtyOrdered + "");
			
			//now to a checkoutpage
		}
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}