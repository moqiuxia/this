package com.moqiuxia.notes.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.moqiuxia.notes.entity.Note;
import com.moqiuxia.notes.entity.User;
import com.moqiuxia.notes.util.StringUtil;


/**
 * 用户Dao类
 * @author Administrator
 *
 */
public class UserDao {
	public  User login(Connection con,User user)throws Exception{
		User resultUser = null;
		String sql = "select * from t_user where userName = ? and password =?";
		
		/**
		 *rs:结果集 
		 */
		PreparedStatement pstmt = con.prepareStatement(sql);
		//获取执行sql的对象，即pstmt对象有执行sql语句的方法
		
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery(); //这里之前多了sql参数
		//执行sql中select语句，返回一个结果集rs
		if(rs.next()){
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));//界面传来的数据
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	
	}      //返回user
	
	
	/**
	 * 注册时用来检测是否有重复用户名，可以优化
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public  User check(Connection con,User user)throws Exception{
		User resultUser = null;
		String sql = "select * from t_user where userName = ? ";
		
		/**
		 *rs:结果集 
		 */
		PreparedStatement pstmt = con.prepareStatement(sql);
		//获取执行sql的对象，即pstmt对象有执行sql语句的方法
		
		pstmt.setString(1, user.getUserName());
		
		ResultSet rs = pstmt.executeQuery(); //这里之前多了sql参数
		//执行sql中select语句，返回一个结果集rs
		if(rs.next()){
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));//界面传来的数据
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	
	}      //返回user


	/**
	 * 用户增加，即注册
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,User user)throws Exception {
		String sql = "insert into t_user values(null,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		//设置问号的值
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		//执行一下
		return pstmt.executeUpdate();	
	}
	
	
	public ResultSet author(Connection con,User user)throws Exception{
		StringBuffer sb = new StringBuffer("select * from note,t_user where t_user.id = note.noteAuthor ");
		if(StringUtil.NotEmpty(user.getUserName())) {
			sb.append("and userName ='"+ user.getUserName()+"'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		
//		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
}
