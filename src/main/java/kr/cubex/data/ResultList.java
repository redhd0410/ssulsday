package kr.cubex.data;

import java.util.List;

public class ResultList extends BaseResult {
	//
	private List<?>		data = null;

	//
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> list) {
		this.data = list;
	}

	//
	public static ResultList create() {
		return create(RET_OK);
	}
	public static ResultList create(List<?> data) {
		ResultList	resList = create(RET_OK);
		resList.setData(data);
		return resList;
	}
	public static ResultList create(int resCode) {
		ResultList	result = new ResultList();
		result.setResultCode(resCode);
		return result;
	}
}
