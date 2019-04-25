package com.ssm.gbq.dao;

import java.util.List;

import com.ssm.gbq.model.Manager;
import com.ssm.gbq.model.vo.NameDto;

import gbq.ssm.utils.PageBounds;

public interface ManagerDao {
    //通过登录名查询
    Manager getManagerByName(String userName)throws Exception;
    /**
     * 获取管理员列表
     * @param manager
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageBounds<Manager> openManagerTable(Manager manager, int currentPage, int pageSize)throws Exception;
    
    /**
     * 添加
     * @param manager
     */
    void addManager(Manager manager)throws Exception;
    /**
     * 修改
     * @param manager
     * @throws Exception
     */
    void updateManger(Manager manager)throws Exception;
    /**
     * 通过id获取
     * @param id
     * @return
     */
    Manager getManagerById(Integer id);
    
    /**
     * 管理员与学生建立关联
     * @param managerId
     * @param studentId
     */
    void addManagerToStudent(Integer managerId, Integer studentId);
    
    /**
     * 管理员与老师建立关联
     * @param managerId
     * @param teacherId
     */
    void addManagerToTeacher(Integer managerId, Integer teacherId);
    /**
     * 查询管理员与老师关联
     * @param ids
     * @return
     */
    List<Integer> searchByTeacherId(List<Integer> ids);
    /**
     * 查询管理员与学生关联
     * @param ids
     * @return
     */
    List<Integer> searchByStudentId(List<Integer> ids);
    /**
     * 删除
     * @param ids
     */
    void delManager(List<Integer> ids);
    /**
     * 删除权限
     * @param ids
     */
    void delRoleByManagerId(List<Integer> ids);
    /**
     * 删除学生与管理员的关联
     * @param ids
     */
    void delManagerToStudent(List<Integer> ids);
    /**
     * 删除管理员与老师的关联
     * @param ids
     */
    void delManagerToTeacher(List<Integer> ids);
    /**
     * 查询全部名称
     * @return
     * @throws Exception
     */
    List<NameDto> getManagerName(String name)throws Exception;
    
    /**
     * 通过老师id查询管理员
     * @return
     * @throws Exception
     */
    Manager getManagerByTeacherId(Integer id)throws Exception;
    /**
     * 通过学生id查询管理员
     * @return
     * @throws Exception
     */
    Manager getManagerByStudentId(Integer id)throws Exception;
}
