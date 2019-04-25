package com.ssm.gbq.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.gbq.model.Course;
import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.Teacher;
import com.ssm.gbq.service.CourseService;

import gbq.ssm.utils.PageBounds;

/**
 * 班级Controller
 * @author 阿前
 * 2019年1月14日11:29:09
 */
@Controller
@RequestMapping("/course")
public class CousreController {
    @Autowired 
    private CourseService  courseService;
    
    /**
     * 获取全部班级信息
     * @Title: getUserInfo
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllCourse", method = RequestMethod.POST)
    public HashMap<String, Object> getAllCourse(){
        HashMap<String,Object> result = new HashMap<String,Object>();
        List<Course> course = courseService.getALLCourse();
        result.put("clasies",course);
        return result;
    }
    
    /**
     * 分页查询班级
     * @Title: openManagerTable
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/openCourseTable", method = RequestMethod.POST)
    public HashMap<String, Object> openCourseTable(Course course,int pageSize,int currentPage){
        HashMap<String,Object> result = new HashMap<String,Object>();
        PageBounds<Course> pageBounds = courseService.openCourseTable(course, currentPage,pageSize);
        result.put("data", pageBounds);
        return result;
    }
    
    /**
     * 获取任教老师详情
     * @Title: teacherArrTableData
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/teacherArrTableData", method = RequestMethod.POST)
    public HashMap<String, Object> recordTableData(Integer courseId,int currentPage,int pageSize){
        HashMap<String,Object> result = new HashMap<String,Object>();
        PageBounds<Teacher> pageList= courseService.teacherArrTableData(courseId, currentPage,pageSize);
        result.put("data", pageList);
        return result;
    }
    
    /**
     * 获取班级内的学生详情
     * @Title: studenrArrTableData
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/studenrArrTableData", method = RequestMethod.POST)
    public HashMap<String, Object> studenrArrTableData(Integer courseId,int currentPage,int pageSize){
        HashMap<String,Object> result = new HashMap<String,Object>();
        PageBounds<Student> pageList= courseService.studenrArrTableData(courseId, currentPage,pageSize);
        Course course = courseService.getCousreById(courseId);
        result.put("data", pageList);
        result.put("courseById", course);
        return result;
    }
    
    
    /**
     * 
     * 委任班主任/班长
     * @Title: appoinPeople
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/appoinPeople", method = RequestMethod.POST)
    public HashMap<String, Object> appoinPeople(Integer appoinId,Integer courseId,Integer state){
        HashMap<String,Object> result = new HashMap<String,Object>();
        Course course = new Course();
        if (state == 0) {
            course.setId(courseId);
            course.setTeacherId(appoinId);
            courseService.updateCourse(course);
        }else {
            course.setId(courseId);
            course.setStudentId(appoinId);
            courseService.updateCourse(course);
        }
        return result;
    }
    
    /**
     * 添加班级
     * @Title: addCourse
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public HashMap<String, Object> addCourse(Course course){
        HashMap<String,Object> result = new HashMap<String,Object>();
        courseService.addCourse(course);
        return result;
    }
    
    /**
     * 修改班级
     * @Title: updateCourse
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateCourse", method = RequestMethod.POST)
    public HashMap<String, Object> updateCourse(Course course){
        HashMap<String,Object> result = new HashMap<String,Object>();
        courseService.updateCourse(course);
        return result;
    }
    /**
     * @Title:delCourse
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delCourse", method = RequestMethod.POST)
    public HashMap<String, Object> delCourse(Integer[] ids){
        HashMap<String,Object> result = new HashMap<String,Object>();
        courseService.delCourse(Arrays.asList(ids));
        return result;
    }
}
