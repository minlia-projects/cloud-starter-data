package com.minlia.module.data.batis.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.minlia.module.data.adapter.PageResponseBodyAdapter;
import com.minlia.module.data.batis.abstraction.AbstractMapper;
import com.minlia.module.data.body.AbstractQueryRequestBody;
import com.minlia.module.data.body.PageResponseBody;
import com.minlia.module.data.service.AbstractFindService;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


/**
 * @author will
 */
public abstract class AbstractBatisServiceImpl<MAPPER extends AbstractMapper<ENTITY>, ENTITY, QUERY extends AbstractQueryRequestBody> extends
    ServiceImpl<MAPPER, ENTITY> implements
    AbstractBatisService<ENTITY, QUERY>,
    AbstractFindService<ENTITY,QUERY>
{

  @Override
  public PageResponseBody<ENTITY> findAll(QUERY queryRequestBody,
      Pageable pageable) {
    com.baomidou.mybatisplus.plugins.Page<ENTITY> page = new com.baomidou.mybatisplus.plugins.Page<ENTITY>();

    //当jpa oneBasedIndexParameter时需要+1

    page.setCurrent(pageable.getPageNumber() + 1);
    page.setSize(pageable.getPageSize());

    page.setAscs(getSortPropertiesList(pageable, Direction.ASC));
    page.setDescs(getSortPropertiesList(pageable, Direction.DESC));
    com.baomidou.mybatisplus.plugins.Page<ENTITY> pagedResult = page
        .setRecords(this.baseMapper.selectPage(page, getSpecification(queryRequestBody)));
    return PageResponseBodyAdapter.adapt(pagedResult);
  }


  private List<String> getSortPropertiesList(Pageable pageable, Direction direction) {
    List<String> result = Lists.newArrayList();
    if (null != pageable.getSort()) {
      List<Sort.Order> list = IteratorUtils.toList(pageable.getSort().iterator());
      for (Sort.Order order : list) {
        Direction directionFound = order.getDirection();
        String property = order.getProperty();
        if (directionFound.equals(direction)) {
          result.add(property);
        }
      }
    }
    return result;
  }

}
