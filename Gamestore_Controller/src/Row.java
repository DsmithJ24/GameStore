
public class Row {
	public String console;
	public String title;
	public String rating;
	public String price;
	public String version_stock;
	public String game_description;
/*	
	Row(String title, String rating, String console, String price, String version_stock, String game_description) {
		this.title = title;
		this.rating = rating;	
		this.console = console;
		this.price = price;
		this.version_stock = version_stock;
		this.game_description = game_description;
	
	}
*/	
	Row(String title,String console, String price, String version_stock) {
		this.title = title;	
		this.console = console;
		this.price = price;
		this.version_stock = version_stock;
	
	}
}
