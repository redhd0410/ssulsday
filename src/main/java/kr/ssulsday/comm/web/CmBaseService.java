package kr.ssulsday.comm.web;

import java.util.List;


public interface CmBaseService<T, SCH> {

	/**
	 * 데이터를 등록한다.

	 */
	Object insertData(T vo) throws Exception;

	/**
	 * 데이터를 수정한다.
	 */
	int updateData(T vo) throws Exception;

	/**
	 * 데이터를 삭제한다.
	 */
	int deleteData(T vo) throws Exception;

	/**
	 * 데이터를 조회한다.
	 */
	T selectData(T vo) throws Exception;

	/**
	 * 데이터 목록을 조회한다.
	 */
	List<?> selectListData(SCH searchVO) throws Exception;

	/**
	 * 데이터 총 갯수를 조회한다.
	 */
	int selectListCount(SCH searchVO) throws Exception;

}
