package com.attendance.bean;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;



public class JTableMode extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable fJTable;// 固定表格对象
	private FixedColumnTableModel fJTableMode;// 固定表格模型
	private JTable floatingColumnTable;// 移动列表格对象
	private FloatingColumnTableModel floatingColumnTableModel;
	private Vector<String> columnName;// 表格列名数组
	private Vector<Vector<Object>> tableValue;// 表格数据
	private int fixedColumn = 1;// 固定列数量

	public JTableMode(Vector<String> columnName, Vector<Vector<Object>> tableValuV, int fixedColumn) {
		super();
		setLayout(new BorderLayout());
		this.columnName=columnName;
		this.tableValue=tableValuV;
		this.fixedColumn=fixedColumn;
		//创建固定列表格模型对象
		fJTableMode=new FixedColumnTableModel();
		//创建固定表格对象
		fJTable =new JTable(fJTableMode);
		//获取选择模型对象
		ListSelectionModel fixed = fJTable.getSelectionModel();
		//选择模式为单选
		fixed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//添加行被选中的事件监听器
		fixed.addListSelectionListener(new MListSelectionListener(true));
		//创建可移动列表格模型对象
		floatingColumnTableModel =new FloatingColumnTableModel();
		//创建可移动列表格对象
		floatingColumnTable = new JTable(floatingColumnTableModel);
		//关闭表格的自动调整功能
		floatingColumnTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ListSelectionModel floating = floatingColumnTable.getSelectionModel();//获得选择模型对象
		//选择模式为单选
		floating.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//添加行被选中的事件监听器
		MListSelectionListener listener = new MListSelectionListener(false);
		floating.addListSelectionListener(listener);
		JScrollPane scrollPane = new JScrollPane();//创建一个滚动面板对象
		//将固定列表格头放到滚动面板的左上方
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fJTable.getTableHeader());
		//创建一个用来显示基础信息的视口对象
		JViewport viewport = new JViewport();
		viewport.setView(fJTable);//将固定列表格添加到视口中
		//设置视口的首选大小为固定列表格的首选大小
		viewport.setPreferredSize(fJTable.getPreferredSize());
		//将视口添加到滚动面板的标题视口中
		scrollPane.setRowHeaderView(viewport);
		//将可移动表格添加到默认视口中
		scrollPane.setViewportView(fJTable);
		add(scrollPane,BorderLayout.CENTER);
		
		
	}
	private class FixedColumnTableModel extends AbstractTableModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public int getRowCount() {
			// 返回行数
			return tableValue.size();
		}

		@Override
		public int getColumnCount() {
			// 返回固定列的数量
			return fixedColumn;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// 返回指定单元格的值
			return tableValue.get(rowIndex).get(columnIndex);
		}
		@Override
		public String getColumnName(int columnIndex) {
			//返回指定列的名称
			return columnName.get(columnIndex);
		}
		
	}
	private class FloatingColumnTableModel extends AbstractTableModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public int getRowCount() {
			// 返回行数
			return tableValue.size();
		}

		@Override
		public int getColumnCount() {
			// 返回可移动列的数量
			return columnName.size()-fixedColumn;//需要扣除固定列的数量
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// 返回指定单元格的值
			return tableValue.get(rowIndex).get(columnIndex+fixedColumn);//需要为列索引加上固定列的数量
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			//返回指定列的名称
			//需要为列索引加上固定列的数量
			return columnName.get(columnIndex+columnIndex);
		}
		
	}
	private class MListSelectionListener implements ListSelectionListener{
		boolean isFixedColumnTable =true;//默认由选中固定列表格中的行触发
		
		public MListSelectionListener(boolean b) {
			this.isFixedColumnTable=b;
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (isFixedColumnTable) {//由选中固定列表格中的行触发
				//获得固定列表格中的选中行
				int row =floatingColumnTable.getSelectedRow();
				//同时选中右侧可移动列表格中的相应行
				floatingColumnTable.setRowSelectionInterval(row, row);
				
			}else {//由选中可移动列表格中的行触发
				//获得可移动列表格中的选中行
				int row =floatingColumnTable.getSelectedRow();
				//同时选中左侧固定列表格中的相应行
				fJTable.setRowSelectionInterval(row, row);
			}
			
		}
		
	}
}
