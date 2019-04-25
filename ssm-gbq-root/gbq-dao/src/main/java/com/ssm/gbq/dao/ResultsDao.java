package com.ssm.gbq.dao;

import java.util.List;

import com.ssm.gbq.model.Results;

import gbq.ssm.utils.PageBounds;

public interface ResultsDao {
    /**
     * 分页查询
     * @param results
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageBounds<Results> openResultsTable(Results results, int currentPage, int pageSize)throws Exception;
    /**
     * 查询考试时间
     * @return
     * @throws Exception
     */
    List<String> selectExamTime(Integer state)throws Exception;
    /**
     * 添加
     * @param results
     * @throws Exception
     */
    void addResult(Results results)throws Exception;
    /**
     * 修改
     * @param results
     * @throws Exception
     */
    void updateResult(Results results)throws Exception;
    /**
     * 通过id把成绩移至成绩档案
     * @param ids
     * @param state
     * @throws Exception
     */
    void updateResultById(List<Integer> ids, Integer state)throws Exception;
    /**
     * 删除
     * @param ids
     * @throws Exception
     */
    void delResultById(List<Integer> ids)throws Exception;
    /**
     * Excel导入  批量添加学生
     * @param resultslist
     * @throws Exception
     */
    void insertBatchResults(List<Results> resultslist)throws Exception;
    /**
     * 通过id查询学生对应的managerIds
     * @param ids
     * @return
     * @throws Exception
     */
    List<Integer> selectResultsById(List<Integer> ids)throws Exception;

}
