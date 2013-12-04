package com.omar.hubino.util;

/** package declaration */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * The Class DBUtil.
 * 
 * @author Vijayaraja Gnansambandan
 * @version 1.0.0 - The Class DBUtil Created
 * @version 1.0.0 - synchronized added in get connection.
 */
public class DBUtil {

	/** The logger. */
	private final static Logger LOGGER = Logger.getLogger(DBUtil.class);

	/** The instance. */
	private static DBUtil instance = null;

	/** The connection. */
	private Connection connection = null;

	/**
	 * Gets the single instance of DBUtil.
	 * 
	 * @return single instance of DBUtil
	 */
	public synchronized static DBUtil getInstance() {
		if (instance == null) {
			instance = new DBUtil();
		}
		return instance;
	}

	/**
	 * Gets the connection.
	 * 
	 * @return the connection
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public Connection getConnection() throws Exception {
		LOGGER.debug("Connection Created ");
//		return getConnectionFromDataSource();
		return getConnectionFromLocal();
	}

	/**
	 * Close connection.
	 * 
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	/**
	 * Gets the connection from local.
	 * 
	 * @return the connection from local
	 * 
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws SQLException
	 *             the SQL exception
	 * @throws Exception
	 *             the exception
	 */
	private Connection getConnectionFromLocal() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/alpd", "root", "root");
		return connection;
	}

	/**
	 * Gets the connection from data source.
	 * 
	 * @return the connection from data source
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unused")
	private Connection getConnectionFromDataSource() throws Exception {

		Context ctx = new InitialContext();
		if (ctx == null) {
			throw new Exception(" No Context");
		}
		DataSource dataSource = (DataSource) ctx
				.lookup("java:comp/env/ibss/jdbc/Datasource");

		if (dataSource != null) {
			connection = dataSource.getConnection();
		}
		return connection;
	}

	/**
	 * Gets the row count.
	 * 
	 * @param query
	 *            the query
	 * @param connec
	 *            the connec
	 * 
	 * @return the row count
	 * 
	 * @throws SQLException
	 *             the SQL exception
	 */
	public int getRowCount(String query, Connection connec) throws SQLException {
		ResultSet rst = null;
		Statement stmt = null;
		int rowCount = 0;
		Connection conn = connec;
		stmt = conn.createStatement();
		rst = stmt.executeQuery(query);
		if (rst.next()) {
			rowCount = rst.getInt(1);
		}
		rst.close();
		stmt.close();
		return rowCount;
	}

	/**
	 * Gets the row count.
	 * 
	 * @param query
	 *            the query
	 * @param parameters
	 *            the parameters
	 * @param connec
	 *            the connec
	 * 
	 * @return the row count
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public int getRowCount(String query, Object[] parameters, Connection connec)
			throws Exception {
		ResultSet rst = null;
		PreparedStatement pstmt = null;
		Object[] para = parameters;
		int rowCount = 0;
		Connection conn = connec;
		pstmt = conn.prepareStatement(query);
		for (int i = 0; i < para.length; i++) {
			pstmt.setString(i + 1, String.valueOf(para[i]));
		}
		rst = pstmt.executeQuery();
		if (rst.next()) {
			rowCount = rst.getInt(1);
		}
		rst.close();
		pstmt.close();
		return rowCount;
	}

	/**
	 * Execute update.
	 * 
	 * @param query
	 *            the query
	 * @param parameters
	 *            the parameters
	 * @param connec
	 *            the connec
	 * 
	 * @return the int
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public int executeUpdate(String query, Object[] parameters,
			Connection connec) throws Exception {
		int updateRows = 0;
		PreparedStatement pstmt = null;
		Object[] para = parameters;
		Connection conn = connec;
		pstmt = conn.prepareStatement(query);
		for (int i = 0; i < para.length; i++) {
			pstmt.setString(i + 1, String.valueOf(para[i]));
		}
		updateRows = pstmt.executeUpdate();
		pstmt.close();
		return updateRows;
	}

	/**
	 * Execute update.
	 * 
	 * @param query
	 *            the query
	 * @param parameters
	 *            the parameters
	 * @param repeatQuery
	 *            the repeat query
	 * @param connec
	 *            the connec
	 * 
	 * @return the int
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public int executeUpdate(String query, Object[] parameters,
			Connection connec, boolean repeatQuery) throws Exception {
		int updateRows = 0;
		PreparedStatement pstmt = null;
		Object[] para = parameters;
		Connection conn = connec;
		pstmt = conn.prepareStatement(query);
		for (int i = 0; i < para.length; i++) {
			if (repeatQuery) {
				pstmt.setString(1, String.valueOf(para[i]));
				updateRows = pstmt.executeUpdate();
			}
		}
		pstmt.close();
		return updateRows;
	}

	/**
	 * Gets the value.
	 * 
	 * @param query
	 *            the query
	 * @param parameters
	 *            the parameters
	 * @param connec
	 *            the connec
	 * 
	 * @return the value
	 * 
	 * @throws SQLException
	 *             the SQL exception
	 */
	public String getValue(String query, Object[] parameters, Connection connec)
			throws SQLException {
		ResultSet rst = null;
		PreparedStatement pstmt = null;
		Object[] para = parameters;
		String returnValue = null;
		Connection conn = connec;
		pstmt = conn.prepareStatement(query);
		for (int i = 0; i < para.length; i++) {
			pstmt.setString(i + 1, String.valueOf(para[i]));
		}
		rst = pstmt.executeQuery();
		if (rst.next()) {
			returnValue = String.valueOf(rst.getString(1));
		}
		rst.close();
		pstmt.close();
		return returnValue;
	}
	
	public int getValueInt(String query, Connection connec)
	throws SQLException {
		
		ResultSet rst = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		pstmt = connec.prepareStatement(query);
		rst = pstmt.executeQuery();
		if (rst.next()) {
			returnValue = rst.getInt(1);
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (rst != null) {
			rst.close();
		}
		return returnValue;
	}

	/**
	 * Execute update.
	 * 
	 * @param query
	 *            the query
	 * @param connec
	 *            the connec
	 * 
	 * @return the int
	 * 
	 * @throws SQLException
	 *             the SQL exception
	 */
	public int executeUpdate(String query, Connection connec)
			throws SQLException {
		Statement st = null;
		Connection conn = connec;
		st = conn.createStatement();
		int updatedRows = st.executeUpdate(query.toString());
		st.close();
		return updatedRows;
	}

}
