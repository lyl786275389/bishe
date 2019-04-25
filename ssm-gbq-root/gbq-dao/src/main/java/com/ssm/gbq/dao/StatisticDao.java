package com.ssm.gbq.dao;

import com.ssm.gbq.model.vo.StatisticDto;

public interface StatisticDao {
    /**
     * 统计首页数量
     * @return
     * @throws Exception
     */
    StatisticDto StatisticAll()throws Exception;

}
