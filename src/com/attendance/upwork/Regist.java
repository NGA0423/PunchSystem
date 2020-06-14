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
		setTitle("注册");
		setBounds(200, 200, 500, 375);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		JLabel jLabel = new JLabel("账号/用户名：");
		jLabel.setBounds(10, 10, 200, 18);
		contentPane.add(jLabel);
		JTextField user_id = new JTextField();
		user_id.addFocusListener(new JTextFieldHintListener(user_id, "请输入用户名/账号"));// 设置输入提示
		user_id.setBounds(90, 10, 150, 18);// 设置组件位置
		contentPane.add(user_id);
		
		JLabel jLabelsex = new JLabel("性别：");
		jLabelsex.setBounds(50, 50, 200, 18);
		contentPane.add(jLabelsex);
		
		JComboBox<String> jComboBox = new JComboBox<>(new SexComboBox());
		jComboBox.setBounds(90, 50, 50, 18);
		contentPane.add(jComboBox);
		
		JLabel jLabel2 = new JLabel("密码：");
		jLabel2.setBounds(50, 100, 200, 18);
		contentPane.add(jLabel2);
		
		JPasswordField password = new JPasswordField();
		// password.addFocusListener(new JTextFieldHintListener(password, "请输入密码"));
		password.setEchoChar('*');
		password.setBounds(90, 100, 150, 18);
		contentPane.add(password);
		
		JLabel jLabel3 = new JLabel("确认密码：");
		jLabel3.setBounds(25, 150, 200, 18);
		contentPane.add(jLabel3);
		JPasswordField confirmpassword = new JPasswordField();
		confirmpassword.setEchoChar('*');
		confirmpassword.setBounds(90, 150, 150, 18);
		contentPane.add(confirmpassword);
		
		
		


		JButton regist = new JButton("注册");// 创建按钮对象
		regist.setBounds(120, 200, 100, 20);
		regist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (user_id.getText().length()==0|password.getText().length()==0|confirmpassword.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "用户名/账号或密码不能为空");
					return;
				}else {
					boolean renext = false;
					Statement statement = new ConnectionMysql().getCreateStatement();
					try {
						String sql=String.format("select * from user_info where user_name=%s;",user_id.getText());
						ResultSet rs = statement.executeQuery(sql);
						renext = rs.next();
						
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					if (equals(password.getText())==equals(confirmpassword.getText())) {
						if (renext) {
							JOptionPane.showMessageDialog(null, "用户名已存在");					
						}else {
							
							String sql=String.format("INSERT INTO user_info(user_name,user_pwd,user_sex) VALUES (%s,%s,'%s');",user_id.getText(),password.getText(),jComboBox.getSelectedItem());
							System.out.println(sql);
							try {
								statement.execute(sql);
								statement.close();
								JOptionPane.showMessageDialog(null, "注册成功");
								new Login();
								dispose();
							} catch (SQLException e1) {
								// TODO 自动生成的 catch 块
								e1.printStackTrace();
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "密码不一致");
					}
					
				}
			}
		});
		contentPane.add(regist);
		
	}
}
