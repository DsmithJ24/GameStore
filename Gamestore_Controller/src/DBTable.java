import java.util.ArrayList;


import javax.swing.table.AbstractTableModel;

public class DBTable extends AbstractTableModel {
	String[] columnNames = {"Title", "Platform", "Price", "In_Stock"};
	
	public ArrayList<Row> table = new ArrayList<Row>();
	
	public void setTable(ArrayList<Row> newTable) {
		table = newTable;
	}
	
	public String getColumnName(int col) {
		return columnNames[col].toString();
	}
/*	
	public Class getCoulumnClass(int col) {
		if (col==1)
		{
			return Icon.class;
		}
		else {
			return getValueAt(0, col).getClass();
		}
	}
*/	
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
/*		
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
*/
	}	
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public void setValueAt(Object value, int row, int col) {
		
	}
}
