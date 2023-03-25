package com.example.mapper;

import com.example.domian.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 刘苏义
 * @since 2023年03月25日
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
