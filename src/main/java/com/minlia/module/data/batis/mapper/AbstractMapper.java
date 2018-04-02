package com.minlia.module.data.batis.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AbstractMapper<T> extends BaseMapper<T> {

  List<?> pageList(Page page, @Param("ew") Wrapper<T> wrapper);
}
