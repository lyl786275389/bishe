package com.ssm.gbq.mapper;

import com.ssm.gbq.model.News;
import com.ssm.gbq.model.NewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsMapper {
    int countByExample(@Param("example")News news);

    int deleteByExample(NewsExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(News record);

    int insertSelective(News record);

    List<News> selectByExample(NewsExample example);

    News selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExample(@Param("record") News record, @Param("example") NewsExample example);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    void addNewsList(News news);

    Integer selectNewsCount(Integer managerId);

    List<News> selectByLimitPage(@Param("example")News news,@Param("offset") int offset, @Param("limit")int pageSize);

    void updateNewState(News news);
}