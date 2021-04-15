package com.moqiuxia.notes.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.moqiuxia.notes.dao.NoteDao;
import com.moqiuxia.notes.dao.UserDao;
import com.moqiuxia.notes.entity.Note;
import com.moqiuxia.notes.entity.User;
import com.moqiuxia.notes.util.DbUtil;
import com.moqiuxia.notes.util.MyButtonRender;

import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JScrollBar;
import javax.swing.JLabel;

public class MainFrame extends JFrame {
	private JDesktopPane table = null;

	private User currentUser;
	private JTable noteTable;

	private DbUtil dbUtil = new DbUtil();
	private NoteDao noteDao = new NoteDao();
	private JTextField authorSearchTxt;
	private JTextField titleSearchTxt;
	  UserDao userDao = new UserDao();
	  JDesktopPane table_1 = new JDesktopPane();



	/**
	 * Create the frame.
	 */
	
	public MainFrame(User currentUser) {
		this.currentUser = currentUser;
		setTitle("欢迎您，"+currentUser.getUserName());
//		setTitle("\u4E3B  \u9875");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u6211\u7684");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItem = new JMenuItem("\u65B0\u5EFA\u7B14\u8BB0");
		mnNewMenu.add(menuItem);
		
		/**
		 * 实现查看我的笔记功能
		 */
		JMenuItem myNoteClick = new JMenuItem("\u6211\u7684\u7B14\u8BB0");
		myNoteClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyNoteInterFrame myNoteInterFrame = new MyNoteInterFrame(currentUser);
				myNoteInterFrame.setVisible(true);
				table_1.add(myNoteInterFrame);
			}
		});
		mnNewMenu.add(myNoteClick);
		
		JMenuItem menuItem_2 = new JMenuItem("\u6211\u7684\u4FE1\u606F");
		mnNewMenu.add(menuItem_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u7BA1\u7406\u5458\u5165\u53E3");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerLoginS magLogin = new ManagerLoginS();
				magLogin.setVisible(true);
				table.add(magLogin);
				
			}
		});
		
		//默认屏幕最大化
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JMenuItem menuItem_3 = new JMenuItem("\u9000\u51FA\u767B\u5F55");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//退出登录
				int result = JOptionPane.showConfirmDialog(null, "是否退出登录？");
				if(result == 0) {
					dispose();
				}
			}
		});
		menuBar.add(menuItem_3);
		
		menuBar.add(mntmNewMenuItem);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u4F5C \u8005\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 25));
		
		JLabel lblNewLabel = new JLabel("\u7B14\u8BB0\u6807\u9898\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 25));
		
		authorSearchTxt = new JTextField();
		authorSearchTxt.setColumns(10);
		
		titleSearchTxt = new JTextField();
		titleSearchTxt.setColumns(10);
		
		/**
		 * 按作者名字查找笔记按钮
		 */
		JButton authorButton = new JButton("\u67E5 \u8BE2");
		authorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				authorSearchActionPerformed(evt);
			}
		});
		
		/**
		 * 按标题查找笔记按钮
		 */
		JButton titleButton = new JButton("\u67E5 \u8BE2");
		titleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				titleSearchActionPerformed(t);
			}
		});
		
		/**
		 * 显示所有笔记按钮
		 */
		JButton showAllNote = new JButton("\u663E\u793A\u6240\u6709\u7B14\u8BB0");
		showAllNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				showAllNoteActionPerformed(a);
				
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(label)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(titleSearchTxt)
								.addComponent(authorSearchTxt, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(titleButton)
								.addComponent(authorButton)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE)
								.addGap(34)
								.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 589, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(221)
								.addComponent(showAllNote))))
					.addContainerGap(146, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(104)
							.addComponent(table_1))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(authorSearchTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(authorButton)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(titleSearchTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(titleButton))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE)))
					.addGap(38)
					.addComponent(showAllNote)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		
		noteTable = new JTable();
		noteTable.setEnabled(false);
		noteTable.setBackground(Color.WHITE);
		noteTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u6807\u9898", "\u4F5C\u8005", "\u70B9\u8D5E\u6570", "\u67E5\u770B\u8BE6\u60C5"
			}
		));
		this.fillTable(new Note());
	
		scrollPane.setViewportView(noteTable);
		getContentPane().setLayout(groupLayout);

}

	
	
	
	

    private void titleSearchActionPerformed(ActionEvent t) {
		String title = this.titleSearchTxt.getText();
		Note note = new Note();
		note.setNoteTitle(title);
		this.fillTable(note);
		
	}






	/**
     * 显示所有笔记事件
     * @param a
     */
	private void showAllNoteActionPerformed(ActionEvent a) {
		this.fillTable(new Note());
		
	}







	/**
	 * 按作者查找笔记事件
	 * @param evt
	 */
   private void authorSearchActionPerformed(ActionEvent evt) {
	   //获取用户输入文本
	   String author = this.authorSearchTxt.getText();
	   User user = new User();
	   user.setUserName(author);
	   this.fillTable(user);
	   JOptionPane.showMessageDialog(null, "这是查询的所有结果！");
	 
	  
	   
	   
		
	}

/**
    * 初始化表格
    * @param note
    */
	private void fillTable(Note note) {
		DefaultTableModel dtm = (DefaultTableModel) noteTable.getModel();
		//格式化表格
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
           
			ResultSet rs = noteDao.relevence(con,note);
			//进行遍历
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("noteTitle"));
				v.add(rs.getString("userName"));
				v.add(rs.getInt("noteLove"));
				
				
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
	
	private void fillTable(User user) {
		DefaultTableModel dtm = (DefaultTableModel) noteTable.getModel();
		//格式化表格
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
           
			ResultSet rs = userDao.author(con, user);
			//进行遍历
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("noteTitle"));
				v.add(rs.getString("userName"));
				v.add(rs.getInt("noteLove"));
			
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
