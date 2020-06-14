package com.attendance.upwork;

import java.util.Calendar;

public class MenuBar {
	/**
	 * Ö÷³ÌÐò
	 */
	
	public static void main(String[] args) {
		Calendar time = Calendar.getInstance();
		int hour = time.get(Calendar.HOUR_OF_DAY);
		if (0<hour&&hour<=12) {
			new UpWork(null);
		}else {
			new GoOffWork(null);
		}
		
	}
}
