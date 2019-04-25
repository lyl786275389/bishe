package com.ssm.gbq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.gbq.model.Notice;
import com.ssm.gbq.model.NoticeExample;
import com.ssm.gbq.model.vo.NoticeDto;

public interface NoticeMapper {
    int countByExample(@Param("example")Notice notice);

    int deleteByExample(NoticeExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Notice record);

    int insertSelective(Notice record);

    List<Notice> selectByExample(NoticeExample example);

    Notice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByExample(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    List<Notice> selectByLimitPage(@Param("example")Notice notice,@Param("offset") int offset, @Param("limit")int pageSize);

    void addNoticeItem(List<Notice> newList);

    List<NoticeDto> statisticAllNotice();
    List<NoticeDto> statisticAllNotice1();
    List<NoticeDto> statisticAllNotice2();

    void arrNotice(@Param("ids")List<Integer> ids, @Param("state")Integer state);
}