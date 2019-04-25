package com.ssm.gbq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.gbq.dao.ResultsDao;
import com.ssm.gbq.mapper.ResultsMapper;
import com.ssm.gbq.model.Results;

import gbq.ssm.utils.PageBounds;

/**
 * 成绩DaoImpl
 * @author 阿前
 * 2019年1月25日19:09:12
 */
@Repository
public class ResultsDaoImpl implements ResultsDao {
    @Autowired
    private ResultsMapper resultsMapper;

    public PageBounds<Results> openResultsTable(Results results, int currentPage, int pageSize)
            throws Exception {
        final int totalSize = resultsMapper.countByExample(results);
        PageBounds<Results> pageBounds = new PageBounds<Results>(currentPage, totalSize, pageSize);
        List<Results> list = resultsMapper.selectByLimitPage(results, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    public List<String> selectExamTime(Integer state) throws Exception {
        return resultsMapper.selectExamTime(state);
    }

    public void addResult(Results results) throws Exception {
        resultsMapper.insertSelective(results);
    }

    public void updateResult(Results results) throws Exception {
        resultsMapper.updateByPrimaryKeySelective(results);
    }

    public void updateResultById(List<Integer> ids, Integer state) throws Exception {
        resultsMapper.updateResultById(ids,state);
    }

    public void delResultById(List<Integer> ids) throws Exception {
        resultsMapper.deleteByPrimaryKey(ids);
    }

    public void insertBatchResults(List<Results> resultslist) throws Exception {
        resultsMapper.insertBatchResults(resultslist);
    }

    public List<Integer> selectResultsById(List<Integer> ids) throws Exception {
        return resultsMapper.selectResultsById(ids);
    }
}
