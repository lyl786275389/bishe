package com.ssm.gbq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.gbq.dao.ManagerDao;
import com.ssm.gbq.mapper.ManagerMapper;
import com.ssm.gbq.model.Manager;
import com.ssm.gbq.model.ManagerExample;
import com.ssm.gbq.model.vo.NameDto;

import gbq.ssm.utils.PageBounds;

@Repository
public class ManagerDaoImpl implements ManagerDao{
    
    @Autowired
    private ManagerMapper managerMapper;

    public Manager getManagerByName(String username) throws Exception {
        ManagerExample example = new ManagerExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Manager> list = this.managerMapper.selectByExample(example);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    public PageBounds<Manager> openManagerTable(Manager manager, int currentPage, int pageSize) throws Exception {
        final int totalSize = managerMapper.countByExample(manager);
        PageBounds<Manager> pageBounds = new PageBounds<Manager>(currentPage, totalSize, pageSize);
        List<Manager> list = managerMapper.selectByLimitPage(manager, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    public void addManager(Manager manager) throws Exception {
        managerMapper.insertSelective(manager);
    }

    public void updateManger(Manager manager) throws Exception {
        managerMapper.updateByPrimaryKeySelective(manager);
    }

    public Manager getManagerById(Integer id) {
        return managerMapper.selectByPrimaryKey(id);
    }

    public void addManagerToStudent(Integer managerId, Integer studentId) {
        managerMapper.addManagerToStudent(managerId,studentId);
    }
    
    public void addManagerToTeacher(Integer managerId, Integer teacherId) {
        managerMapper.addManagerToTeacher(managerId,teacherId);
    }

    public List<Integer> searchByTeacherId(List<Integer> ids) {
        return managerMapper.searchByTeacherId(ids);
    }

    public List<Integer> searchByStudentId(List<Integer> ids) {
        return managerMapper.searchByStudentId(ids);
    }

    public void delManager(List<Integer> ids) {
        managerMapper.deleteByPrimaryKey(ids);
    }

    public void delRoleByManagerId(List<Integer> ids) {
        managerMapper.delRoleByManagerId(ids);
    }

    public void delManagerToStudent(List<Integer> ids) {
        managerMapper.delManagerToStudent(ids);
    }

    public void delManagerToTeacher(List<Integer> ids) {
        managerMapper.delManagerToTeacher(ids);
    }

    public List<NameDto> getManagerName(String name) throws Exception {
        return managerMapper.getManagerName(name);
    }

    public Manager getManagerByTeacherId(Integer id) throws Exception {
        return managerMapper.getManagerByTeacherId(id);
    }

    public Manager getManagerByStudentId(Integer id) throws Exception {
        return managerMapper.getManagerByStudentId(id);
    }
}
