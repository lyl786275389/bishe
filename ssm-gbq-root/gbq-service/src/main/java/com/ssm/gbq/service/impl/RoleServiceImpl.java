package com.ssm.gbq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.gbq.dao.RoleDao;
import com.ssm.gbq.service.RoleService;

import gbq.ssm.utils.BusinessException;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao; 

    public String[] getRoleByManagerId(Integer id) throws BusinessException {
        try {
            return roleDao.getRoleByManagerId(id);
        } catch (Exception e) {
            throw new BusinessException("查询用户角色失败！",e.getMessage());
        }
    }

}
