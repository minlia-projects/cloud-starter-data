package com.minlia.module.data.batis.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.minlia.module.data.body.AbstractQueryRequestBody;
import com.minlia.module.data.service.AbstractFindService;

/**
 * @author will
 * @since 2.0.3
 */
public interface AbstractBatisService<ENTITY,QUERY extends AbstractQueryRequestBody> extends IService<ENTITY>,
    //with find service support
    AbstractFindService<ENTITY,QUERY> {

  /**
   * 搜索条件应该由后台服务控制，所以都在实现类里面进行条件拼装
   */
  public EntityWrapper<ENTITY> getSpecification(QUERY queryRequestBody);

}
