package kr.cubex.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DateUtil {

	/**
	 * 두날짜 사이의 일수를 리턴
	 * 
	 * @param fromDate
	 *            yyyyMMdd 형식의 시작일
	 * @param toDate
	 *            yyyyMMdd 형식의 종료일
	 * @return 두날짜 사이의 일수
	 */
	public static int getDiffDayCount(String fromDate, String toDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		try {
			return (int) ((sdf.parse(toDate).getTime() - sdf.parse(fromDate)
					.getTime()) / 1000 / 60 / 60 / 24);
		} catch (ParseException e) {
			return 0;
		}
	}

	/**
	 * 시작일부터 종료일까지 사이의 날짜를 배열에 담아 리턴 ( 시작일과 종료일을 모두 포함한다 )
	 * 
	 * @param fromDate
	 *            yyyyMMdd 형식의 시작일
	 * @param toDate
	 *            yyyyMMdd 형식의 종료일
	 * @return yyyyMMdd 형식의 날짜가 담긴 배열
	 */
	public static String[] getDiffDays(String fromDate, String toDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Calendar cal = Calendar.getInstance();

		try {
			cal.setTime(sdf.parse(fromDate));
		} catch (ParseException e) { cal.set(Calendar.SECOND, 0); }

		int count = getDiffDayCount(fromDate, toDate);

		// 시작일부터
		cal.add(Calendar.DATE, -1);

		// 데이터 저장
		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i <= count; i++) {
			cal.add(Calendar.DATE, 1);

			list.add(sdf.format(cal.getTime()));
		}

		String[] result = new String[list.size()];

		list.toArray(result);

		return result;
	}

	public static String[] getDiffMonth(String start_dt, String end_dt) {
		
		int diffe = Integer.parseInt(end_dt);
		int diffs = Integer.parseInt(start_dt);
		
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i = diffs; i <= diffe ; i++) {
			
			if(String.valueOf(i).substring(4, 6).equals("13")) {
				
				String newDiff = Integer.parseInt(String.valueOf(i).substring(0, 4)) + 1  + "01";
				list.add(newDiff);
				i = Integer.parseInt(newDiff);
			} else {
				list.add(String.valueOf(i));
			}
		}
		
		String[] result = new String[list.size()];

		list.toArray(result);

		return result;
	}

}