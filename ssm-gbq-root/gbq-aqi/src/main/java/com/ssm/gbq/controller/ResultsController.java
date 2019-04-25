package com.ssm.gbq.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ssm.gbq.model.Results;
import com.ssm.gbq.model.enums.ExamStatusEnum;
import com.ssm.gbq.model.vo.ClassEnumDto;
import com.ssm.gbq.service.ResultService;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;

/**
 * 成绩Controller
 * @author 阿前
 * 2019年1月20日10:12:59
 */
@Controller
@RequestMapping("/results")
public class ResultsController {
    @Autowired
    private ResultService resultService;
    
    /**
     * 获取教师列表
     * @Title: openResultsTable
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/openResultsTable", method = RequestMethod.POST)
    public HashMap<String, Object> openResultsTable(Results results,int pageSize,int currentPage){
        HashMap<String,Object> result = new HashMap<String,Object>();
        PageBounds<Results> pageBounds = resultService.openResultsTable(results, currentPage,pageSize);
        result.put("data", pageBounds);
        return result;
    }
    
    /**
     * 查询全部考试时间
     * @Title: openResultsTable
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectExamTime", method = RequestMethod.POST)
    public HashMap<String, Object> selectExamTime(Integer state){
        HashMap<String,Object> result = new HashMap<String,Object>();
        List<String> examTime = resultService.selectExamTime(state);
        List<ExamStatusEnum> exam = ExamStatusEnum.getexamiesEnum();
        List<ClassEnumDto> dtos = new ArrayList<>();
        for(ExamStatusEnum e : exam){
            ClassEnumDto enumDto = new ClassEnumDto();
            enumDto.setId(e.getId());
            enumDto.setName(e.getName());
            dtos.add(enumDto);
        }
        result.put("examTime", examTime);
        result.put("exam", dtos);
        return result;
    }
    
    /**
     * 添加成绩
     * @Title: addResult
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/addResult", method = RequestMethod.POST)
    public HashMap<String, Object> addResult(Results results){
        HashMap<String,Object> result = new HashMap<String,Object>();
        resultService.addResult(results);
        return result;
    }
    
    /**
     * 修改成绩
     * @Title: updateResult
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateResult", method = RequestMethod.POST)
    public HashMap<String, Object> updateResult(Results results){
        HashMap<String,Object> result = new HashMap<String,Object>();
        resultService.updateResult(results);
        return result;
    }
    
    /**
     * 通过id把成绩移至成绩档案
     * @Title: delStudentById
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateResultById", method = RequestMethod.POST)
    public HashMap<String, Object> updateResultById(Integer[] ids,Integer states){
        HashMap<String,Object> result = new HashMap<String,Object>();
        resultService.updateResultById(Arrays.asList(ids),states);
        return result;
    }
    
    /**
     * 保存上传好的学生成绩
     * @Title: savaResults
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/savaResults",  method = { RequestMethod.GET, RequestMethod.POST } )
    public HashMap<String, Object> upload(@RequestParam(value = "file", required = false)MultipartFile file,
            HttpServletResponse response, HttpSession session) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        
        if (file.isEmpty()) {
            result.put("error", "文件不存在");
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            resultService.insertUploadExcel(inputStream, file);
        } catch (BusinessException e) {
            result.put("error", e.getErrorMessage());
        } catch (IOException e) {
            result.put("error", e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
            return result;
    }
}
