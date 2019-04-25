package com.ssm.gbq.mapper;

import com.ssm.gbq.model.Results;
import com.ssm.gbq.model.ResultsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResultsMapper {
    int countByExample(@Param("example")Results results);

    int deleteByExample(ResultsExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Results record);

    int insertSelective(Results record);

    List<Results> selectByExample(ResultsExample example);

    Results selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Results record, @Param("example") ResultsExample example);

    int updateByExample(@Param("record") Results record, @Param("example") ResultsExample example);

    int updateByPrimaryKeySelective(Results record);

    int updateByPrimaryKey(Results record);

    List<Results> selectByLimitPage(@Param("example")Results results,@Param("offset") int offset,@Param("limit") int pageSize);

    List<String> selectExamTime(@Param("state")Integer state);

    void updateResultById(@Param("item")List<Integer> ids, @Param("state")Integer state);

    void insertBatchResults(List<Results> resultslist);

    List<Integer> selectResultsById(List<Integer> ids);
}