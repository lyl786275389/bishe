package com.ssm.gbq.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.gbq.model.Manager;
import com.ssm.gbq.model.vo.NameDto;
import com.ssm.gbq.service.ManagerService;
import com.ssm.gbq.service.NewService;
import com.ssm.gbq.service.RoleService;

import gbq.ssm.utils.PageBounds;
/**
 * 管理员Controller
 * @author 阿前
 * 2019年1月4日09:47:56
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired 
    private ManagerService managerService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private NewService newService;
    
    /**
     * 获取后台用户信息
     * @Title: getUserInfo
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public HashMap<String, Object> getUserInfo(){
        HashMap<String,Object> result = new HashMap<String,Object>();
        Manager currentManager = managerService.getCurrentManager();
        String[] roles =roleService.getRoleByManagerId(currentManager.getId());
        Integer count = newService.selectNewsCount(currentManager.getId());
        result.put("manager",currentManager);
        result.put("roles", roles);
        result.put("count", count);
        return result;
    }
    
    /**
     * 获取管理列表（超级管理员才有此权限）
     * @Title: openManagerTable
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/openManagerTable", method = RequestMethod.POST)
    public HashMap<String, Object> openManagerTable(Manager manager,int pageSize,int currentPage){
        HashMap<String,Object> result = new HashMap<String,Object>();
        PageBounds<Manager> pageBounds = managerService.openManagerTable(manager, currentPage,pageSize);
        result.put("pageBounds", pageBounds);
        return result;
    }
    
    /**
     * 通过id删除
     * @Title: delStudentById
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/delManager", method = RequestMethod.POST)
    public HashMap<String, Object> delManager(Integer[] ids){
        HashMap<String,Object> result = new HashMap<String,Object>();
        managerService.delManager(Arrays.asList(ids));
        return result;
    }
    
    /**
     * 查询全部管理员名称  除了超级管理员
     * @Title: delStudentById
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/getManagerName", method = RequestMethod.POST)
    public HashMap<String, Object> getManagerName(String name){
        HashMap<String,Object> result = new HashMap<String,Object>();
        List<NameDto> nameDto =managerService.getManagerName(name);
        result.put("managerNames", nameDto);
        return result;
    }

}
