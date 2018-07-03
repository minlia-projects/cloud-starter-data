package com.minlia.module.data.service;

import com.minlia.module.data.body.AbstractQueryRequestBody;
import com.minlia.module.data.body.PageResponseBody;
import org.springframework.data.domain.Pageable;

/**
 * An abstract find service with paginated result
 *
 * @author will
 * @since 2.0.3
 */
public interface AbstractFindService<ENTITY, QUERY extends AbstractQueryRequestBody> {

  /**
   * paginated returning according query request body and pageable
   */
  public PageResponseBody<ENTITY> findAll(QUERY queryRequestBody, Pageable pageable);

  /**
   *
   * @param queryRequestBody
   * @return
   */
  public Long count(QUERY queryRequestBody);

  /**
   *
   * @param queryRequestBody
   * @return
   */
  public Boolean exists(QUERY queryRequestBody);
}
