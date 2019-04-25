package com.ssm.gbq.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.gbq.model.Course;
import com.ssm.gbq.model.Teacher;
import com.ssm.gbq.model.enums.SubjectEnum;
import com.ssm.gbq.model.vo.ClassEnumDto;
import com.ssm.gbq.service.CourseService;
import com.ssm.gbq.service.TeacherService;

import gbq.ssm.utils.PageBounds;

/**
 * 教师Controller
 * @author 阿前
 * 2019年1月7日09:47:56
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired 
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    
    /**
     * 获取教师列表
     * @Title: openTeacherTable
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/openTeacherTable", method = RequestMethod.POST)
    public HashMap<String, Object> openTeacherTable(Teacher teacher,int pageSize,int currentPage){
        HashMap<String,Object> result = new HashMap<String,Object>();
        PageBounds<Teacher> pageBounds = teacherService.openTeacherTable(teacher, currentPage,pageSize);
        result.put("data", pageBounds);
        return result;
    }
    
    /**
     * 修改教师信息
     * @Title: updateTeacher
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateTeacher", method = RequestMethod.POST)
    public HashMap<String, Object> updateTeacher(Teacher teacher){
        HashMap<String,Object> result = new HashMap<String,Object>();
        teacherService.updateTeacher(teacher);
        return result;
    }
    
    /**
     * 获取全部课程信息/获取全部班级信息
     * @Title: getAllClass
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllClass", method = RequestMethod.POST)
    public HashMap<String, Object> getAllClass(){
        HashMap<String,Object> result = new HashMap<String,Object>();
        //获取全部课程信息
        List<SubjectEnum> enums = SubjectEnum.getClassEnum();
        //此处不知道是啥原因直接返回  枚举集合前台获取的到的只有元素名，因此我把它放到一个新的对象里重新返回
        List<ClassEnumDto> dtos = new ArrayList<>();
        for(SubjectEnum e : enums){
            ClassEnumDto enumDto = new ClassEnumDto();
            enumDto.setId(e.getId());
            enumDto.setName(e.getName());
            dtos.add(enumDto);
        }
        
        //获取全部班级信息
        List<Course> listcourse = courseService.getALLCourse();
        result.put("clasies", dtos);
        result.put("courses", listcourse);
        return result;
    }
    
    /**
     * 添加教师信息
     * @Title: addTeacher
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    public HashMap<String, Object> addTeacher(Teacher teacher){
        HashMap<String,Object> result = new HashMap<String,Object>();
        teacherService.addTeacher(teacher);
        return result;
    }
    
    /**
     * 通过id删除老师
     * @Title: delTeacherById
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/delTeacherById", method = RequestMethod.POST)
    public HashMap<String, Object> delTeacherById(Integer[] ids){
        HashMap<String,Object> result = new HashMap<String,Object>();
        teacherService.delTeacherById(Arrays.asList(ids));
        return result;
    }

    /**
     * 获取全部老师
     * @Title: getAllTeacher
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllTeacher", method = RequestMethod.POST)
    public HashMap<String, Object> getAllTeacher(){
        HashMap<String,Object> result = new HashMap<String,Object>();
        List<Teacher> list = teacherService.getAllTeacher();
        result.put("teachers", list);
        return result;
    }
}
