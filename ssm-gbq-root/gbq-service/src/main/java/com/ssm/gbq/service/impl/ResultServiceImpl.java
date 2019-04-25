package com.ssm.gbq.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssm.gbq.dao.NewsDao;
import com.ssm.gbq.dao.ResultsDao;
import com.ssm.gbq.dao.StudentDao;
import com.ssm.gbq.model.News;
import com.ssm.gbq.model.Results;
import com.ssm.gbq.model.Student;
import com.ssm.gbq.service.ResultService;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.ExcelUtils;
import gbq.ssm.utils.PageBounds;
import gbq.ssm.utils.StringUtil;

/**
 * 成绩Service
 * @author 阿前
 * 2019年1月25日19:04:22
 */
@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultsDao resultsDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private NewsDao newsDao;
    
    public PageBounds<Results> openResultsTable(Results results, int currentPage, int pageSize)throws BusinessException {
        try {
            return resultsDao.openResultsTable(results,currentPage,pageSize);
        } catch (Exception e) {
            throw new BusinessException("分页查询失败！",e.getMessage());
        }
    }

    public List<String> selectExamTime(Integer state) throws BusinessException {
        try {
            return resultsDao.selectExamTime(state);
        } catch (Exception e) {
            throw new BusinessException("查询全部考试时间失败！",e.getMessage());
        }
    }

    public void addResult(Results results) throws BusinessException {
        try {
            if (results.getEndTime() != null) {
                results.setExamTime(StringUtil.stringToDateMM(results.getEndTime()));
            }
            resultsDao.addResult(results);
        } catch (Exception e) {
            throw new BusinessException("添加失败！",e.getMessage());
        }
    }

    public void updateResult(Results results) throws BusinessException {
        try {
            resultsDao.updateResult(results);
        } catch (Exception e) {
            throw new BusinessException("修改失败！",e.getMessage());
        }
    }

    public void updateResultById(List<Integer> ids, Integer state) throws BusinessException {
        try {
            resultsDao.updateResultById(ids,state);
            if (state==1) {
                List<Integer> managerIds = resultsDao.selectResultsById(ids);
                News news = new News();
                news.setCreatetime(new Date());
                news.setManagerIds(managerIds);
                news.setNews("最新的成绩公布了");
                newsDao.addNewsList(news);
            }
        } catch (Exception e) {
            throw new BusinessException("移除失败！",e.getMessage());
        }
    }

    public void delResultById(List<Integer> ids) throws BusinessException {
        try {
            resultsDao.delResultById(ids);
        } catch (Exception e) {
            throw new BusinessException("移除失败！",e.getMessage());
        }
    }

    public void insertUploadExcel(InputStream in, MultipartFile file)throws BusinessException{
            List<List<Object>> listob = new ArrayList<List<Object>>();
            try {
                listob = ExcelUtils.getBankListByExcel(in,
                        file.getOriginalFilename());
            } catch (Exception e) {
                throw new BusinessException("excel格式不正确");
            }
            
            List<Results> resultslist = new ArrayList<Results>();
            //查询全部学生
            List<Student> students = studentDao.getAllStudent();
            for (int i = 0; i < listob.size(); i++) {
                List<Object> ob = listob.get(i);
                Results results = new Results();
                //学生名字
                if (StringUtils.isNotBlank(String.valueOf(ob.get(0)))) {
                    for (Student student : students) {
                        if (String.valueOf(ob.get(0)).trim().equals(student.getName())) {
                            results.setStudentId(student.getId());
                        }else {
                           throw new BusinessException(String.valueOf(ob.get(0))+"这位学生不存在，请核实");
                        }
                    }
                }
                //语文成绩
                if (StringUtils.isNotBlank(String.valueOf(ob.get(1)))){
                    results.setChinese(Double.valueOf((String) ob.get(1)));
                }
                //数学成绩
                if (StringUtils.isNotBlank(String.valueOf(ob.get(2)))){
                    results.setMath(Double.valueOf((String) ob.get(2)));
                }
                //英语
                if (StringUtils.isNotBlank(String.valueOf(ob.get(3)))){
                    results.setEnglish(Double.valueOf((String) ob.get(3)));
                }
                //政治
                if (StringUtils.isNotBlank(String.valueOf(ob.get(4)))){
                    results.setPolitical(Double.valueOf((String) ob.get(4)));
                }
                //历史
                if (StringUtils.isNotBlank(String.valueOf(ob.get(5)))){
                    results.setHistory(Double.valueOf((String) ob.get(5)));
                }
                //地理
                if (StringUtils.isNotBlank(String.valueOf(ob.get(6)))){
                    results.setGeographic(Double.valueOf((String) ob.get(6)));
                }
                //考试阶段
                if ("期中考试".equals(String.valueOf(ob.get(7)))) {
                    results.setExamStatus(1);
                } else if ("期末考试".equals(String.valueOf(ob.get(7)))) {
                    results.setExamStatus(2);
                } else {
                    results.setExamStatus(null);
                }
                //考试时间
                results.setExamTime(new Date());
                resultslist.add(results);
            }
            
        try {
            resultsDao.insertBatchResults(resultslist);
        }catch (Exception e) {
            throw new BusinessException("上传失败！",e.getMessage());
        }
    }


}
