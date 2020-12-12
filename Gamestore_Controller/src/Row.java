//seems to bethe data showing up on table
//for this, want title, platform, price, and number in stock
//can add in all others later
class Row {
	public String title;
	public String console;
	public String price;
	public String version_stock;
	
	Row(String title, String console, String price, String version_stock) {
		this.title = title;
		this.console = console;
		this.price = price;
		this.version_stock = version_stock;
	}
}
