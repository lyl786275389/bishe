package com.ssm.gbq.controller;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.gbq.model.Notice;
import com.ssm.gbq.service.NoticeService;

import gbq.ssm.utils.PageBounds;

/**
 * 公告通知
 * @author 阿前
 * 2019年1月31日09:38:41
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    
    /**
     * 获取公告通知列表
     * @Title: openNoticeTable
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/openNoticeTable", method = RequestMethod.POST)
    public HashMap<String, Object> openNoticeTable(Notice notice,int pageSize,int currentPage){
        HashMap<String,Object> result = new HashMap<String,Object>();
        PageBounds<Notice> pageBounds = noticeService.openNoticeTable(notice, currentPage,pageSize);
        result.put("pageBounds", pageBounds);
        return result;
    }
    
    /**
     * 添加公告
     * @Title: addNotice
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/addNotice", method = RequestMethod.POST)
    public HashMap<String, Object> addNotice(Notice notice){
        HashMap<String,Object> result = new HashMap<String,Object>();
        noticeService.addNotice(notice);
        return result;
    }
    /**
     * 删除公告
     * @Title: delNotice
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/delNotice", method = RequestMethod.POST)
    public HashMap<String, Object> delNotice(Integer[] ids){
        HashMap<String,Object> result = new HashMap<String,Object>();
        noticeService.delNotice(Arrays.asList(ids));
        return result;
    }
    
    /**
     * 处理公告
     * @Title: arrNotice
     * @param @return
     */
    @ResponseBody
    @RequestMapping(value = "/arrNotice", method = RequestMethod.POST)
    public HashMap<String, Object> arrNotice(Integer[] ids,Integer states){
        HashMap<String,Object> result = new HashMap<String,Object>();
        noticeService.arrNotice(Arrays.asList(ids),states);
        return result;
    }
}
