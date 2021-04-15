package com.moqiuxia.notes.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ManagerLoginS extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerLoginS frame = new ManagerLoginS();
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
	public ManagerLoginS() {
		setIconifiable(true);
		setClosable(true);
		setTitle("\u7BA1\u7406\u5458\u767B\u5F55\u9875\u9762");
		setBounds(100, 100, 450, 300);
		
		JLabel label = new JLabel("\u672C\u5730\u7B14\u8BB0\u7CFB\u7EDF");
		label.setFont(new Font("ËÎÌו", Font.PLAIN, 30));
		
		JLabel label_1 = new JLabel("\u8D26  \u53F7");
		label_1.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		
		JLabel label_2 = new JLabel("\u5BC6  \u7801");
		label_2.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton button = new JButton("\u767B  \u5F55");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(154, Short.MAX_VALUE)
					.addComponent(label)
					.addGap(100))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(146)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(label_2)
							.addGap(18)
							.addComponent(textField_1))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(label_1)
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(85, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addComponent(label)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1)
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addComponent(button)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

}
