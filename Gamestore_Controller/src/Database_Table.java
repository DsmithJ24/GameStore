import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

//GUI table requires a model to define what data appears at which row/column

public class Database_Table extends AbstractTableModel {
	String[] columnNames = {"Title", "Platform", "Price", "In_Stock"};
	
	public ArrayList<Row> table = new ArrayList<Row>();
	
	public void setTable(ArrayList<Row> newTable) {
		table = newTable;
	}
	
	public String getColumnName(int col) {
		return columnNames[col].toString();
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
	}
	
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public void setValueAt(Object value, int row, int col) {
		
	}

}
