package kr.cubex.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.MessageSource;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResultData extends BaseResult {
	//
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, Object>			data = null;

	//
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(HashMap<String, Object> result) {
		this.data = result;
	}

	public void addData(String sName, Object value) {
		if (this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		this.data.put(sName, value);
	}
	public void addData(String sName, int value) {
		if (this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		this.data.put(sName, value);
	}
	public void setRowCount(int cnt) {
		addData("rowCount", cnt);
	}

	//
	public static ResultData create() {
		return create(RET_OK);
	}
	public static ResultData create(int resCode) {
		ResultData	result = new ResultData();
		result.setResultCode(resCode);
		return result;
	}
	public static ResultData create(HashMap<String, Object> map) {
		ResultData	result = new ResultData();
		if (map == null) {
			result.setResultCode(ERR_RESULT_FAIL);
		}
		else {
			result.setResultCode(RET_OK);
			result.setData(map);
		}
		return result;
	}
	public static ResultData create(int nRetCode, MessageSource messageSource) {
		ResultData	vo = new ResultData();
		vo.setRetCode(nRetCode, messageSource);
		return vo;
	}
}
