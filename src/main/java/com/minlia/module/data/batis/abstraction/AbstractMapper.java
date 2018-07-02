package com.minlia.module.data.batis.abstraction;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Notes: 由于 mybatis 的扫描机制，不可以将此抽象类命名为 mapper包名下
 */
public interface AbstractMapper<T> extends BaseMapper<T> {

  List<?> pageList(Page page, @Param("ew") Wrapper<T> wrapper);
}
