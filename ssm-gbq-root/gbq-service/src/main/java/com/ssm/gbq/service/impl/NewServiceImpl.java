package com.ssm.gbq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.gbq.dao.NewsDao;
import com.ssm.gbq.model.News;
import com.ssm.gbq.service.NewService;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;

/**
 * 消息service
 * @author 阿前
 * 2019年2月11日18:15:51
 */
@Service
public class NewServiceImpl implements NewService{
    @Autowired
    private NewsDao newsDao;

    public Integer selectNewsCount(Integer managerId) throws BusinessException {
        try {
            return newsDao.selectNewsCount(managerId);
        } catch (Exception e) {
            throw new BusinessException("查询失败！",e.getMessage());
        }
    }

    public PageBounds<News> openNewsTable(News news, int currentPage, int pageSize) throws BusinessException {
        try {
            return newsDao.openNewsTable(news,currentPage,pageSize);
        } catch (Exception e) {
            throw new BusinessException("分页查询失败！",e.getMessage());
        }
    }

    public void updateNewState(News news) throws BusinessException {
        try {
            newsDao.updateNewState(news);
        } catch (Exception e) {
            throw new BusinessException("修改失败！",e.getMessage());
        }
    }

    public void delNews(List<Integer> ids) throws BusinessException {
        try {
            newsDao.delNews(ids);
        } catch (Exception e) {
            throw new BusinessException("删处失败！",e.getMessage());
        }
    }

    public void addNewsList(News news) throws BusinessException {
        try {
            newsDao.addNewsList(news);
        } catch (Exception e) {
            throw new BusinessException("添加失败！",e.getMessage());
        }
    }

}
