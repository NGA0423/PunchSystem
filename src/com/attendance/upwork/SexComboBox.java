package com.attendance.upwork;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class SexComboBox extends AbstractListModel<String> implements ComboBoxModel<String>{
	/**
	 * ѡ�����������
	 */
	private static final long serialVersionUID = 1L;
	String selecteditem = null;
	String[] test = { "��", "Ů" };
	@Override
	public int getSize() {
		
		return test.length;
	}

	@Override
	public String getElementAt(int index) {
		
		return test[index];
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selecteditem=(String) anItem;
		
	}

	@Override
	public Object getSelectedItem() {
		
		return selecteditem;
	}
	public int getIndex() {
		for (int i = 0; i < test.length; i++) {
			if (test[i].equals(getSelectedItem()))
				return i;
		}
		return 0;
	}
}
