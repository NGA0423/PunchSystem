package com.attendance.upwork;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.attendance.bean.QueryRecord;
import com.attendance.bean.RButton;
import com.attendance.jdbc.ConnectionMysql;

public class UpWork extends JFrame {
	/**
	 * �ϰ�򿨽���
	 */
	RButton rButton;
	private static final long serialVersionUID = 1L;

	public UpWork(String userName2) {
		super();
		setVisible(true);
		setTitle("�ϰ��");
		setBounds(500, 400, 500, 375);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		// JLabel displayTime = new JLabel();

		JLabel userName = new JLabel();

		JButton jButton = new JButton("��¼");

		if (userName2 == null) {
			userName.setBounds(10, 10, 100, 20);
			userName.setText("δ��¼");
			userName.setForeground(Color.RED);
			jButton.setVisible(true);

			jButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new Login();
					dispose();
				}
			});
		} else {
			jButton.setVisible(false);
			userName.setText("�ѵ�¼��" + userName2);
			userName.setBounds(10, 10, 100, 20);
			rButton = new RButton("��");
			rButton.setBounds(200, 100, 100, 100);
			rButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Calendar time = Calendar.getInstance();
					int hour = time.get(Calendar.HOUR_OF_DAY);
//					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
//					String format = df.format(System.currentTimeMillis());
//					Date date = new Date(); // �����ʱutil���µ�
//					SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // ����24ʱ
//					String Date = temp.format(date);
//					System.out.println(Date);
					Timestamp dete = new Timestamp(System.currentTimeMillis());
					
					Statement createStatement = new ConnectionMysql().getCreateStatement();
					String record_state = null;
//					String sql=String.format("INSERT INTO record_tbl(user_name,record_time,record_state) VALUES ('%s','%s','%s');",userName2,Date,record_state);
					String sql = "INSERT INTO record_tbl(user_name,record_time,record_state) VALUES (?,?,?);";
					PreparedStatement prepareStatement = new ConnectionMysql().prepareStatement(sql);
					if (hour > 9) {
						record_state = "�ٵ�";
						try {
							prepareStatement.setString(1, userName2);
							prepareStatement.setTimestamp(2, dete);
							prepareStatement.setString(3, record_state);
							prepareStatement.executeUpdate();
							prepareStatement.close();

							JOptionPane.showMessageDialog(UpWork.this, "�򿨳ɹ�����ٵ���");

						} catch (SQLException e1) {
							// TODO �Զ����ɵ� catch ��
							e1.printStackTrace();
						}
					} else {
						record_state = "";
						try {
							prepareStatement.setString(1, userName2);
							prepareStatement.setTimestamp(2, dete);
							prepareStatement.setString(3, record_state);
							prepareStatement.executeUpdate();
							prepareStatement.close();
							JOptionPane.showMessageDialog(null, "�򿨳ɹ�");
						} catch (SQLException e1) {
							// TODO �Զ����ɵ� catch ��
							e1.printStackTrace();
						}
					}
				}

			});
			contentPane.add(rButton);
			
			JButton query = new JButton("�򿨲�ѯ");
			query.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new QueryRecord(userName2);
					dispose();
					
				}
			});
			query.setBounds(300, 10, 100, 20);
			contentPane.add(query);

		}

		jButton.setBounds(120, 10, 100, 20);
		contentPane.add(jButton);
		contentPane.add(userName);
		
		

	}
}
