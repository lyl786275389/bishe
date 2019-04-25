package com.ssm.gbq.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ssm.gbq.model.Manager;
import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.Teacher;
import com.ssm.gbq.model.vo.CurrentDto;
import com.ssm.gbq.service.ManagerService;
import com.ssm.gbq.service.StudentService;
import com.ssm.gbq.service.TeacherService;

import gbq.ssm.utils.CommonConstants;
import gbq.ssm.utils.FileEntity;
import gbq.ssm.utils.UploadPictureUtil;

/**
 * 当前登录人Controller
 * @author 阿前
 * 2019年1月4日09:47:56
 */
@Controller
@RequestMapping("/current")
public class CurrentController {
    @Autowired
    private ManagerService managerService;
    @Autowired 
    private TeacherService teacherService;
    @Autowired 
    private StudentService studentService;
    /**
     * 修改当前用户的手机号和名字
     * @Title: updateCurrent
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateCurrent", method = RequestMethod.POST)
    public HashMap<String, Object> updateCurrent(CurrentDto currentDto){
        HashMap<String,Object> result = new HashMap<String,Object>();
        Manager currentManager = managerService.getCurrentManager();
        Manager manager =new Manager();
        manager.setId(currentManager.getId());
        manager.setName(currentDto.getName());
        manager.setPhone(currentDto.getPhone());
        manager.setUsername(currentDto.getPhone());
        managerService.updateManger(manager);
        String username = currentManager.getUsername();
        if (currentDto.getAccess().equals("teacher")) {
            Teacher exteacher = teacherService.getTeacherByPhone(username);
            Teacher teacher = new Teacher();
            teacher.setId(exteacher.getId());
            teacher.setName(currentDto.getName());
            teacher.setPhone(currentDto.getPhone());
            teacherService.updateTeacher(exteacher);
        }else {
            Student exstudent = studentService.getStudentByPhone(username);
            Student student = new Student();
            student.setId(exstudent.getId());
            exstudent.setName(currentDto.getName());
            exstudent.setPhone(currentDto.getPhone());
            studentService.updateStudent(exstudent);
        }
        return result;
    }
    
    
    /**
     * 修改当前用户的密码
     * @Title: updateCurrent1
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateCurrent1", method = RequestMethod.POST)
    public HashMap<String, Object> updateCurrent1(CurrentDto currentDto){
        HashMap<String,Object> result = new HashMap<String,Object>();
        Manager currentManager = managerService.getCurrentManager();
        currentManager.setConfirmPwd(currentDto.getConfirmPwd());
        currentManager.setOldPassword(currentDto.getOldpassword());
        currentManager.setPassword(currentDto.getPassword());
        managerService.updateManger(currentManager);
        return result;
    }
    
    /**
     * 移除当前管理员头像
     * @Title: updateTeacher
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/removeCurrentPic", method = RequestMethod.POST)
    public HashMap<String, Object> removeCurrentPic(Manager manager){
        HashMap<String,Object> result = new HashMap<String,Object>();
        boolean deleteFile = UploadPictureUtil.deleteFile(manager.getHeadPic());
        if (deleteFile == true) {
            manager.setHeadPic(null);
            managerService.updateManger(manager);
        }
        return result;
    }
    
    /**
     * 上传当前管理员头像
     * @Title: updateTeacher
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateCurrentPic", method = RequestMethod.POST)
    public HashMap<String, Object> upload(@RequestParam(value = "file", required = false) CommonsMultipartFile multipartFile,
                         @RequestHeader(value =CommonConstants.USER_ID) Integer userId,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        //生产环境绝对不允许设置为“*”
        response.setHeader("Access-Control-Allow-Origin", "*"); 
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8") ;
        FileEntity entity = UploadPictureUtil.CheckuploadPicture(multipartFile);
        if(null !=entity) {
            Manager currentManager = managerService.getManagerById(userId);
            if(StringUtils.isNotBlank(currentManager.getHeadPic())) {
                //此处我将图片上传设置为一张，所以这里不会走这条语句
                currentManager.setHeadPic(currentManager.getHeadPic()+","+entity.getTitleOrig());
            }else {
                currentManager.setPassword(null);
                currentManager.setHeadPic(entity.getTitleOrig());
            }
            managerService.updateManger(currentManager);
            result.put("picName",entity.getTitleOrig());
            return result;
        }else {
            return null;
        }
    }
}
