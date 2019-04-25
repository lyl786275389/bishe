package com.ssm.gbq.service;

import java.util.List;

import com.ssm.gbq.model.Notice;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;

public interface NoticeService {
    /**
     * 分页查询
     * @param notice
     * @param currentPage
     * @param pageSize
     * @return
     * @throws BusinessException
     */
    PageBounds<Notice> openNoticeTable(Notice notice, int currentPage, int pageSize)throws BusinessException;
    /**
     * 添加
     * @param notice
     * @throws BusinessException
     */
    void addNotice(Notice notice)throws BusinessException;
    /**
     * 删除
     * @param ids
     * @throws BusinessException
     */
    void delNotice(List<Integer> ids)throws BusinessException;
    /**
     * 处理申请公告
     * @param asList
     * @param state
     * @throws BusinessException
     */
    void arrNotice(List<Integer> asList, Integer state)throws BusinessException;
   

}
