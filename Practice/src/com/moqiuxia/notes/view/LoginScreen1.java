package com.moqiuxia.notes.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.moqiuxia.notes.dao.UserDao;
import com.moqiuxia.notes.entity.User;
import com.moqiuxia.notes.util.DbUtil;
import com.moqiuxia.notes.util.StringUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginScreen1 extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen1 frame = new LoginScreen1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginScreen1() {
		setTitle("\u767B\u5F55\u9875\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u672C\u5730\u7B14\u8BB0\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		
		JLabel label = new JLabel("\u8D26  \u53F7");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6  \u7801");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		JButton button = new JButton("\u767B  \u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
				//进入主页面，完成登录
				/**
				 * 用户名或密码为空的处理
				 * 用户名或密码错误的处理
				 * 登录成功的处理
				 */
			}
		});
		
		JButton button_1 = new JButton("\u8FD4  \u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//返回注册页面的处理
				dispose();
				new RegisterScreen().setVisible(true);
			}
		});
		
		passwordTxt = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(121)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(120)
								.addComponent(button)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button_1))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(111)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(label)
									.addComponent(lblNewLabel_1))
								.addGap(26)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(passwordTxt)
									.addComponent(userNameTxt, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))))
					.addContainerGap(106, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addComponent(lblNewLabel)
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	
	    //默认窗口居中
		this.setLocationRelativeTo(null);
	}

	
	
	/**
	 * 登录事件处理
	 * @param e
	 */
	private void loginActionPerformed(ActionEvent e) {
		//从界面获取用户名和密码
		String userName = this.userNameTxt.getText();
		//返回值是一个char，所以要转化
		String password = new String(this.passwordTxt.getPassword());
		
		//判断用户名和密码不能为空
		if(StringUtil.isEmpty(userName)) {
			//提示用户名为空
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		//判断密码不能为空
		if(StringUtil.isEmpty(password)) {
			//提示密码为空
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		
		//进行数据库连接
		//封装一个对象用来查询,实例化
		
		User user = new User(userName,password);
		User currentUser =null;
		//进行数据库连接
		//1.定义一个连接,然后获取连接，还没有获得dbUtil实例，要实例出对象
		Connection con = null;
		try {
			con = dbUtil.getCon();
			
			//返回一个User放在currentUser,假如返回null，意味着密码错误；假如成功会返回用户所有信息
			currentUser= userDao.login(con,user);
			
			//判断是否存在
			if(currentUser != null) {
				//销毁原窗口,出现主页面
				dispose();
				
				new MainFrame(currentUser).setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误！");
			}
			
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		
		
	}
}
