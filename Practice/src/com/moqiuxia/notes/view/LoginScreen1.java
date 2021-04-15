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
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 30));
		
		JLabel label = new JLabel("\u8D26  \u53F7");
		label.setFont(new Font("����", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6  \u7801");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		JButton button = new JButton("\u767B  \u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
				//������ҳ�棬��ɵ�¼
				/**
				 * �û���������Ϊ�յĴ���
				 * �û������������Ĵ���
				 * ��¼�ɹ��Ĵ���
				 */
			}
		});
		
		JButton button_1 = new JButton("\u8FD4  \u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//����ע��ҳ��Ĵ���
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
	
	    //Ĭ�ϴ��ھ���
		this.setLocationRelativeTo(null);
	}

	
	
	/**
	 * ��¼�¼�����
	 * @param e
	 */
	private void loginActionPerformed(ActionEvent e) {
		//�ӽ����ȡ�û���������
		String userName = this.userNameTxt.getText();
		//����ֵ��һ��char������Ҫת��
		String password = new String(this.passwordTxt.getPassword());
		
		//�ж��û��������벻��Ϊ��
		if(StringUtil.isEmpty(userName)) {
			//��ʾ�û���Ϊ��
			JOptionPane.showMessageDialog(null, "�û�������Ϊ�գ�");
			return;
		}
		//�ж����벻��Ϊ��
		if(StringUtil.isEmpty(password)) {
			//��ʾ����Ϊ��
			JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
			return;
		}
		
		//�������ݿ�����
		//��װһ������������ѯ,ʵ����
		
		User user = new User(userName,password);
		User currentUser =null;
		//�������ݿ�����
		//1.����һ������,Ȼ���ȡ���ӣ���û�л��dbUtilʵ����Ҫʵ��������
		Connection con = null;
		try {
			con = dbUtil.getCon();
			
			//����һ��User����currentUser,���緵��null����ζ��������󣻼���ɹ��᷵���û�������Ϣ
			currentUser= userDao.login(con,user);
			
			//�ж��Ƿ����
			if(currentUser != null) {
				//����ԭ����,������ҳ��
				dispose();
				
				new MainFrame(currentUser).setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "�û������������");
			}
			
		} catch (Exception e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
		
		
	}
}
