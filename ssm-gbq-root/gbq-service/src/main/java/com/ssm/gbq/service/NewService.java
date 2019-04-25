package com.ssm.gbq.service;

import java.util.List;

import com.ssm.gbq.model.News;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;

public interface NewService {
    /**
     * 查询未读消息数量
     * @param id
     * @return
     */
    Integer selectNewsCount(Integer id)throws BusinessException;
    /**
     * 消息列表
     * @param news
     * @param currentPage
     * @param pageSize
     * @return
     * @throws BusinessException
     */
    PageBounds<News> openNewsTable(News news, int currentPage, int pageSize)throws BusinessException;
    /**
     * 消息已读
     * @param news
     * @throws BusinessException
     */
    void updateNewState(News news)throws BusinessException;
    /**
     * 删除
     * @param ids
     * @throws BusinessException
     */
    void delNews(List<Integer> ids)throws BusinessException;
    /**
     * 不合理评论删除消息
     * @param news
     * @throws BusinessException
     */
    void addNewsList(News news)throws BusinessException;

}
