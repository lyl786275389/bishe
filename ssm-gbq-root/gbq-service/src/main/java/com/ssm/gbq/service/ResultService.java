package com.ssm.gbq.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssm.gbq.model.Results;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;

public interface ResultService {
    /**
     * 获取成绩列表
     * @param results
     * @param currentPage
     * @param pageSize
     * @return
     * @throws BusinessException
     */
    PageBounds<Results> openResultsTable(Results results, int currentPage, int pageSize)throws BusinessException;
    /**
     * 返回考试时间
     * @return
     * @throws BusinessException
     */
    List<String> selectExamTime(Integer state)throws BusinessException;
    /**
     * 添加
     * @param results
     * @throws BusinessException
     */
    void addResult(Results results)throws BusinessException;
    /**
     * 修改
     * @param results
     * @throws BusinessException
     */
    void updateResult(Results results)throws BusinessException;
    /**
     * 通过id把成绩移至成绩档案
     * @param ids
     * @param state
     * @throws BusinessException
     */
    void updateResultById(List<Integer> ids, Integer state)throws BusinessException;
    /**
     * 删除
     * @param ids
     * @throws BusinessException
     */
    void delResultById(List<Integer> ids)throws BusinessException;
   /**
    * 保存上传好的学生成绩
    * @param inputStream
    * @param file
    */
    void insertUploadExcel(InputStream inputStream, MultipartFile file)throws BusinessException;

}
