package com.minlia.module.data.abstraction.endpoint;

import com.minlia.cloud.loggable.annotation.Loggable;
import com.minlia.cloud.stateful.Responses;
import com.minlia.cloud.stateful.body.StatefulBody;
import com.minlia.cloud.stateful.body.impl.SuccessResponseBody;
import com.minlia.module.data.body.AbstractQueryRequestBody;
import com.minlia.module.data.body.PageResponseBody;
import com.minlia.module.data.abstraction.service.ConditionalService;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FunctionalInterface
public interface FindPaginatedByConditionEndpoint<ENTITY extends Serializable,QUERY extends AbstractQueryRequestBody> {

  @Autowired
  public abstract ConditionalService<ENTITY, QUERY> getConditionalService();

  /**
   * 使用  @Pretend(value = "**,-payload.items.code") 进行结果排除，不需要此字段在前端展示
   */
  //TODO 添加权限点控制
  @Loggable
  @PostMapping(value = "/findPaginated", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Find all by conditions with paginated result")
  public default ResponseEntity<StatefulBody<PageResponseBody<ENTITY>>> findPaginated(
      @RequestBody QUERY requestBody,

      //由于自带的放出去接口不太清晰，需要定义一个，然后在service层进行Pageable转换
      @PageableDefault Pageable pageable) {
    return Responses.ok(SuccessResponseBody.builder()
        .payload(getConditionalService().findAllByCondition(requestBody, pageable)).build());
  }
}
