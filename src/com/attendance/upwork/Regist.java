package com.attendance.upwork;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import com.attendance.jdbc.ConnectionMysql;

public class Regist extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Regist() {
		super();
		setVisible(true);
		setTitle("ע��");
		setBounds(200, 200, 500, 375);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		JLabel jLabel = new JLabel("�˺�/�û�����");
		jLabel.setBounds(10, 10, 200, 18);
		contentPane.add(jLabel);
		JTextField user_id = new JTextField();
		user_id.addFocusListener(new JTextFieldHintListener(user_id, "�������û���/�˺�"));// ����������ʾ
		user_id.setBounds(90, 10, 150, 18);// �������λ��
		contentPane.add(user_id);
		
		JLabel jLabelsex = new JLabel("�Ա�");
		jLabelsex.setBounds(50, 50, 200, 18);
		contentPane.add(jLabelsex);
		
		JComboBox<String> jComboBox = new JComboBox<>(new SexComboBox());
		jComboBox.setBounds(90, 50, 50, 18);
		contentPane.add(jComboBox);
		
		JLabel jLabel2 = new JLabel("���룺");
		jLabel2.setBounds(50, 100, 200, 18);
		contentPane.add(jLabel2);
		
		JPasswordField password = new JPasswordField();
		// password.addFocusListener(new JTextFieldHintListener(password, "����������"));
		password.setEchoChar('*');
		password.setBounds(90, 100, 150, 18);
		contentPane.add(password);
		
		JLabel jLabel3 = new JLabel("ȷ�����룺");
		jLabel3.setBounds(25, 150, 200, 18);
		contentPane.add(jLabel3);
		JPasswordField confirmpassword = new JPasswordField();
		confirmpassword.setEchoChar('*');
		confirmpassword.setBounds(90, 150, 150, 18);
		contentPane.add(confirmpassword);
		
		
		


		JButton regist = new JButton("ע��");// ������ť����
		regist.setBounds(120, 200, 100, 20);
		regist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (user_id.getText().length()==0|password.getText().length()==0|confirmpassword.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "�û���/�˺Ż����벻��Ϊ��");
					return;
				}else {
					boolean renext = false;
					Statement statement = new ConnectionMysql().getCreateStatement();
					try {
						String sql=String.format("select * from user_info where user_name=%s;",user_id.getText());
						ResultSet rs = statement.executeQuery(sql);
						renext = rs.next();
						
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
					if (equals(password.getText())==equals(confirmpassword.getText())) {
						if (renext) {
							JOptionPane.showMessageDialog(null, "�û����Ѵ���");					
						}else {
							
							String sql=String.format("INSERT INTO user_info(user_name,user_pwd,user_sex) VALUES (%s,%s,'%s');",user_id.getText(),password.getText(),jComboBox.getSelectedItem());
							System.out.println(sql);
							try {
								statement.execute(sql);
								statement.close();
								JOptionPane.showMessageDialog(null, "ע��ɹ�");
								new Login();
								dispose();
							} catch (SQLException e1) {
								// TODO �Զ����ɵ� catch ��
								e1.printStackTrace();
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "���벻һ��");
					}
					
				}
			}
		});
		contentPane.add(regist);
		
	}
}
