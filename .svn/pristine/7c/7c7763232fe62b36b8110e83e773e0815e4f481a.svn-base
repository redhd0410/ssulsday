package kr.cubex.comm.web;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface CmBaseMapper<T, SCH> {

	void insertData(T vo) throws Exception;

	int updateData(T vo) throws Exception;

	int deleteData(T vo) throws Exception;

	T selectData(T vo) throws Exception;

	List<?> selectListData(SCH searchVO) throws Exception;

	int selectListCount(SCH searchVO);
}
