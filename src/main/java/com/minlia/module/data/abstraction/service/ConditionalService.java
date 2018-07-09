package com.minlia.module.data.abstraction.service;

import com.minlia.module.data.body.AbstractQueryRequestBody;
import com.minlia.module.data.body.PageResponseBody;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * An abstract find service with paginated result
 *
 * @author will
 * @since 2.0.3
 */
public interface ConditionalService<ENTITY, QUERY extends AbstractQueryRequestBody> {

  /**
   * paginated returning according query request body and pageable
   */
  public PageResponseBody<ENTITY> findAllByCondition(QUERY queryRequestBody, Pageable pageable);

  /**
   * list returning according query request body and pageable
   * @param queryRequestBody
   * @return
   */
  public List<ENTITY> findAllByCondition(QUERY queryRequestBody);

  /**
   *
   * @param queryRequestBody
   * @return
   */
  public Long countByCondition(QUERY queryRequestBody);

  /**
   *
   * @param queryRequestBody
   * @return
   */
  public Boolean existsByCondition(QUERY queryRequestBody);

  /**
   *
   * @param queryRequestBody
   * @return
   */
  public Integer deleteByCondition(QUERY queryRequestBody);

}
