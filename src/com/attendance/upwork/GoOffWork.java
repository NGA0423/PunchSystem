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
	 * �°�򿨽���
	 * @param uname
	 */
	public GoOffWork(String uname) {
		super();
		setTitle("�°��");
		setBounds(500, 300, 500, 375);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		JLabel user_id = new JLabel();
		JButton ligin_button = new JButton("��¼");
		ligin_button.setBounds(100, 10, 80, 20);
		
		user_id.setBounds(10, 10, 100, 20);
		if (uname==null) {
			user_id.setText("δ��¼,���¼��");
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
			user_id.setText("�ѵ�¼��"+uname);
			ligin_button.setVisible(false);
			RButton rButton = new RButton("��");
			rButton.setBounds(200, 100, 100, 100);
			rButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//��ȡ��ǰϵͳʱ��
					Calendar time = Calendar.getInstance();
					int hour = time.get(Calendar.HOUR_OF_DAY);
					Timestamp dete = new Timestamp(System.currentTimeMillis());
					//�������ݿ����Ӷ���
					Statement createStatement = new ConnectionMysql().getCreateStatement();//����createStatement����
					String sql = "INSERT INTO gooffwork(user_name,gooffwork_time,gooffwork_state) VALUES (?,?,?);";
					PreparedStatement prepareStatement = new ConnectionMysql().prepareStatement(sql);//����prepareStatement����
					
					String record_state =null;
					//�ж��°�ʱ��
					if (hour>18) {
						record_state = "";
						//�����°�
						try {
							prepareStatement.setString(1, uname);
							prepareStatement.setTimestamp(2, dete);
							prepareStatement.setString(3,record_state);
							prepareStatement.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "�򿨳ɹ�");//��ʾ
							prepareStatement.close();//�ر�����
						} catch (SQLException e1) {
							// TODO �Զ����ɵ� catch ��
							e1.printStackTrace();
						}
					}else {
						//����
						record_state = "����";
						try {
							//�ϴ�����
							prepareStatement.setString(1, uname);
							prepareStatement.setTimestamp(2, dete);
							prepareStatement.setString(3, record_state);
							prepareStatement.executeUpdate();
							
							//��ʾ
							JOptionPane.showMessageDialog(null, "�򿨳ɹ����������ˣ�");
							prepareStatement.close();//�ر�����
							
						} catch (SQLException e1) {
							// TODO �Զ����ɵ� catch ��
							e1.printStackTrace();
						}
					}
				}
			});
			JButton query = new JButton("�򿨲�ѯ");//�����򿨲�ѯ��ť����
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
