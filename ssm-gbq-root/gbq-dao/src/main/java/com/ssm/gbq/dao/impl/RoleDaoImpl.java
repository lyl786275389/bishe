package com.ssm.gbq.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.gbq.dao.RoleDao;
import com.ssm.gbq.mapper.RoleMapper;

@Repository
public class RoleDaoImpl implements RoleDao{
    @Autowired
    private RoleMapper roleMapper;
    

    public String[] getRoleByManagerId(Integer id) throws Exception {
        return roleMapper.getRoleByManagerId(id); 
    }


    public void addRole(Integer managerId, Integer roleId) throws Exception {
        roleMapper.addRole(managerId,roleId);
    }

}
