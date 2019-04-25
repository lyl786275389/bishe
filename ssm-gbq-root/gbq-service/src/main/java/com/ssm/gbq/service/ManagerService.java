package com.ssm.gbq.service;

import java.util.List;

import com.ssm.gbq.model.Manager;
import com.ssm.gbq.model.vo.NameDto;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;

public interface ManagerService {
    /**
     * 验证账号密码
     * @param userName
     * @param password
     * @return
     */
    Manager checkManagerLogin(String userName, String password)throws BusinessException;
    /**
     * 获取当前登录人
     * @return
     * @throws BusinessException
     */
    Manager getCurrentManager()throws BusinessException;
    
    /**
     * 获取管理员列表
     * @param manager
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBounds<Manager> openManagerTable(Manager manager, int currentPage, int pageSize)throws BusinessException;
    /**
     * 修改
     * @param manager
     */
    void updateManger(Manager manager)throws BusinessException;
    /**
     * 通过id查询
     * @param id
     * @return
     */
    Manager getManagerById(Integer id)throws BusinessException;
    
    /**
     * 删除
     * @param asList
     */
    void delManager(List<Integer> asList)throws BusinessException;
    /**
     * 查询全部管理员名称
     * @return
     */
    List<NameDto> getManagerName(String name)throws BusinessException;
    /**
     * 查找学生关联的管理员
     * @param studentId
     * @return
     * @throws BusinessException
     */
    Manager getManagerByStudentId(Integer studentId)throws BusinessException;
    /**
     * 查找老师关联的管理员
     * @param teacherId
     * @return
     * @throws BusinessException
     */
    Manager getManagerByTeacherId(Integer teacherId)throws BusinessException;

}
