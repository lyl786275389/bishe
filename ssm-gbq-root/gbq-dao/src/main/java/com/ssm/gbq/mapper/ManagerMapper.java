package com.ssm.gbq.mapper;

import com.ssm.gbq.model.Manager;
import com.ssm.gbq.model.ManagerExample;
import com.ssm.gbq.model.vo.NameDto;
import com.ssm.gbq.model.vo.StatisticDto;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManagerMapper {
    int countByExample(@Param("example")Manager manager);

    int deleteByExample(ManagerExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Manager record);

    int insertSelective(Manager record);

    List<Manager> selectByExample(ManagerExample example);

    Manager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByExample(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);

    StatisticDto countByManager();

    List<Manager> selectByLimitPage(@Param("example")Manager manager,@Param("offset")int offset,@Param("limit")int pageSize);

    void addManagerToStudent(@Param("managerId")Integer managerId, @Param("studentId")Integer studentId);
    
    void addManagerToTeacher(@Param("managerId")Integer managerId, @Param("teacherId")Integer teacherId);

    List<Integer> searchByTeacherId(List<Integer> ids);

    List<Integer> searchByStudentId(List<Integer> ids);

    void delRoleByManagerId(List<Integer> ids);

    void delManagerToStudent(List<Integer> ids);

    void delManagerToTeacher(List<Integer> ids);

    List<NameDto> getManagerName(@Param("name")String name);

    Manager getManagerByTeacherId(Integer id);
    
    Manager getManagerByStudentId(Integer id);
}