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
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RegisterScreen extends JFrame {

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
					RegisterScreen frame = new RegisterScreen();
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
	public RegisterScreen() {
		setResizable(false);
		setTitle("\u6CE8\u518C\u9875\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u6765\u5230\u672C\u5730\u7B14\u8BB0\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 30));
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6  \u7801");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 20));
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("\u5DF2\u6709\u8D26\u53F7\uFF0C\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				//ת���¼ҳ��
				
				dispose();
				new LoginScreen1().setVisible(true);
				
				
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 15));
		
		JButton btnNewButton_1 = new JButton("\u6CE8  \u518C");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				registerActionPerformed(a);
				
				//ʵ��ע�Ṧ�ܣ�������ҳ��
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 15));
		
		passwordTxt = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(91)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel_2))
									.addGap(33)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(passwordTxt)
										.addComponent(userNameTxt, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton)
									.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
									.addComponent(btnNewButton_1)
									.addGap(25))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(71, Short.MAX_VALUE)
							.addComponent(lblNewLabel)))
					.addGap(63))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(55)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(19))
		);
		contentPane.setLayout(gl_contentPane);
	 
		//Ĭ�ϴ��ھ���
		this.setLocationRelativeTo(null);
	}

	
	/**
	 * ʵ��ע���¼�
	 * @param a
	 */
	private void registerActionPerformed(ActionEvent a) {
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
		//�ж��û��������ظ�
		
		
		//ʵ����User��
		/**
		 * user ��ȡע���û����ı�
		 * currentUser �����ݿ��û�У��
		 */
		User user = new User(userName,password);
		User currentUser = null;
		Connection con = null;
		
		try {
			con = dbUtil.getCon();
			currentUser= userDao.check(con,user);
			//�����ݿ��ҵ��û���
			if(currentUser != null) {
				JOptionPane.showMessageDialog(null, "�û��Ѵ��ڣ�");
				
			}else {
			
				int n = userDao.add(con, user);
			    if(n == 1) {
				JOptionPane.showMessageDialog(null, "ע��ɹ���");
				dispose();
				new MainFrame(user).setVisible(true);
			
			}else {
				JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�");
			}
			}    
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�����");
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		
		
	
	}

}
