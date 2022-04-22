package com.moqiuxia.notes.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.moqiuxia.notes.dao.NoteDao;
import com.moqiuxia.notes.entity.Note;
import com.moqiuxia.notes.entity.User;
import com.moqiuxia.notes.util.DbUtil;

public class MyNoteInterFrame extends JInternalFrame {
	private JTable myTable;
	
	private User currentUser;
	private DbUtil dbUtil = new DbUtil();
	private NoteDao noteDao = new NoteDao();


	/**
	 * Create the frame.
	 */
	public MyNoteInterFrame(User currentUser) {
		setIconifiable(true);
		setClosable(true);
		this.currentUser = currentUser;
		setTitle(""+currentUser.getUserName()+",欢迎来到你的笔记页面");
		setBounds(100, 100, 450, 300);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(77)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(64)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(58, Short.MAX_VALUE))
		);
		
		myTable = new JTable();
		myTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u6807\u9898", "\u70B9\u8D5E\u6570", "\u6743\u9650"
			}
		));
		scrollPane.setViewportView(myTable);
		getContentPane().setLayout(groupLayout);

		this.fillMyTable(new Note());
	}
	
	/**
	 * 初始化我的笔记界面
	 * @param note
	 */
	private void fillMyTable(Note note) {
		DefaultTableModel dtm = (DefaultTableModel) myTable.getModel();
		//格式化表格
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
            System.out.println("获取成功！");
			ResultSet rs = noteDao.relevence(con,note);
			//进行遍历
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("noteTitle"));
				
				v.add(rs.getInt("noteLove"));
				v.add(rs.getString("noteLimit"));
				
//				v.add(lookButton);
				dtm.addRow(v);		
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	

       
}
