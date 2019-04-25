package com.ssm.gbq.service;

import gbq.ssm.utils.BusinessException;

public interface RoleService {

    /**
     * 查询用户权限
     * @param id
     * @return
     * @throws BusinessException
     */
    String[] getRoleByManagerId(Integer id)throws BusinessException;

}
