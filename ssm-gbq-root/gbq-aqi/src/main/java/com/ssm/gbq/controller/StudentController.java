package com.ssm.gbq.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.gbq.model.Course;
import com.ssm.gbq.model.Manager;
import com.ssm.gbq.model.News;
import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.vo.CustomStudentDto;
import com.ssm.gbq.model.vo.NameDto;
import com.ssm.gbq.service.CourseService;
import com.ssm.gbq.service.ManagerService;
import com.ssm.gbq.service.NewService;
import com.ssm.gbq.service.StudentService;

import gbq.ssm.utils.PageBounds;

/**
 * 教师Controller
 * @author 阿前
 * 2019年1月14日10:12:59
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired 
    private StudentService studentService;
    @Autowired 
    private ManagerService managerService;
    @Autowired
    private CourseService cousreService;
    @Autowired
    private NewService newService;
    
    /**
     * 获取学生列表
     * @Title: openStudentTable
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/openStudentTable", method = RequestMethod.POST)
    public HashMap<String, Object> openStudentTable(Student student,int pageSize,int currentPage){
        HashMap<String,Object> result = new HashMap<String,Object>();
        PageBounds<Student> pageBounds = studentService.openStudentTable(student, currentPage,pageSize);
        result.put("data", pageBounds);
        return result;
    }
    
    /**
     * 添加学生
     * @Title: addStudent
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public HashMap<String, Object> addStudent(Student student){
        HashMap<String,Object> result = new HashMap<String,Object>();
        studentService.addStudent(student);
        return result;
    }
    
    /**
     * 修改学生信息
     * @Title: updateStudent
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public HashMap<String, Object> updateStudent(Student student){
        HashMap<String,Object> result = new HashMap<String,Object>();
        studentService.updateStudent(student);
        return result;
    }
    
    /**
     * 通过id删除学生
     * @Title: delStudentById
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/delStudentById", method = RequestMethod.POST)
    public HashMap<String, Object> delStudentById(Integer[] ids){
        HashMap<String,Object> result = new HashMap<String,Object>();
        studentService.delStudentById(Arrays.asList(ids));
        return result;
    }
    
    /**
     * 给学生评分
     * @Title: customStudent
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/customStudent", method = RequestMethod.POST)
    public HashMap<String, Object> customStudent(CustomStudentDto customDto){
        HashMap<String,Object> result = new HashMap<String,Object>();
        Manager currentManager = managerService.getCurrentManager();
        customDto.setTeacherId(currentManager.getId());
        studentService.customStudent(customDto);
        return result;
    }
    
    /**
     * 获取评分列表
     * @Title: openCustomTable
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/openCustomTable", method = RequestMethod.POST)
    public HashMap<String, Object> openCustomTable(CustomStudentDto customDto,int pageSize,int currentPage){
        HashMap<String,Object> result = new HashMap<String,Object>();
        PageBounds<Student> pageBounds = studentService.openCustomTable(customDto, currentPage,pageSize);
        result.put("data", pageBounds);
        return result;
    }
    
    /**
     * 获取评分详情
     * @Title: recordTableData
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/recordTableData", method = RequestMethod.POST)
    public HashMap<String, Object> recordTableData(Integer studentId,int currentPage,int pageSize){
        HashMap<String,Object> result = new HashMap<String,Object>();
        PageBounds<CustomStudentDto> customStudentDto= studentService.recordTableData(studentId, currentPage,pageSize);
        result.put("data", customStudentDto);
        return result;
    }
    
    /**
     * 删除不合理评价
     * @Title: delStudentById
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/customDel", method = RequestMethod.POST)
    public HashMap<String, Object> customDel(Integer[] ids,String customDelText){
        HashMap<String,Object> result = new HashMap<String,Object>();
        List<CustomStudentDto> customStudentDtos = studentService.getCustomStudentDtos(ids);
        List<Integer> managerId = new ArrayList<Integer>();
        for(CustomStudentDto dto : customStudentDtos){
            Integer teacherId = dto.getTeacherId();
            Manager manager1 = managerService.getManagerByTeacherId(teacherId);
            managerId.add(manager1.getId());
            Integer studentId = dto.getStudentId();
            Manager manager2 = managerService.getManagerByStudentId(studentId);
            managerId.add(manager2.getId());
        }
        News news = new News();
        news.setCreatetime(new Date());
        news.setManagerIds(managerId);
        news.setNews("评论不合理"+customDelText);
        newService.addNewsList(news);
        //删除
        studentService.customDel(Arrays.asList(ids));
        return result;
    }
    
    /**
     * 模糊查询学生名字
     * @Title: serchStudentByName
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/serchStudentByName", method = RequestMethod.POST)
    public HashMap<String, Object> serchStudentByName(String studentName){
        HashMap<String,Object> result = new HashMap<String,Object>();
        List<NameDto> students = studentService.serchStudentByName(studentName);
        result.put("students", students);
        return result;
    }
    
    /**
     * 查询登录老师任课班级下的学生
     * @Title: getStudentName
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/getStudentName", method = RequestMethod.POST)
    public HashMap<String, Object> getStudentName(Integer courseId){
        HashMap<String,Object> result = new HashMap<String,Object>();
        Manager currentManager = managerService.getCurrentManager();
        Course course = cousreService.getCousreByTeacherId(currentManager.getTeacherId());
        List<Student> students = studentService.getStudentByCourseId(course.getId());
        List<Manager> exManagers = new ArrayList<Manager>();
        for(Student stu:students){
            Manager manager = managerService.getManagerByStudentId(stu.getId());
            exManagers.add(manager);
        }
        result.put("managerNames", exManagers);
        return result;
    }
}
