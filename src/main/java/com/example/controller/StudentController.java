package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domian.Student;
import com.example.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 刘苏义
 * @since 2023年03月25日
 */
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    StudentMapper studentMapper;

    @GetMapping(value = "/insert")
    public void insert() {
        Student student = new Student();
        student.setId(3);
        student.setName("张三");
        student.setAge(35);
        studentMapper.insert(student);
    }
    @GetMapping(value = "/delete")
    public void delete(){
        Student student = new Student();
        student.setId(3);
        QueryWrapper entityWrapper=new QueryWrapper(student);
        studentMapper.delete(entityWrapper);
    }
    @GetMapping(value = "/update")
    public void update(){
        Student student = new Student();
        student.setId(2);
        //update的判断条件
        QueryWrapper entityWrapper=new QueryWrapper(student);
        //更新之后的对象
        student = new Student();
        student.setAge(1000);
        studentMapper.update(student,entityWrapper);
    }
    @GetMapping(value = "/selectAllById")
    public Student selectByTeacherName(int id){
        Student student = new Student();
        student.setId(id);
        QueryWrapper entityWrapper=new QueryWrapper(student);
        return studentMapper.selectOne(entityWrapper);
    }
    @GetMapping(value = "/selectAllByMap")
    public List<Student> selectAllByEntity(String name){
        Map<String,Object> hashMap=new HashMap<>();
        hashMap.put("name",name);
        return studentMapper.selectByMap(hashMap);
    }
    @GetMapping(value = "/selectCountByEntity")
    public long selectCount(String name){
        Student student=new Student();
        student.setId(1);
        student.setName(name);
        QueryWrapper<Student> entityWrapper=new QueryWrapper<>(student);
        return studentMapper.selectCount(entityWrapper);
    }
    @GetMapping(value = "/selectAllInPage")
    public  List<Student> selectAllInPage(int pageNumber,int pageSize){
        Page<Student> page =new Page<>(pageNumber,pageSize);
        QueryWrapper<Student> entityWrapper = new QueryWrapper<>();
        entityWrapper.ge("id", 1);
        Page<Student> studentPage = studentMapper.selectPage(page, entityWrapper);
        List<Student> students=new ArrayList<>();
        for(Student stu:studentPage.getRecords())
        {
            students.add(stu);
        }
        return students;
    }
    @GetMapping(value = "/selectInIdArr")
    public List<Student> selectInIdArr(){
        List<Integer> idList=new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        log.info(idList.toString());
        return studentMapper.selectBatchIds(idList);
    }
    @GetMapping(value = "/selectAllByWrapper1")
    public  List<Student> selectAllByWrapper1(){
        Map<String,Object> map=new HashMap<>();
        map.put("id",2);
        map.put("name","张三");
        QueryWrapper entity=new QueryWrapper();
        entity.allEq(map);
        return studentMapper.selectList(entity);
    }
    @GetMapping(value = "/selectAllByWrapper3")
    public List<Student> selectAllByWrapper3(){
        QueryWrapper entity=new QueryWrapper();
        entity.ne("name","张三");
        return studentMapper.selectList(entity);
    }
    @GetMapping(value = "/selectAllByWrapper2")
    public List<Student> selectAllByWrapper2(){
        QueryWrapper entity=new QueryWrapper();
        entity.eq("name","张三");
        return studentMapper.selectList(entity);
    }
    @GetMapping(value = "/selectAllByWrapper4")
    public  List<Student> selectAllByWrapper4(){
        QueryWrapper entity=new QueryWrapper();
        entity.gt("id",1);
        entity.le("id",7);
        entity.ne("age",3);
        entity.like("name","4");
        entity.notLike("name","3");
        entity.orderByAsc("id");
        return studentMapper.selectList(entity);
    }

}
