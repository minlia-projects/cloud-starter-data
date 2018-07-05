package com.minlia.module.data.endpoint;

import com.minlia.cloud.stateful.Responses;
import com.minlia.cloud.stateful.body.StatefulBody;
import com.minlia.cloud.stateful.body.impl.SuccessResponseBody;
import com.minlia.module.data.body.AbstractQueryRequestBody;
import com.minlia.module.data.service.AbstractConditionalService;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FunctionalInterface
public interface FindListByConditionEndpoint<ENTITY extends Serializable,  QUERY extends AbstractQueryRequestBody> {

  @Autowired
  public abstract AbstractConditionalService<ENTITY, QUERY> getConditionalService();

  /**
   * 使用  @Pretend(value = "**,-payload.items.code") 进行结果排除，不需要此字段在前端展示
   */
  @PostMapping(value = "/findList", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Find all by conditions with list result")
  public default ResponseEntity<StatefulBody<List<ENTITY>>> findList(
      @RequestBody QUERY requestBody) {
    return Responses.ok(SuccessResponseBody.builder()
        .payload(getConditionalService().findAllByCondition(requestBody)).build());
  }

}
