package com.ssm.gbq.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.gbq.dao.StatisticDao;
import com.ssm.gbq.mapper.ManagerMapper;
import com.ssm.gbq.model.vo.StatisticDto;

@Repository
public class StatisticDaoImpl implements StatisticDao{
    @Autowired
    private ManagerMapper managerMapper;
    
    
    public StatisticDto StatisticAll() throws Exception {
        return managerMapper.countByManager();
    }

}
