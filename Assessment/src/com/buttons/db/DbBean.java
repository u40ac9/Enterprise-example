package com.buttons.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class that manages JDBC connections
 * 
 * @author Marius Fjeld Wold 
 *
 */
public class DbBean {

	//Derby settings
	String dbURL = "jdbc:derby://localhost:1527/buttons;create=true;username=user;password=pas";
	
	
	String dbDriver = "org.apache.derby.jdbc.ClientDriver";

	private Connection dbCon;
	private Statement stmt;

	public DbBean(){
		super();
	}

	public boolean connect()
	throws
	ClassNotFoundException,
	SQLException,
	InstantiationException,
	IllegalAccessException {
		Class.forName(this.getDbDriver());
		dbCon = DriverManager.getConnection(this.getDbURL());
		return true;
	}
	public void close() throws SQLException {
		dbCon.close();
	}

	public void commit() throws SQLException {
		dbCon.commit();
	}

	public void setAutoCommit(boolean autocommit) throws SQLException {
		dbCon.setAutoCommit(autocommit);
	}

	public void rollback() throws SQLException {
		dbCon.rollback();
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		PreparedStatement s = dbCon.prepareStatement(sql);
		return s;
	}

	public Statement createStatement() throws SQLException {
		Statement s = dbCon.createStatement();
		return s;
	}

	public int executeUpdate(String s) throws SQLException {
		int count = stmt.executeUpdate(s);
		return count;
	}

	public ResultSet execSQL(StringBuffer sqlBuf) throws SQLException {
		Statement s = dbCon.createStatement();
		String sql = sqlBuf.toString();
		ResultSet rs = s.executeQuery(sql);
		return (rs == null) ? null : rs;
	}

	public String getDbDriver() {
		return this.dbDriver;
	}

	public void setDbDriver(String newValue) {
		this.dbDriver = newValue;
	}

	public String getDbURL() {
		return this.dbURL;
	}

	public void setDbURL(String newValue) {
		this.dbURL = newValue;
	}

	public ResultSet doQuery(StringBuffer qry) {

		ResultSet rs = null;

		try {
			connect();
			rs = execSQL(qry);

		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: " + e.getMessage());
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: " + e.getMessage());  
		}

		return rs;
	}

	/**
	 * method to find the highest value in a table so that you can 
	 * then increment it to have a new unused unique value
	 * @param table
	 * @param column
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public int getMaxValue(String table, String column) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		int maxValue = 0;
		ResultSet rs = null;
		StringBuffer qry = new StringBuffer(1024);
		qry.append("SELECT MAX(");
		qry.append(column);
		qry.append(") as maxValue FROM ");
		qry.append(table);

		connect();
		rs = doQuery(qry);
		while(rs.next()){
			maxValue = rs.getInt("maxValue");
		}
		return maxValue;
	}

}
