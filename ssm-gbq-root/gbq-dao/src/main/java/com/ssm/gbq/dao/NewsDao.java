package com.ssm.gbq.dao;

import java.util.List;

import com.ssm.gbq.model.News;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;

public interface NewsDao {
    /**
     * 添加
     * @param news
     * @throws BusinessException
     */
    void addNews(News news)throws Exception;
    /**
     * 批量添加
     * @param news
     * @throws BusinessException
     */
    void addNewsList(News news)throws Exception;
    /**
     * 查询未读数量
     * @param managerId
     * @return
     * @throws Exception
     */
    Integer selectNewsCount(Integer managerId)throws Exception;
    /**
     * 分页查询
     * @param news
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageBounds<News> openNewsTable(News news, int currentPage, int pageSize)throws Exception;
    /**
     * 处理消息
     * @param news
     * @throws Exception
     */
    void updateNewState(News news)throws Exception;
    /**
     * 删除
     * @param ids
     * @throws Exception
     */
    void delNews(List<Integer> ids)throws Exception;

}
