package com.minlia.module.data.jpa.service;


import com.minlia.module.data.adapter.PageResponseBodyAdapter;
import com.minlia.module.data.body.AbstractQueryRequestBody;
import com.minlia.module.data.body.PageResponseBody;
import com.minlia.module.data.interfaces.IRawService;
import com.minlia.module.data.jpa.abstraction.AbstractRepository;
import com.minlia.module.data.service.AbstractReadonlyService;
import java.io.Serializable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


/**
 * @author will
 * @since 2.0.3
 */
//JDK8函数式接口注解 仅能包含一个抽象方法
//@FunctionalInterface
public interface AbstractJpaService<ENTITY extends Serializable, ID extends Serializable, QUERY extends AbstractQueryRequestBody> extends
    //with find service support
    AbstractReadonlyService<ENTITY, QUERY>, IRawService<ENTITY, ID> {

  public AbstractRepository<ENTITY, ID> getJpaRepository();


  /**
   * 根据ID获取
   */
  @Override
  public default ENTITY findOne(ID id) {
    return getJpaRepository().findById(id).get();
  }

  /**
   * 保存
   */
  @Override
  public default ENTITY save(ENTITY entity) {
    return getJpaRepository().save(entity);
  }

  /**
   * 批量保存与修改
   */
  @Override
  public default Iterable<ENTITY> saveAll(Iterable<ENTITY> entities) {
    return getJpaRepository().saveAll(entities);
  }

  /**
   * 修改
   */
  @Override
  public default ENTITY update(ENTITY entity) {
    return getJpaRepository().saveAndFlush(entity);
  }


  /**
   * 根据Id删除
   */
  @Override
  public default Boolean deleteOne(ID id) {
    getJpaRepository().deleteById(id);
    return Boolean.TRUE;
  }


  /**
   * 批量删除
   */
  @Override
  public default Boolean deleteAll(Iterable<ID> ids) {
    for (ID id : ids) {
      getJpaRepository().deleteById(id);
    }
    return Boolean.TRUE;
  }

//
//  /**
//   * 清空数据库
//   */
//  public default Boolean deleteAll() {
//    getJpaRepository().deleteAll();
//    return Boolean.TRUE;
//  }


  /**
   * 统计总数
   */
  @Override
  public default Long count() {
    return getJpaRepository().count();
  }

  /**
   * ID是否存在
   */
  @Override
  public default Boolean exists(ID id) {
    return getJpaRepository().existsById(id);
  }

  //以下方法为 data 模块提供的功能


  /**
   * 搜索条件应该由后台服务控制，所以都在实现类里面进行条件组装
   */
  @Override
  public default PageResponseBody<ENTITY> findAll(QUERY queryRequestBody,
      Pageable pageable) {
    return PageResponseBodyAdapter.adapt(
        getJpaRepository().findAll(getFindAllSpecification(queryRequestBody), pageable));
  }

  public default PageResponseBody<ENTITY> findAll(Example<ENTITY> entityExample,Pageable pageable) {
    return PageResponseBodyAdapter.adapt(
        getJpaRepository().findAll(entityExample, pageable));
  }

  /**
   * 获取查询条件的结果数
   */
  @Override
  public default Long count(QUERY queryRequestBody) {
    return getJpaRepository().count(getCountSpecification(queryRequestBody));
  }

  /**
   * 根据条件查询是否存在此实体，存在返回TRUE, 不存在返回FALSE
   */
  @Override
  public default Boolean exists(QUERY queryRequestBody) {
    return getJpaRepository().count(getExistsSpecification(queryRequestBody)) > 0 ? Boolean.TRUE
        : Boolean.FALSE;
  }


  /**
   * 搜索条件应该由后台服务控制，所以都在实现类里面进行条件组装
   */
  public default Specification<ENTITY> getFindAllSpecification(
      QUERY queryRequestBody) {
    return null;
  }

  public default Specification<ENTITY> getExistsSpecification(
      QUERY queryRequestBody) {
    return null;
  }

  public default Specification<ENTITY> getCountSpecification(
      QUERY queryRequestBody) {
    return null;
  }


}
