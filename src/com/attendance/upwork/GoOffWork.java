package com.attendance.upwork;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.attendance.bean.QueryRecord;
import com.attendance.bean.RButton;
import com.attendance.jdbc.ConnectionMysql;

public class GoOffWork extends JFrame{
	/**
	 * 下班打卡界面
	 * @param uname
	 */
	public GoOffWork(String uname) {
		super();
		setTitle("下班打卡");
		setBounds(500, 300, 500, 375);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		JLabel user_id = new JLabel();
		JButton ligin_button = new JButton("登录");
		ligin_button.setBounds(100, 10, 80, 20);
		
		user_id.setBounds(10, 10, 100, 20);
		if (uname==null) {
			user_id.setText("未登录,请登录！");
			user_id.setForeground(Color.RED);
			ligin_button.setVisible(true);
			ligin_button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new Login();
					dispose();
					
				}
			});
			
		}else {
			user_id.setText("已登录："+uname);
			ligin_button.setVisible(false);
			RButton rButton = new RButton("打卡");
			rButton.setBounds(200, 100, 100, 100);
			rButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//获取当前系统时间
					Calendar time = Calendar.getInstance();
					int hour = time.get(Calendar.HOUR_OF_DAY);
					Timestamp dete = new Timestamp(System.currentTimeMillis());
					//创建数据库连接对象
					Statement createStatement = new ConnectionMysql().getCreateStatement();//创建createStatement对象
					String sql = "INSERT INTO gooffwork(user_name,gooffwork_time,gooffwork_state) VALUES (?,?,?);";
					PreparedStatement prepareStatement = new ConnectionMysql().prepareStatement(sql);//创建prepareStatement对象
					
					String record_state =null;
					//判断下班时间
					if (hour>18) {
						record_state = "";
						//正常下班
						try {
							prepareStatement.setString(1, uname);
							prepareStatement.setTimestamp(2, dete);
							prepareStatement.setString(3,record_state);
							prepareStatement.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "打卡成功");//提示
							prepareStatement.close();//关闭连接
						} catch (SQLException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
					}else {
						//早退
						record_state = "早退";
						try {
							//上传数据
							prepareStatement.setString(1, uname);
							prepareStatement.setTimestamp(2, dete);
							prepareStatement.setString(3, record_state);
							prepareStatement.executeUpdate();
							
							//提示
							JOptionPane.showMessageDialog(null, "打卡成功，你早退了！");
							prepareStatement.close();//关闭连接
							
						} catch (SQLException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
					}
				}
			});
			JButton query = new JButton("打卡查询");//创建打卡查询按钮对象
			query.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new QueryRecord(uname);
					dispose();
					
				}
			});
			query.setBounds(300, 10, 100, 20);
			contentPane.add(query);
			contentPane.add(rButton);
		}
		contentPane.add(ligin_button);
		contentPane.add(user_id);
		
		
	}
}
