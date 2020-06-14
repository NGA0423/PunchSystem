package com.attendance.bean;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

public class RButton extends JButton{
	/**
	 * Բ�ΰ�ť
	 */
	public RButton(String lable) {
		super(lable);
		//����Բ��
		Dimension size = getPreferredSize();
		size.height=size.width=Math.max(size.width, size.height);
		setPreferredSize(size);
		//�������ʹjbutton����������������һ��Բ�ı���
		setContentAreaFilled(false);
		this.setBackground(Color.green);
	}
	protected void paintComponent(Graphics graphics) {
		if (getModel().isArmed()) {
			//��ѡһ��������ɫ��ΪԲ�ΰ�ť�������
			graphics.setColor(Color.BLUE);
		}else {
			graphics.setColor(getBackground());
		}
		graphics.fillOval(0, 0, getSize().width-1, getSize().height-1);
		//���û�һ����ǩ�ͽ������
		super.paintComponent(graphics);
	}
	
	/**
	 * �ü򵥵Ļ�����ť�ı߽�
	 */
	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawOval(0, 0, getSize().width-1, getSize().height-1);
	}
	
	//������¼�
	Shape shape;
	public boolean contains(int x,int y) {
		//����һ����jackpot��ǩ�İ�ť
		RButton button = new RButton("Jackpot");
		if (shape==null||!shape.getBounds().equals(getBounds())) {
			shape=new Ellipse2D.Float(0,0,getWidth(),getHeight());
		}
		return shape.contains(x, y);
	}
}
