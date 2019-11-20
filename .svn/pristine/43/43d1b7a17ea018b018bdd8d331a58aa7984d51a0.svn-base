package kr.cubex.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ComDate {

	/**
	 * 현재일시를 문자열로 변환해주는 함수
	 * 기본패턴 : "yyyyMMddHHmmss"
	 * 
	 * @param strDatePattern
	 * @return
	 */
	public static String makeDateTimeMS() {
		Calendar 	calendar = Calendar.getInstance();
		Date 		date = calendar.getTime();
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
	}
	public static String makeDateTimeString() {
		Calendar 	calendar = Calendar.getInstance();
		Date 		date = calendar.getTime();
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}
	public static String makeDateString() {
		Calendar 	calendar = Calendar.getInstance();
		Date 		date = calendar.getTime();
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}
	public static String makeDispDateString() {
		Calendar 	calendar = Calendar.getInstance();
		Date 		date = calendar.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	public static String makeDispDateString(int nAddDay) {
		Calendar 	calendar = Calendar.getInstance();
		
		calendar.add(Calendar.DATE, nAddDay);
		Date 		date = calendar.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	public static String makeDispDateTimeString() {
		Calendar 	calendar = Calendar.getInstance();
		Date 		date = calendar.getTime();
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	public static String makeDispDateTimeString(int nAddHour) {
		Calendar 	calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, nAddHour);
		Date 		date = calendar.getTime();
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	//
	public static String makeDateTimeString(Date date) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}
	public static String makeDateString(Date date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	/**
	 * 현재일시를 문자열로 변환해주는 함수
	 * strDatePattern : "yyyyMMddHHmmss"
	 * 
	 * @param strDatePattern
	 * @return
	 */
	public static String makeDateTimeString(String strDatePattern) {
		Calendar 	calendar = Calendar.getInstance();
		Date 		date = calendar.getTime();
		return new SimpleDateFormat(strDatePattern).format(date);
	}
}
