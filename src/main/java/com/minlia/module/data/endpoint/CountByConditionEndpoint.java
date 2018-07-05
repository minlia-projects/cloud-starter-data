package com.minlia.module.data.endpoint;

import com.minlia.cloud.stateful.Responses;
import com.minlia.cloud.stateful.body.StatefulBody;
import com.minlia.cloud.stateful.body.WithResultBody;
import com.minlia.cloud.stateful.body.impl.SuccessResponseBody;
import com.minlia.module.data.body.AbstractQueryRequestBody;
import com.minlia.module.data.service.AbstractConditionalService;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FunctionalInterface
public interface CountByConditionEndpoint<ENTITY extends Serializable, QUERY extends AbstractQueryRequestBody> {

  @Autowired
  public abstract AbstractConditionalService<ENTITY, QUERY> getConditionalService();

  @PostMapping(value = "/count", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Count by conditions")
  public default ResponseEntity<StatefulBody<WithResultBody<Long>>> count(
      @RequestBody QUERY requestBody) {
    WithResultBody<Long> body = new WithResultBody();
    body.setResult(getConditionalService().countByCondition(requestBody));
    return Responses.ok(SuccessResponseBody.builder()
        .payload(body).build());
  }
}
