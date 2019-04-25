package com.ssm.gbq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.gbq.dao.CourseDao;
import com.ssm.gbq.dao.NoticeDao;
import com.ssm.gbq.dao.StatisticDao;
import com.ssm.gbq.model.vo.NoticeDto;
import com.ssm.gbq.model.vo.StatisticDto;
import com.ssm.gbq.service.StatisticService;

import gbq.ssm.utils.BusinessException;


@Service
public class StatisticServiceImpl implements StatisticService{
    @Autowired
    private StatisticDao statisticDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private NoticeDao noticeDao;

    public StatisticDto StatisticAll() throws BusinessException {
        try {
            StatisticDto statisticAll = statisticDao.StatisticAll();
            Integer allCourse = courseDao.getALLCourse().size();
            statisticAll.setCourseCount(allCourse);
            return statisticAll;
        } catch (BusinessException e) {
            throw new BusinessException("查询失败！",e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NoticeDto> statisticAllNotice() throws BusinessException {
        try {
            return noticeDao.statisticAllNotice();
        } catch (BusinessException e) {
            throw new BusinessException("查询失败！",e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    public List<NoticeDto> statisticAllNotice1() throws BusinessException {
        try {
            return noticeDao.statisticAllNotice1();
        } catch (BusinessException e) {
            throw new BusinessException("查询失败！",e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    public List<NoticeDto> statisticAllNotice2() throws BusinessException {
        try {
            return noticeDao.statisticAllNotice2();
        } catch (BusinessException e) {
            throw new BusinessException("查询失败！",e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

}
