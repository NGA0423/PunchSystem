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
	private JTable fJTable;// �̶�������
	private FixedColumnTableModel fJTableMode;// �̶����ģ��
	private JTable floatingColumnTable;// �ƶ��б�����
	private FloatingColumnTableModel floatingColumnTableModel;
	private Vector<String> columnName;// �����������
	private Vector<Vector<Object>> tableValue;// �������
	private int fixedColumn = 1;// �̶�������

	public JTableMode(Vector<String> columnName, Vector<Vector<Object>> tableValuV, int fixedColumn) {
		super();
		setLayout(new BorderLayout());
		this.columnName=columnName;
		this.tableValue=tableValuV;
		this.fixedColumn=fixedColumn;
		//�����̶��б��ģ�Ͷ���
		fJTableMode=new FixedColumnTableModel();
		//�����̶�������
		fJTable =new JTable(fJTableMode);
		//��ȡѡ��ģ�Ͷ���
		ListSelectionModel fixed = fJTable.getSelectionModel();
		//ѡ��ģʽΪ��ѡ
		fixed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//����б�ѡ�е��¼�������
		fixed.addListSelectionListener(new MListSelectionListener(true));
		//�������ƶ��б��ģ�Ͷ���
		floatingColumnTableModel =new FloatingColumnTableModel();
		//�������ƶ��б�����
		floatingColumnTable = new JTable(floatingColumnTableModel);
		//�رձ����Զ���������
		floatingColumnTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ListSelectionModel floating = floatingColumnTable.getSelectionModel();//���ѡ��ģ�Ͷ���
		//ѡ��ģʽΪ��ѡ
		floating.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//����б�ѡ�е��¼�������
		MListSelectionListener listener = new MListSelectionListener(false);
		floating.addListSelectionListener(listener);
		JScrollPane scrollPane = new JScrollPane();//����һ������������
		//���̶��б��ͷ�ŵ������������Ϸ�
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fJTable.getTableHeader());
		//����һ��������ʾ������Ϣ���ӿڶ���
		JViewport viewport = new JViewport();
		viewport.setView(fJTable);//���̶��б����ӵ��ӿ���
		//�����ӿڵ���ѡ��СΪ�̶��б�����ѡ��С
		viewport.setPreferredSize(fJTable.getPreferredSize());
		//���ӿ���ӵ��������ı����ӿ���
		scrollPane.setRowHeaderView(viewport);
		//�����ƶ������ӵ�Ĭ���ӿ���
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
			// ��������
			return tableValue.size();
		}

		@Override
		public int getColumnCount() {
			// ���ع̶��е�����
			return fixedColumn;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// ����ָ����Ԫ���ֵ
			return tableValue.get(rowIndex).get(columnIndex);
		}
		@Override
		public String getColumnName(int columnIndex) {
			//����ָ���е�����
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
			// ��������
			return tableValue.size();
		}

		@Override
		public int getColumnCount() {
			// ���ؿ��ƶ��е�����
			return columnName.size()-fixedColumn;//��Ҫ�۳��̶��е�����
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// ����ָ����Ԫ���ֵ
			return tableValue.get(rowIndex).get(columnIndex+fixedColumn);//��ҪΪ���������Ϲ̶��е�����
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			//����ָ���е�����
			//��ҪΪ���������Ϲ̶��е�����
			return columnName.get(columnIndex+columnIndex);
		}
		
	}
	private class MListSelectionListener implements ListSelectionListener{
		boolean isFixedColumnTable =true;//Ĭ����ѡ�й̶��б���е��д���
		
		public MListSelectionListener(boolean b) {
			this.isFixedColumnTable=b;
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (isFixedColumnTable) {//��ѡ�й̶��б���е��д���
				//��ù̶��б���е�ѡ����
				int row =floatingColumnTable.getSelectedRow();
				//ͬʱѡ���Ҳ���ƶ��б���е���Ӧ��
				floatingColumnTable.setRowSelectionInterval(row, row);
				
			}else {//��ѡ�п��ƶ��б���е��д���
				//��ÿ��ƶ��б���е�ѡ����
				int row =floatingColumnTable.getSelectedRow();
				//ͬʱѡ�����̶��б���е���Ӧ��
				fJTable.setRowSelectionInterval(row, row);
			}
			
		}
		
	}
}
