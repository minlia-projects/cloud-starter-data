package com.minlia.module.data.batis.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.minlia.module.data.body.AbstractQueryRequestBody;
import com.minlia.module.data.interfaces.IRawService;
import com.minlia.module.data.service.AbstractFindService;
import java.io.Serializable;

/**
 * @author will
 * @since 2.0.3
 */
public interface AbstractBatisService<ENTITY extends Serializable, ID extends Serializable, QUERY extends AbstractQueryRequestBody> extends
    IService<ENTITY>,
    //with find service support
    AbstractFindService<ENTITY, QUERY>, IRawService<ENTITY, ID> {

  /**
   * 搜索条件应该由后台服务控制，所以都在实现类里面进行条件组装
   */
  public default EntityWrapper<ENTITY> getFindAllSpecification(QUERY queryRequestBody) {
    return null;
  }

  public default EntityWrapper<ENTITY> getExistsSpecification(
      QUERY queryRequestBody) {
    return null;
  }

  public default EntityWrapper<ENTITY> getCountSpecification(
      QUERY queryRequestBody) {
    return null;
  }


}
