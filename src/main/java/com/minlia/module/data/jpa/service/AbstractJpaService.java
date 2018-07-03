package com.minlia.module.data.jpa.service;


import com.minlia.module.data.adapter.PageResponseBodyAdapter;
import com.minlia.module.data.body.AbstractQueryRequestBody;
import com.minlia.module.data.body.PageResponseBody;
import com.minlia.module.data.jpa.abstraction.AbstractRepository;
import com.minlia.module.data.service.AbstractFindService;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


/**
 * @author will
 * @since 2.0.3
 */
//JDK8函数式接口注解 仅能包含一个抽象方法
//@FunctionalInterface
public interface AbstractJpaService<ENTITY, ID extends Serializable, QUERY extends AbstractQueryRequestBody> extends
    //with find service support
    AbstractFindService<ENTITY, QUERY> {

  public AbstractRepository<ENTITY, ID> getJpaRepository();

  /**
   * 搜索条件应该由后台服务控制，所以都在实现类里面进行条件组装
   */
  public Specification<ENTITY> getSpecification(
      QUERY queryRequestBody);

  /**
   * 搜索条件应该由后台服务控制，所以都在实现类里面进行条件组装
   */
  @Override
  public default PageResponseBody<ENTITY> findAll(QUERY queryRequestBody,
      Pageable pageable) {
    return PageResponseBodyAdapter.adapt(
        getJpaRepository().findAll(getSpecification(queryRequestBody), pageable));
  }

  /**
   * 根据ID获取
   */
  public default ENTITY get(ID id) {
    return getJpaRepository().getOne(id);
  }

  /**
   * 获取所有列表
   */
  public default List<ENTITY> getAll() {
    return getJpaRepository().findAll();
  }

  /**
   * 获取总数
   */
  public default Long getTotalCount() {
    return getJpaRepository().count();
  }

  /**
   * 保存
   */
  public default ENTITY save(ENTITY entity) {

    return getJpaRepository().save(entity);
  }

  /**
   * 修改
   */
  public default ENTITY update(ENTITY entity) {
    return getJpaRepository().saveAndFlush(entity);
  }

  /**
   * 批量保存与修改
   */
  public default Iterable<ENTITY> saveOrUpdateAll(Iterable<ENTITY> entities) {
    return getJpaRepository().saveAll(entities);
  }

  /**
   * 删除
   */
  public default void delete(ENTITY entity) {
    getJpaRepository().delete(entity);
  }

  /**
   * 根据Id删除
   */
  public default void delete(ID id) {
    getJpaRepository().deleteById(id);
  }

  /**
   * 批量删除
   */
  public default void delete(Iterable<ENTITY> entities) {
    getJpaRepository().deleteAll(entities);
  }

  /**
   * 清空缓存，提交持久化
   */
  public default void flush() {
    getJpaRepository().flush();
  }

  /**
   * 根据条件查询获取
   */
  public default List<ENTITY> findAll(Specification<ENTITY> spec) {
    return getJpaRepository().findAll(spec);
  }

  /**
   * 分页获取
   */
  public default org.springframework.data.domain.Page<ENTITY> findAll(Pageable pageable) {
    return getJpaRepository().findAll(pageable);
  }

  /**
   * 根据查询条件分页获取
   */
  public default org.springframework.data.domain.Page<ENTITY> findAll(Specification<ENTITY> spec,
      Pageable pageable) {
    return getJpaRepository().findAll(spec, pageable);
  }

  /**
   * 获取查询条件的结果数
   */
  public default long count(Specification<ENTITY> spec) {
    return getJpaRepository().count(spec);
  }
}
