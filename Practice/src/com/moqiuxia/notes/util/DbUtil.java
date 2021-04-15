package com.moqiuxia.notes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 数据库工具类
 * @author Administrator
 *
 */
public class DbUtil {
	
	private String dbUrl = "jdbc:mysql://localhost:3306/local_note?serverTimezone=UTC";
	private String dbUserName = "root"; //用户名
	private String dbPassword = "123456";//密码
	private String jdbcName = "com.mysql.cj.jdbc.Driver"; //驱动连接
	
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}

	/**
	 * 关闭数据库连接
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception{
		if(con != null) {
			con.close();
		}
	}
	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功！");
			
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("数据库连接失败！");
		}
	}
}
