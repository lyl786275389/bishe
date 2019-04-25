package com.ssm.gbq.service;

import java.util.List;

import com.ssm.gbq.model.vo.NoticeDto;
import com.ssm.gbq.model.vo.StatisticDto;

import gbq.ssm.utils.BusinessException;

public interface StatisticService {
    /**
     * 统计首页数量
     * @return
     * @throws BusinessException
     */
    StatisticDto StatisticAll()throws BusinessException;
    /**
     * 首页公告
     * @return
     * @throws BusinessException
     */
    List<NoticeDto> statisticAllNotice()throws BusinessException;
    List<NoticeDto> statisticAllNotice1()throws BusinessException;
    List<NoticeDto> statisticAllNotice2()throws BusinessException;

}
