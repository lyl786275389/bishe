package com.ssm.gbq.dao;

public interface RoleDao {
    /**
     * 查询用户角色
     * @param id
     * @return
     * @throws Exception
     */
    String[] getRoleByManagerId(Integer id)throws Exception;

    void addRole(Integer id, Integer roleId)throws Exception;

}
