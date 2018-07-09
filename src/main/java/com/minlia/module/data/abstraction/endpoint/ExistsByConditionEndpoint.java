package com.minlia.module.data.abstraction.endpoint;

import com.minlia.cloud.loggable.annotation.Loggable;
import com.minlia.cloud.stateful.Responses;
import com.minlia.cloud.stateful.body.StatefulBody;
import com.minlia.cloud.stateful.body.WithResultBody;
import com.minlia.cloud.stateful.body.impl.SuccessResponseBody;
import com.minlia.module.data.body.AbstractQueryRequestBody;
import com.minlia.module.data.abstraction.service.ConditionalService;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FunctionalInterface
public interface ExistsByConditionEndpoint<ENTITY extends Serializable,   QUERY extends AbstractQueryRequestBody> {

  @Autowired
  public abstract ConditionalService<ENTITY, QUERY> getConditionalService();
  //TODO 添加权限点控制
  @Loggable
  @PostMapping(value = "/exists", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Exists by conditions")
  public default ResponseEntity<StatefulBody<WithResultBody<Boolean>>> exists(
      @RequestBody QUERY requestBody) {
    WithResultBody<Boolean> body = new WithResultBody();
    body.setResult(getConditionalService().existsByCondition(requestBody));
    return Responses.ok(SuccessResponseBody.builder()
        .payload(body).build());
  }


}
