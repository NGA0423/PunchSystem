package com.attendance.bean;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

public class RButton extends JButton{
	/**
	 * 圆形按钮
	 */
	public RButton(String lable) {
		super(lable);
		//声明圆形
		Dimension size = getPreferredSize();
		size.height=size.width=Math.max(size.width, size.height);
		setPreferredSize(size);
		//这个调用使jbutton不画背景，而允许画一个圆的背景
		setContentAreaFilled(false);
		this.setBackground(Color.green);
	}
	protected void paintComponent(Graphics graphics) {
		if (getModel().isArmed()) {
			//任选一个高亮颜色作为圆形按钮类的属性
			graphics.setColor(Color.BLUE);
		}else {
			graphics.setColor(getBackground());
		}
		graphics.fillOval(0, 0, getSize().width-1, getSize().height-1);
		//调用画一个标签和焦点矩形
		super.paintComponent(graphics);
	}
	
	/**
	 * 用简单的弧画按钮的边界
	 */
	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawOval(0, 0, getSize().width-1, getSize().height-1);
	}
	
	//侦测点击事件
	Shape shape;
	public boolean contains(int x,int y) {
		//产生一个带jackpot标签的按钮
		RButton button = new RButton("Jackpot");
		if (shape==null||!shape.getBounds().equals(getBounds())) {
			shape=new Ellipse2D.Float(0,0,getWidth(),getHeight());
		}
		return shape.contains(x, y);
	}
}
