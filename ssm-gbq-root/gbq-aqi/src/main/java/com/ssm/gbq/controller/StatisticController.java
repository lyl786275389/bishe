package com.ssm.gbq.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.gbq.model.vo.NoticeDto;
import com.ssm.gbq.model.vo.StatisticDto;
import com.ssm.gbq.service.StatisticService;

/**
 * StatisticController
 * @author 阿前
 * 2019年1月30日11:39:47
 */
@Controller
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;
    
    /**
     * 首页统计数量
    * @Title: StatisticAll
    * @param @return
     */
    @ResponseBody
    @RequestMapping("/statisticAll")
    public HashMap<String, Object> StatisticAll(){
        HashMap<String,Object> result = new HashMap<String, Object>();
        StatisticDto statisticDto = statisticService.StatisticAll();
        result.put("statisticDto", statisticDto);
        return result;
    }
    
    
    /**
     * 首页公告
    * @Title: statisticAllNotice
    * @param @return
     */
    @ResponseBody
    @RequestMapping("/statisticAllNotice")
    public HashMap<String, Object> statisticAllNotice(){
        HashMap<String,Object> result = new HashMap<String, Object>();
        List<NoticeDto> list = statisticService.statisticAllNotice();
        List<NoticeDto> list1 = statisticService.statisticAllNotice1();
        List<NoticeDto> list2 = statisticService.statisticAllNotice2();
        result.put("notices", list);
        result.put("notices1", list1);
        result.put("notices2", list2);
        return result;
    }
    
}
