package kr.cubex.comm.web;

import java.util.List;


public interface CmBaseService<T, SCH> {

	void insertData(T vo) throws Exception;

	int updateData(T vo) throws Exception;

	int deleteData(T vo) throws Exception;

	T selectData(T vo) throws Exception;

	List<?> selectListData(SCH searchVO) throws Exception;

	int selectListCount(SCH searchVO) throws Exception;

}
