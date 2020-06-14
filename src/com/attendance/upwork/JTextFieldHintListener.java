package com.attendance.upwork;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class JTextFieldHintListener implements FocusListener{
	private String hintText;
	private JTextField textField;
	public JTextFieldHintListener(JTextField jTextField,String hintText) {
		this.textField =jTextField;
		this.hintText =hintText;
		jTextField.setText(hintText);
		jTextField.setForeground(Color.gray);
	}
	
	public void focusGained(FocusEvent e) {
		//��ȡ����ʱ�������ʾ����
		String temp = textField.getText();
		if (temp.equals(hintText)) {
			textField.setText("");
			textField.setForeground(Color.black);
		}
	}
	@Override
	public void focusLost(FocusEvent e) {
		// ʧȥ����ʱ��û���������ݣ���ʾ��ʾ����
		String temp = textField.getText();
		if (temp.equals("")) {
			textField.setForeground(Color.gray);
			textField.setText(hintText);
		}
	}
	
}