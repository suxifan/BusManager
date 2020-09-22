package com.cictec.web.auth.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.auth.pojo.BusLine;
@Repository("repoBusLineDao")
public class BusLineMapperImpl implements BusLineMapper{
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSessionTemplate;
	public int deleteByPrimaryKey(String lineId)
    {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lineId", lineId);
		sqlSessionTemplate.delete("com.cictec.web.auth.dao.BusLineMapper.deleteByPrimaryKey", map);
    	return 0;
    }

	public int insert(BusLine record){
    	return 0;
    }

	public int insertSelective(BusLine record){
		sqlSessionTemplate.insert("com.cictec.web.auth.dao.BusLineMapper.insertSelective",
				record);
    	return 0;
    }

	public List<BusLine> selectByPrimaryKey(String lineId){
		List<BusLine> busLineList = null;
		//try{
			busLineList = sqlSessionTemplate.selectList("com.cictec.web.auth.dao.BusLineMapper.selectByPrimaryKey");
		//}catch (Exception e) {
		//}
		return busLineList;
    }

	public int updateByPrimaryKeySelective(BusLine record){
		
    	return 0;
    }

	public int updateByPrimaryKey(BusLine record){
		sqlSessionTemplate.update("com.cictec.web.auth.dao.BusLineMapper.updateByPrimaryKey",
				record);
    	return 0;
    }
	
	public List<BusLine> queryBusLineByKeywords(String key)
	{
		List<BusLine> busLineList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("line", key);
		busLineList = sqlSessionTemplate.selectList("com.cictec.web.auth.dao.BusLineMapper.queryBusLineByKeywords", map);
		return busLineList;
	}

    @Override
    public BusLine queryBusLineByNameAndOrg(Map map) {
        BusLine busLine = sqlSessionTemplate.selectOne("com.cictec.web.auth.dao.BusLineMapper.queryBusLineByNameAndOrg", map);
        return busLine;
    }
}