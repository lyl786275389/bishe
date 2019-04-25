package com.ssm.gbq.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.gbq.dao.NewsDao;
import com.ssm.gbq.dao.NoticeDao;
import com.ssm.gbq.model.News;
import com.ssm.gbq.model.Notice;
import com.ssm.gbq.service.NoticeService;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;

/**
 * 公告Service
 * @author 阿前
 * 2019年1月31日09:50:00
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    
    @Autowired
    private NoticeDao noticeDao;
    @Autowired 
    private NewsDao newsDao;

    public PageBounds<Notice> openNoticeTable(Notice notice, int currentPage, int pageSize) throws BusinessException {
        try {
            return noticeDao.openNoticeTable(notice,currentPage,pageSize);
        } catch (Exception e) {
            throw new BusinessException("分页查询失败！",e.getMessage());
        }
    }

    public void addNotice(Notice notice) throws BusinessException {
        try {
            notice.setCreateTime(new Date());
            //有多个被通知人
            if(null != notice.getManagerIds()){
                List<Integer> managerIds = Arrays.asList(notice.getManagerIds()); 
                List<Notice> newList = new ArrayList<Notice>();
                for(Integer ids:managerIds){
                    Notice newNotice = new Notice();
                    newNotice.setNotice(notice.getNotice());
                    newNotice.setCreateTime(notice.getCreateTime());
                    newNotice.setManagerId(ids);
                    newNotice.setState(notice.getState());
                    newNotice.setRose(notice.getRose());
                    newList.add(newNotice);
                }
                noticeDao.addNoticeItem(newList);
                //发送消息通知
                News news = new News();
                news.setCreatetime(new Date());
                news.setNews("您有一条新的通知");
                news.setManagerIds(managerIds);
                //批量添加
                newsDao.addNewsList(news);
              //当是申请上首页时，给管理员发送消息
                if (notice.getState() == 2) {
                    news.setCreatetime(new Date());
                    news.setNews("有新的公告申请");
                    news.setManagerId(1);
                    newsDao.addNews(news);
                }
            }else {
                //没有被通知人   
                noticeDao.addNotice(notice);
            }
            
        } catch (Exception e) {
            throw new BusinessException("添加失败！",e.getMessage());
        }
    }

    public void delNotice(List<Integer> ids) throws BusinessException {
        try {
            noticeDao.delNotice(ids);
        } catch (Exception e) {
            throw new BusinessException("删除失败！",e.getMessage());
        }    
    }

    public void arrNotice(List<Integer> ids, Integer state) throws BusinessException {
        try {
            noticeDao.arrNotice(ids,state);
        } catch (Exception e) {
            throw new BusinessException("处理失败！",e.getMessage());
        }    
    }

}
