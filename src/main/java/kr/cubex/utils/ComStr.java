/**
 * version : 1.3
 * update  : 2018.03.02
 */
package kr.cubex.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ComStr {

	/**
	 * String 형변환
	 * @param oSrc
	 * @return
	 */
	public static String toStr(Object oSrc) {
		if (oSrc == null)
			return "";

		if (oSrc instanceof String)
			return (String) oSrc;

		return oSrc.toString();
	}

	/**
	 * Integer 형변환
	 * @param oSrc
	 * @param nDefVal
	 * @return
	 */
	public static int toInt(Object oSrc, int nDefVal) {
		if (oSrc == null)
			return nDefVal;

		if (oSrc instanceof Integer)
			return (Integer) oSrc;

		if (oSrc instanceof Double) {
			return ((Double) oSrc).intValue();
		}

		if (oSrc instanceof Float) {
			return ((Float) oSrc).intValue();
		}

		int 	retValue = nDefVal;

		try {
			String sTarget = oSrc.toString();
			retValue = Integer.parseInt(sTarget.trim());
		} catch (NumberFormatException e) { retValue = nDefVal; }

		return retValue;
	}

	/**
	 * Long 형변환
	 * @param oSrc
	 * @param nDefVal
	 * @return
	 */
	public static long toLong(Object oSrc, long nDefVal) {
		if (oSrc == null)
			return nDefVal;

		if (oSrc instanceof Integer)
			return ((Integer) oSrc).longValue();

		if (oSrc instanceof Long)
			return (Long) oSrc;

		if (oSrc instanceof Double) {
			return ((Double) oSrc).intValue();
		}

		if (oSrc instanceof Float) {
			return ((Float) oSrc).intValue();
		}

		long 	retValue = nDefVal;

		try {
			String sTarget = oSrc.toString();
			retValue = Long.parseLong(sTarget.trim());
		} catch (NumberFormatException e) { retValue = nDefVal; }

		return retValue;
	}

	/**
	 * Float 형변환
	 * @param oSrc
	 * @param nDefVal
	 * @return
	 */
	public static float toFloat(Object oSrc, float nDefVal) {
		if (oSrc == null)
			return nDefVal;

		if (oSrc instanceof String) {
			float 	retValue = nDefVal;

			try {
				retValue = Float.parseFloat(((String) oSrc).trim());
			} catch (Exception e) { retValue = nDefVal; }

			return retValue;
		}

		if (oSrc instanceof Integer) {
			return ((Integer) oSrc).floatValue();
		}
		if (oSrc instanceof Double) {
			return ((Double) oSrc).floatValue();
		}
		if (oSrc instanceof Float) {
			return ((Float) oSrc).floatValue();
		}

		float 	retValue = nDefVal;

		try {
			retValue = Float.parseFloat(oSrc.toString());
		} catch (Exception e) { retValue = nDefVal; }

		return retValue;
	}

	/**
	 * Integer 형변환
	 * @param oSrc
	 * @return
	 */
	public static int toInt(Object oSrc) {
		return toInt(oSrc, 0);
	}

	/**
	 * Long 형변환
	 * @param oSrc
	 * @return
	 */
	public static long toLong(Object oSrc) {
		return toLong(oSrc, 0);
	}

	/**
	 * String 크기 반환
	 * @param sSrc
	 * @return
	 */
	public static int getLength(String sSrc) {
		if (sSrc == null)
			return 0;
		return sSrc.length();
	}

	/**
	 * String 비어 있는지 검사하는 함수
	 * @param sSrc
	 * @return
	 */
	public static boolean isEmpty(String sSrc) {
		if (sSrc == null)
			return true;
		return sSrc.isEmpty();
	}

	/**
	 * String 비어 있는지 검사하는 함수
	 * @param oSrc
	 * @return
	 */
	public static boolean isEmpty(Object oSrc) {
		if (oSrc == null) {
			return true;
		}
		if (oSrc instanceof String) {
			return ((String) oSrc).isEmpty();
		}
		return false;
	}

	/**
	 * String Base64 Encode
	 * @param oSrc
	 * @return
	 */
	public static String toBase64Encode(Object oData) {
		String		str = toStr(oData);

		if (str.isEmpty()) {
			return str;
		}

		return Base64Utils.encodeToString(str.getBytes());
	}

	/**
	 * String Base64 Decode
	 * @param oSrc
	 * @return
	 */
	public static String toBase64Decode(String str) {
		if (str == null) {
			return "";
		}
		if (str.isEmpty()) {
			return str;
		}

		byte[] bytes = Base64Utils.decodeFromString(str);
		if (bytes == null) {
			return "";
		}

		return new String(bytes);
	}

	/**
	 * Json String 변환
	 * @param oSrc
	 * @return
	 */
	public static String toJson(Object obj, String defVal) {
		ObjectMapper	objectMapper = new ObjectMapper();
		String			json = defVal;

		try {
			json = objectMapper.writeValueAsString(obj);
		} catch (IOException e) {
			json = defVal;
		}
		return json;
	}
	public static String toJson(Object obj) {
		return toJson(obj, "{}");
	}
	public static <T> List<T> toList(String json) {
		ObjectMapper	objectMapper = new ObjectMapper();
		List<T>			aryList = null;

		if (json != null && json.length() > 0) {
			try {
				aryList = objectMapper.readValue(json, new TypeReference<List<T>>(){});
			} catch (IOException e) {
				aryList = null;
			}
		}
		if (aryList == null) {
			aryList = new ArrayList<T>();
		}
		return aryList;
	}

}
