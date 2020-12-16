import java.io.PrintWriter;
import java.sql.ResultSet;

//seems to be the data showing up on table
//for this, want title, platform, price, and number in stock
//can add in all others later
class CartItem {
	//public byte[] game_icon;
	
	public String productNo;
	public int qtyOrdered;
	//from title table
	public String game_icon;
	public String title;
	public String rating;
	
	//from info
	public String developer;
	public String genre;
	public String game_description;
	
	//from inventory
	public String release_date;
	public String publisher;
	public String console;
	public double price;
	public int version_stock;
	
	public CartItem(String game_icon,String title,String rating, String console, String release_date,
			String publisher, double price, int version_stock, String game_description , String genre, String developer) {
		// TODO Auto-generated constructor stub
		this.game_icon = game_icon;
		this.title = title;
		this.rating = rating;
		this.developer = developer;
		this.genre = genre;
		this.game_description = game_description;
		this.release_date = release_date;
		this.publisher = publisher;
		this.console = console;
		this.price = price;
		this.version_stock = version_stock;
		
	}

	//try to get console and genre too
	public String getGameIcon() {
		return game_icon;
	}
	public String getTitle() {
		return title;
	}
	
	public String getRating() {
		return rating; 
	}
	
	public String getPlatform() {
		return console; 
	}
	
	public String getDate() {
		return release_date;
	}

	public String getPublisher() {
		return publisher;
	}
	
	public double getPrice() {
		return price; 
	}
	public int getStock() {
		return version_stock; 
	}
	public String getDescription() {
		return game_description; 
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getDeveloper() {
		return developer;
	}
	/*
	public int getQtyOrdered() {
		return qtyOrdered;
	}
	*/
    
    //this is a response from the database. This will be displayed on the Detail.html
    public void getInfo() {
    	
    }
    
	public void getItem(ResultSet res, PrintWriter out) {
		// TODO Auto-generated method stub
		try {
    		title = res.getString("Title");
    		game_icon = res.getString("Game_Icon");
    		game_description = res.getString("Game_Description");
    		console = res.getString("Platform");
    		genre = res.getString("Genre");
    		
    	}
    	catch (Exception ex) {
    		out.println ("<h1>Connection Error.</h1>");
    	}
	}
	
/*	
	//have this take only productNo and stock 
	
	public String getTitle() {
		return title;
	}
	
	public String getRating() {
		return rating; 
	}
	
	public String getPlatform() {
		return console; 
	}

	public double getPrice() {
		return price; 
	}
	public int getStock() {
		return version_stock; 
	}
	public String getDescription() {
		return game_description; 
	}
	
	public int getQtyOrdered() {
		return qtyOrdered;
	}

	//now the set func
	public void setQtyOrdered(int qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}
	
	public void getItem(ResultSet res, PrintWriter out) {
		try {
			productNo = res.getString("ProductNo");
			title = res.getString("Title");
			price = res.getDouble("Price");
			version_stock = res.getInt("Number_In_Stock");
		}
		catch (Exception ex){
			
		}
	}
	*/
}
