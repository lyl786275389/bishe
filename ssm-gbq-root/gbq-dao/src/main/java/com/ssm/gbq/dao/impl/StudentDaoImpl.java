package com.ssm.gbq.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.gbq.dao.StudentDao;
import com.ssm.gbq.mapper.StudentMapper;
import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.StudentExample;
import com.ssm.gbq.model.vo.CustomStudentDto;
import com.ssm.gbq.model.vo.NameDto;

import gbq.ssm.utils.PageBounds;

@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private StudentMapper studentMapper;

    public PageBounds<Student> openStudentTable(Student student, int currentPage, int pageSize) throws Exception {
        final int totalSize = studentMapper.countByExample(student);
        PageBounds<Student> pageBounds = new PageBounds<Student>(currentPage, totalSize, pageSize);
        List<Student> list = studentMapper.selectByLimitPage(student, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    public Student getStudentById(Integer id) throws Exception {
        return studentMapper.selectByPrimaryKey(id);
    }

    public void updateStudent(Student student)throws Exception {
        studentMapper.updateByPrimaryKeySelective(student);
    }

    public void addStudent(Student student) throws Exception {
        studentMapper.insertSelective(student);
    }

    public Student getStudentByPhone(String phone) throws Exception {
        StudentExample example = new StudentExample();
        example.createCriteria().andPhoneEqualTo(phone);
        List<Student> list = studentMapper.selectByExample(example);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    public void delStudentById(List<Integer> ids) throws Exception {
        studentMapper.deleteByPrimaryKey(ids);        
    }

    public void customStudent(CustomStudentDto customDto) throws Exception {
        studentMapper.customStudent(customDto);
    }

    public PageBounds<Student> openCustomTable(CustomStudentDto customDto, int currentPage, int pageSize)
            throws Exception {
        final int totalSize = studentMapper.countByCustom(customDto);
        PageBounds<Student> pageBounds = new PageBounds<Student>(currentPage, totalSize, pageSize);
        List<Student> list = studentMapper.selectByCustomPage(customDto, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    public PageBounds<CustomStudentDto> recordTableData(Integer studentId, int currentPage,int pageSize) throws Exception {
        final int totalSize = studentMapper.countToCustom(studentId);
        PageBounds<CustomStudentDto> pageBounds = new PageBounds<CustomStudentDto>(currentPage, totalSize,pageSize);
        List<CustomStudentDto> list = studentMapper.selectToCustomPage(studentId, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    public void customDel(List<Integer> ids) throws Exception {
        studentMapper.customDel(ids);
    }

    public List<NameDto> serchStudentByName(String studentName) throws Exception {
        return studentMapper.serchStudentByName(studentName);
    }

    public List<Student> getAllStudent(){
        StudentExample example = new StudentExample();
        return studentMapper.selectByExample(example);
    }

    public List<Student> getStudentByCourseId(Integer courseId) throws Exception {
        StudentExample example = new StudentExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<Student> list = studentMapper.selectByExample(example);
        return list;
    }

    public List<CustomStudentDto> getCustomStudentDtos(Integer[] ids) throws Exception {
        return studentMapper.getCustomStudentDtos(Arrays.asList(ids));
    }


}
