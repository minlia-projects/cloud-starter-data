package com.minlia.module.data.jpa.service;


import com.minlia.module.data.jpa.abstraction.AbstractRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


/**
 * @author rdteam
 */
//JDK8函数式接口注解 仅能包含一个抽象方法
@FunctionalInterface
public interface AbstractJpaService<E, ID extends Serializable> {

  public AbstractRepository<E, ID> getRepository();

  /**
   * 根据ID获取
   */
  public default E get(ID id) {
    return getRepository().getOne(id);
  }

  /**
   * 获取所有列表
   */
  public default List<E> getAll() {
    return getRepository().findAll();
  }

  /**
   * 获取总数
   */
  public default Long getTotalCount() {
    return getRepository().count();
  }

  /**
   * 保存
   */
  public default E save(E entity) {

    return getRepository().save(entity);
  }

  /**
   * 修改
   */
  public default E update(E entity) {
    return getRepository().saveAndFlush(entity);
  }

  /**
   * 批量保存与修改
   */
  public default Iterable<E> saveOrUpdateAll(Iterable<E> entities) {
    return getRepository().saveAll(entities);
  }

  /**
   * 删除
   */
  public default void delete(E entity) {
    getRepository().delete(entity);
  }

  /**
   * 根据Id删除
   */
  public default void delete(ID id) {
    getRepository().deleteById(id);
  }

  /**
   * 批量删除
   */
  public default void delete(Iterable<E> entities) {
    getRepository().deleteAll(entities);
  }

  /**
   * 清空缓存，提交持久化
   */
  public default void flush() {
    getRepository().flush();
  }

  /**
   * 根据条件查询获取
   */
  public default List<E> findAll(Specification<E> spec) {
    return getRepository().findAll(spec);
  }

  /**
   * 分页获取
   */
  public default org.springframework.data.domain.Page<E> findAll(Pageable pageable) {
    return getRepository().findAll(pageable);
  }

  /**
   * 根据查询条件分页获取
   */
  public default org.springframework.data.domain.Page<E> findAll(Specification<E> spec,
      Pageable pageable) {
    return getRepository().findAll(spec, pageable);
  }

  /**
   * 获取查询条件的结果数
   */
  public default long count(Specification<E> spec) {
    return getRepository().count(spec);
  }
}
