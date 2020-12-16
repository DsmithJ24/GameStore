# GameStore

This program is the controller for the project. The original goal was to use the controller to link the html files to a database and to do actions based on inputs sent from the
html files. Unfortunately, the html webpage connection does not work. All work done for the unfinished html connection can be found in DB_Controller.java.
Controller.java brings up a simple GUI to search through the database and give back titles that match what has been typed in a search bar. The GUI also shows
the platform for each entry, price, and version stock. Row.Java and DB_Table.java are required to run along Controller.java. CartItem.java and Order.java were intended to
work with DB_Controller.Java and has some code to take sql queries. However, these do not run or work properly, only Controller.java will run.
