package com.ssm.gbq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.gbq.dao.NewsDao;
import com.ssm.gbq.mapper.NewsMapper;
import com.ssm.gbq.model.News;

import gbq.ssm.utils.PageBounds;
/**
 * 消息通知Dao
 * @author 阿前
 * 2019年2月7日18:21:42
 */
@Repository
public class NewsDaoImpl implements NewsDao{
    @Autowired 
    private NewsMapper newsMapper;

    public void addNews(News news) throws Exception {
        newsMapper.insertSelective(news);
    }

    public void addNewsList(News news) throws Exception {
        newsMapper.addNewsList(news);
    }

    public Integer selectNewsCount(Integer managerId) throws Exception {
        return newsMapper.selectNewsCount(managerId);
    }

    public PageBounds<News> openNewsTable(News news, int currentPage, int pageSize) throws Exception {
        final int totalSize = newsMapper.countByExample(news);
        PageBounds<News> pageBounds = new PageBounds<News>(currentPage, totalSize, pageSize);
        List<News> list = newsMapper.selectByLimitPage(news, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    public void updateNewState(News news) throws Exception {
        newsMapper.updateNewState(news);
    }

    public void delNews(List<Integer> ids) throws Exception {
        newsMapper.deleteByPrimaryKey(ids);
    }

}
