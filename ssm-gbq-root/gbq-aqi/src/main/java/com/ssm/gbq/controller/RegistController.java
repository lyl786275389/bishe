package com.ssm.gbq.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.Teacher;
import com.ssm.gbq.model.vo.Userdto;
import com.ssm.gbq.service.StudentService;
import com.ssm.gbq.service.TeacherService;
/**
 * 注册
 * @author 阿前
 *
 */
@Controller
@RequestMapping("")
public class RegistController {
    
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    
    /**
     * 注册
     * @Title: getUserInfo
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/regist.zhtml", method = RequestMethod.POST)
    public HashMap<String, Object> getAllCourse(Userdto userdto,HttpServletRequest request,
            HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*"); 
        HashMap<String,Object> result = new HashMap<String,Object>();
        if (userdto.getIdentityId() == 0) {
            //注册老师
            Teacher teacher = new Teacher();
            teacher.setName(userdto.getName());
            teacher.setPhone(userdto.getPhone());
            teacher.setPassword(userdto.getPassword());
            teacher.setConfirmPwd(userdto.getConfirmPwd());
            teacher.setSubjectId(userdto.getSubjectId());
            teacherService.addTeacher(teacher);
        }else {
            //注册学生
            Student student = new Student();
            student.setName(userdto.getName());
            student.setPhone(userdto.getPhone());
            student.setPassword(userdto.getPassword());
            student.setConfirmPwd(userdto.getConfirmPwd());
            studentService.addStudent(student);
        }
        return result;
    }
}
