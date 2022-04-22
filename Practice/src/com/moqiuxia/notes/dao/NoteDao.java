package com.moqiuxia.notes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.moqiuxia.notes.entity.Note;
import com.moqiuxia.notes.util.StringUtil;

/**
 * 笔记Dao类，数据访问对象
 * @author Administrator
 *
 */
public class NoteDao {
	public int add(Connection con,Note note) throws SQLException {
		String sql ="insert into note value(null,?,?,0,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, note.getNoteTitle());
		pstmt.setString(2, note.getNotecont());
		pstmt.setInt(3, note.getNoteAuthor());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 查询笔记集合
	 * @param con
	 * @param note
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con,Note note)throws Exception{
		//用拼接方式
		StringBuffer sb = new StringBuffer("select * from note");
		if(StringUtil.NotEmpty(note.getNoteTitle())) {
			sb.append("and note like '%"+note.getNoteTitle()+ "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		
//		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	
	
	public ResultSet relevence(Connection con,Note note)throws Exception{
		StringBuffer sb = new StringBuffer("select * from note,t_user where t_user.id = note.noteAuthor and noteLimit ='public' "
				+ "and noteTitle like '%"+note.getNoteTitle()+ "%'");
//		if(StringUtil.NotEmpty(note.getNoteTitle())) {
//			sb.append("and noteTitle like '%"+note.getNoteTitle()+ "%'");
//		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		
//		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	
	
	
}
