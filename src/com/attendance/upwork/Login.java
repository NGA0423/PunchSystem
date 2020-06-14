package com.attendance.upwork;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.attendance.jdbc.ConnectionMysql;

public class Login extends JFrame {
//	public static void main(String[] args) {
//		Login frame = new Login();
//		frame.setVisible(true);
//	}

	public Login() {
		super();
		setVisible(true);
		setTitle("登录");
		setBounds(500, 500, 500, 375);
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

		JLabel jLabel2 = new JLabel("密码：");
		jLabel2.setBounds(50, 50, 200, 18);
		contentPane.add(jLabel2);

		JPasswordField password = new JPasswordField();
		// password.addFocusListener(new JTextFieldHintListener(password, "请输入密码"));
		password.setEchoChar('*');
		password.setBounds(90, 50, 150, 18);
		contentPane.add(password);

		JButton delu = new JButton("登录");// 创建按钮对象

		delu.addActionListener(new ActionListener() {
			boolean fail = true;

			@Override
			public void actionPerformed(ActionEvent e) {

				if (user_id.getText().length() == 0 | "".equals(user_id.getText())) {
					JOptionPane.showMessageDialog(null, "请输入用户名/账号");
				} else {
					if (password.getText().length() == 0 | "".equals(password.getText())) {
						JOptionPane.showMessageDialog(null, "请输入密码");
					} else {
						String sql = String.format("select * from user_info where user_name=%s and user_pwd=%s;",
								user_id.getText(), password.getText());
						Statement createStatement = new ConnectionMysql().getCreateStatement();

						boolean res = false;
						ResultSet rs = null;
						try {
							rs = createStatement.executeQuery(sql);
							res = rs.next();

						} catch (SQLException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
						String userName = null;

						if (res) {
							try {
								userName = rs.getString("user_name");
							} catch (SQLException e1) {
								// TODO 自动生成的 catch 块
								e1.printStackTrace();
							}
							fail = false;
							Calendar time = Calendar.getInstance();
							int hour = time.get(Calendar.HOUR_OF_DAY);
							if (hour>12) {
								new GoOffWork(userName);
							}else {
								new UpWork(userName);
							}
							
							dispose();
							JOptionPane.showMessageDialog(null, "登录成功");
							
						} else {

//							JOptionPane.showInternalMessageDialog(this, "用户名或密码错误");

						}

					}
				}
				if (fail) {

					JOptionPane.showMessageDialog(null, "用户名或密码错误");
				}
			}
		});
		delu.setBounds(100, 100, 100, 20);
		contentPane.add(delu);
		
		JButton regist = new JButton("注册");
		regist.setBounds(250, 100, 100, 20);
		regist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Regist();
				dispose();
			}
		});
		contentPane.add(regist);
	}
	
	
}
