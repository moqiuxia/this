package com.moqiuxia.notes.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.moqiuxia.notes.entity.Note;
import com.moqiuxia.notes.entity.User;
import com.moqiuxia.notes.util.StringUtil;


/**
 * �û�Dao��
 * @author Administrator
 *
 */
public class UserDao {
	public  User login(Connection con,User user)throws Exception{
		User resultUser = null;
		String sql = "select * from t_user where userName = ? and password =?";
		
		/**
		 *rs:����� 
		 */
		PreparedStatement pstmt = con.prepareStatement(sql);
		//��ȡִ��sql�Ķ��󣬼�pstmt������ִ��sql���ķ���
		
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery(); //����֮ǰ����sql����
		//ִ��sql��select��䣬����һ�������rs
		if(rs.next()){
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));//���洫��������
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	
	}      //����user
	
	
	/**
	 * ע��ʱ��������Ƿ����ظ��û����������Ż�
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public  User check(Connection con,User user)throws Exception{
		User resultUser = null;
		String sql = "select * from t_user where userName = ? ";
		
		/**
		 *rs:����� 
		 */
		PreparedStatement pstmt = con.prepareStatement(sql);
		//��ȡִ��sql�Ķ��󣬼�pstmt������ִ��sql���ķ���
		
		pstmt.setString(1, user.getUserName());
		
		ResultSet rs = pstmt.executeQuery(); //����֮ǰ����sql����
		//ִ��sql��select��䣬����һ�������rs
		if(rs.next()){
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));//���洫��������
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	
	}      //����user


	/**
	 * �û����ӣ���ע��
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,User user)throws Exception {
		String sql = "insert into t_user values(null,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		//�����ʺŵ�ֵ
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		//ִ��һ��
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
