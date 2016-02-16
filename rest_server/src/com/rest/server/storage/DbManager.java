package com.rest.server.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rest.server.util.Logger;

abstract public class DbManager {
	
	private Integer id = 0;
	private Connection connection;
	private String dbName = "db/server";
	
	public void connect() {
		connect(dbName);
	}
	
	public void connect(String name) {
        try {
            // Load the SQLITE Database Engine JDBC driver
        	Class.forName("org.sqlite.JDBC");
            // connect to the database.
        	Logger.stdOut("Attempting to connect to the database...");
			connection = DriverManager.getConnection("jdbc:sqlite:" + name);
			connection.setAutoCommit(true);
			Logger.stdOut("Connection established...");
		} catch (SQLException e) {
			Logger.stdErr(e.getMessage());
		} catch (ClassNotFoundException e) {
			Logger.stdErr(e.getMessage());
		}
	}
	
    public void shutdown() throws SQLException {

        Statement st = connection.createStatement();

        // db writes out to files and performs clean shuts down
        // otherwise there will be an unclean shutdown
        // when program ends
        st.execute("SHUTDOWN");
        connection.close();    // if there are no other open connection
    }
    
  //use for SQL command SELECT
    public synchronized List<Map<Integer, Object>> query(String expression) throws SQLException {

        Statement st = null;
        ResultSet rs = null;

        st = connection.createStatement();         // statement objects can be reused with

        // repeated calls to execute but we
        // choose to make a new one each time
        rs = st.executeQuery(expression);    // run the query

        List<Map<Integer, Object>> data = getDataFromResultSet(rs);
        
        st.close();    // NOTE!! if you close a statement the associated ResultSet is

        // closed too
        // so you should copy the contents to some other object.
        // the result set is invalidated also  if you recycle an Statement
        // and try to execute some other query before the result set has been
        // completely examined.
        
        return data;
    }

  //use for SQL commands CREATE, DROP, INSERT and UPDATE
    public synchronized long update(String expression) throws SQLException {

        Statement st = null;

        st = connection.createStatement();    // statements

        int i = st.executeUpdate(expression);    // run the query

        ResultSet generatedKeys = st.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getLong(1);
        }

        if (i == -1) {
        	Logger.stdErr("could not execute update: " + expression);
        }

        st.close();
        
        return i;
    }    // void update()

    public static void dump(ResultSet rs) throws SQLException {

        // the order of the rows in a cursor
        // are implementation dependent unless you use the SQL ORDER statement
        ResultSetMetaData meta   = rs.getMetaData();
        int               colmax = meta.getColumnCount();
        int               i;
        Object            o = null;

        // the result set is a cursor into the data.  You can only
        // point to one row at a time
        // assume we are pointing to BEFORE the first row
        // rs.next() points to next row and returns true
        // or false if there is no next row, which breaks the loop
        for (; rs.next(); ) {
            for (i = 0; i < colmax; ++i) {
                o = rs.getObject(i + 1);    // Is SQL the first column is indexed

                // with 1 not 0
                System.out.print(o.toString() + " ");
            }

            Logger.stdOut(" ");
        }
    }
    
    private List<Map<Integer, Object>> getDataFromResultSet(ResultSet rs) throws SQLException {
    	List<Map<Integer, Object>> result = new ArrayList<Map<Integer, Object>>();
    	ResultSetMetaData meta   = rs.getMetaData();
    	int colmax = meta.getColumnCount();
    	int i;
    	Object o = null;
        while (rs.next()) {
        	Map<Integer, Object> cols = new HashMap<Integer, Object>();
        	result.add(cols);
            for (i = 0; i < colmax; ++i) {
                o = rs.getObject(i + 1);    // Is SQL the first column is indexed
                if (o != null) {
                	cols.put(i, o);
                }
            }
        }
    	return result;
    }
    
    protected Integer getNextId() {
    	return ++id;
    }

}
