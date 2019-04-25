package com.ssm.gbq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.gbq.dao.NoticeDao;
import com.ssm.gbq.mapper.NoticeMapper;
import com.ssm.gbq.model.Notice;
import com.ssm.gbq.model.vo.NoticeDto;

import gbq.ssm.utils.PageBounds;

/**
 * 
 * @author 阿前
 * 2019年1月31日09:53:27
 */
@Repository
public class NoticeDaoImpl implements NoticeDao {
    
    @Autowired 
    private NoticeMapper noticeMapper;
    
    public PageBounds<Notice> openNoticeTable(Notice notice, int currentPage, int pageSize) throws Exception {
        final int totalSize = noticeMapper.countByExample(notice);
        PageBounds<Notice> pageBounds = new PageBounds<Notice>(currentPage, totalSize, pageSize);
        List<Notice> list = noticeMapper.selectByLimitPage(notice, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    public void addNoticeItem(List<Notice> newList) throws Exception {
        noticeMapper.addNoticeItem(newList);
    }

    public void addNotice(Notice notice) throws Exception {
        noticeMapper.insertSelective(notice);
    }

    public List<NoticeDto> statisticAllNotice() throws Exception {
        return noticeMapper.statisticAllNotice();
    }

    public List<NoticeDto> statisticAllNotice1() throws Exception {
        return noticeMapper.statisticAllNotice1();
    }

    public List<NoticeDto> statisticAllNotice2() throws Exception {
        return noticeMapper.statisticAllNotice2();
    }

    public void delNotice(List<Integer> ids)throws Exception {
        noticeMapper.deleteByPrimaryKey(ids);     
    }
    
    public void arrNotice(List<Integer> ids, Integer state) throws Exception {
        noticeMapper.arrNotice(ids,state);
    }
    
}
