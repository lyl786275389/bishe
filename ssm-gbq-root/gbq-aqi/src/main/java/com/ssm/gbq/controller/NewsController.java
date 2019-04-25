package com.ssm.gbq.controller;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.gbq.model.Manager;
import com.ssm.gbq.model.News;
import com.ssm.gbq.service.ManagerService;
import com.ssm.gbq.service.NewService;

import gbq.ssm.utils.PageBounds;

/**
 * 消息Controller
 * @author 阿前
 * 2019年2月11日18:05:27
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    
    @Autowired
    private NewService newService;
    @Autowired
    private ManagerService managerService;
    /**
     * 获取消息列表
     * @Title: openNewsTable
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/openNewsTable", method = RequestMethod.POST)
    public HashMap<String, Object> openNewsTable(News news,int pageSize,int currentPage){
        HashMap<String,Object> result = new HashMap<String,Object>();
        Manager currentManager = managerService.getCurrentManager();
        news.setManagerId(currentManager.getId());
        PageBounds<News> pageBounds = newService.openNewsTable(news, currentPage,pageSize);
        newService.updateNewState(news);
        result.put("data", pageBounds);
        return result;
    }
    
    /**
     * 删除消息
     * @Title: delNews
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/delNews", method = RequestMethod.POST)
    public HashMap<String, Object> delNews(Integer[] ids){
        HashMap<String,Object> result = new HashMap<String,Object>();
        newService.delNews(Arrays.asList(ids));
        return result;
    }
}
