package com.attendance.bean;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import com.attendance.jdbc.ConnectionMysql;

public class QueryRecord extends JFrame{
	private boolean remove=false;
	private JTable information;
	
	public QueryRecord(String uname) {
		super();
		setTitle("打卡查询");
		setVisible(true);//窗体可见
		setBounds(500, 400, 500, 375);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		JButton forenoon = new JButton("上午");
		forenoon.setBounds(150, 10, 80, 20);
		Vector<String> vector = new Vector<>();
		vector.add("序号");
		vector.add("用户名");
		vector.add("打卡时间");
		vector.add("状态");
		
		Vector<Vector<String>> vectordata = new Vector<>();//创建数据保存对象

		forenoon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String sql = String.format("select * from record_tbl where user_name=%s;",uname);
				Statement createStatement = new ConnectionMysql().getCreateStatement();
				
				try {
					ResultSet rs = createStatement.executeQuery(sql);				
					String state = null;
					String time = null;
					String username = null;
					while(rs.next()) {
						//获取数据并保存
						Vector<String> rowV = new Vector<>();
						String id = rs.getString("record_id");
						username = rs.getString("user_name");
						Timestamp timestamp = rs.getTimestamp(3);
						time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
						state = rs.getString("record_state");
						//将数据添加到表格
						rowV.add(id);
						rowV.add(username);
						rowV.add(time);
						rowV.add(state);
						vectordata.add(rowV);
						information = new JTable(vectordata,vector);
						
						information.setPreferredScrollableViewportSize(new Dimension(500, 400));
						JScrollPane scrollPane = new JScrollPane(information);//创建JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看
						scrollPane.setBounds(45, 50, 400, 400);
						information.updateUI();
						contentPane.add(scrollPane);
						remove=true;
					}
					
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
			
		});
		
		contentPane.add(forenoon);
		
		JButton afternoon = new JButton("下午");
		afternoon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove=true;
				String sql = String.format("select * from gooffwork where user_name=%s;",uname);
				Statement createStatement = new ConnectionMysql().getCreateStatement();
				
				try {
					ResultSet rs = createStatement.executeQuery(sql);				
					String state = null;
					String time = null;
					String username = null;
					while(rs.next()) {
						//获取数据并保存
						Vector<String> rowV = new Vector<>();
						String id = rs.getString("user_id");
						username = rs.getString("user_name");
						Timestamp timestamp = rs.getTimestamp(3);
						time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
						state = rs.getString("gooffwork_state");
						//将数据添加到表格
						rowV.add(id);
						rowV.add(username);
						rowV.add(time);
						rowV.add(state);
						vectordata.add(rowV);
						information = new JTable(vectordata,vector);
						
						information.setPreferredScrollableViewportSize(new Dimension(500, 400));
						JScrollPane scrollPane = new JScrollPane(information);//创建JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看
						scrollPane.setBounds(45, 50, 400, 400);

						contentPane.add(scrollPane);
					}
					
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		afternoon.setBounds(240, 10, 80, 20);
		contentPane.add(afternoon);
		
		if (remove) {
			information.removeAll();
		}
	}	
}
