package com.ssm.gbq.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.gbq.dao.CourseDao;
import com.ssm.gbq.dao.ManagerDao;
import com.ssm.gbq.dao.NewsDao;
import com.ssm.gbq.dao.TeacherDao;
import com.ssm.gbq.model.Course;
import com.ssm.gbq.model.Manager;
import com.ssm.gbq.model.News;
import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.Teacher;
import com.ssm.gbq.service.CourseService;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;
/**
 * 班级service
 * @author 阿前
 * 2019年1月11日10:56:23
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private NewsDao newsDao;
    
    public List<Course> getALLCourse() throws BusinessException {
        try {
            return courseDao.getALLCourse();
        } catch (Exception e) {
            throw new BusinessException("获取全部信息失败！",e.getMessage());
        }
    }

    public void updateCourse(Course course) throws BusinessException {
        try {
            if (course.getTeacherIds()!=null) {
                //建立班级与任课老师之间的关系
                teacherDao.delTeacherAndCourseByIds(Arrays.asList(course.getTeacherIds()));
                courseDao.courseToTeacher(course.getId(),Arrays.asList(course.getTeacherIds()));
            }
            if (course.getTeacherId() != null) {
                Manager manager = managerDao.getManagerByTeacherId(course.getTeacherId());
                News news = new News();
                news.setCreatetime(new Date());
                news.setManagerId(manager.getId());
                news.setNews("您被任命为"+course.getClassName()+"的班主任");
                newsDao.addNews(news);
            }
            if (course.getStudentId()!= null) {
                Manager manager = managerDao.getManagerByStudentId(course.getStudentId());
                News news = new News();
                news.setCreatetime(new Date());
                news.setManagerId(manager.getId());
                news.setNews("您被任命为"+course.getClassName()+"的班长");
                newsDao.addNews(news);
            }
            courseDao.updateCourse(course);
        } catch (Exception e) {
            throw new BusinessException("修改失败！",e.getMessage());
        }
    }

    public Course getCousreByTeacherId(Integer teacherId) throws BusinessException {
        try {
            return courseDao.getCousreByTeacherId(teacherId);
        } catch (Exception e) {
            throw new BusinessException("通过老师id查询失败！",e.getMessage());
        }
    }

    public PageBounds<Course> openCourseTable(Course course, int currentPage, int pageSize) throws BusinessException {
        try {
            return courseDao.getCousreByTeacherId(course,currentPage,pageSize);
        } catch (Exception e) {
            throw new BusinessException("分页查询失败！",e.getMessage());
        }
    }

    public PageBounds<Teacher> teacherArrTableData(Integer courseId, int currentPage, int pageSize)
            throws BusinessException {
        try {
            return courseDao.teacherArrTableData(courseId,currentPage,pageSize);
        } catch (Exception e) {
            throw new BusinessException("获取老师详情失败！",e.getMessage());
        }
    }

    public PageBounds<Student> studenrArrTableData(Integer courseId, int currentPage, int pageSize)
            throws BusinessException {
        try {
            return courseDao.studenrArrTableData(courseId,currentPage,pageSize);
        } catch (Exception e) {
            throw new BusinessException("获取班内学生详情失败！",e.getMessage());
        }
    }

    public void addCourse(Course course) throws BusinessException {
        try {
            courseDao.addCourse(course);
            if (course.getTeacherIds() != null) {
                //建立班级与任课老师之间的关系
                courseDao.courseToTeacher(course.getId(),Arrays.asList(course.getTeacherIds()));
            }
        } catch (Exception e) {
            throw new BusinessException("添加失败！",e.getMessage());
        }
    }

    public void delCourse(List<Integer> ids) throws BusinessException {
        try {
            courseDao.delCourse(ids);
            //删除班级与老师之间的关联
            courseDao.delCourseToTeacher(ids);
        } catch (Exception e) {
            throw new BusinessException("删除失败！",e.getMessage());
        }
    }

    public Course getCousreById(Integer courseId) throws BusinessException {
        try {
            return courseDao.getCousreById(courseId);
        } catch (Exception e) {
            throw new BusinessException("通过id查询失败！",e.getMessage());
        }
    }

}
