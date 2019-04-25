package com.ssm.gbq.dao;

import java.util.List;

import com.ssm.gbq.model.Notice;
import com.ssm.gbq.model.vo.NoticeDto;

import gbq.ssm.utils.PageBounds;

public interface NoticeDao {
    /**
     * 分页查询
     * @param notice
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageBounds<Notice> openNoticeTable(Notice notice, int currentPage, int pageSize)throws Exception;
    /**
     * 批量添加通知
     * @param notice
     * @throws Exception
     */
    void addNoticeItem(List<Notice> newList)throws Exception;
    /**
     * 通知
     * @param notice
     * @throws Exception
     */
    void addNotice(Notice notice)throws Exception;
    /**
     * 首页查询公告
     * @return
     * @throws Exception
     */
    List<NoticeDto> statisticAllNotice()throws Exception;
    List<NoticeDto> statisticAllNotice1()throws Exception;
    List<NoticeDto> statisticAllNotice2()throws Exception;
    
    /**
     * 删除
     * @param ids
     */
    void delNotice(List<Integer> ids)throws Exception;
    /**
     * 处理
     * @param ids
     * @param state
     * @throws Exception
     */
    void arrNotice(List<Integer> ids, Integer state)throws Exception;

}
