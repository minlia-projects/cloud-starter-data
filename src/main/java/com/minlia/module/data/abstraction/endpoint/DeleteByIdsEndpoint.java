package com.minlia.module.data.abstraction.endpoint;

import com.minlia.cloud.loggable.annotation.Loggable;
import com.minlia.cloud.stateful.Responses;
import com.minlia.cloud.stateful.body.StatefulBody;
import com.minlia.cloud.stateful.body.WithIdBody;
import com.minlia.cloud.stateful.body.WithIdItemBody;
import com.minlia.cloud.stateful.body.impl.SuccessResponseBody;
import com.minlia.module.data.interfaces.IRawService;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FunctionalInterface
public interface DeleteByIdsEndpoint<ENTITY extends Serializable, ID extends Serializable> {

  @Autowired
  public abstract IRawService<ENTITY, ID> getRawService();
  //TODO 添加权限点控制
  @Loggable
  @DeleteMapping(value = "/deleteByIds", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Delete by ids")
  public default ResponseEntity<StatefulBody<ENTITY>> delete(
      @RequestBody WithIdItemBody<ID> requestBody) {
    if (null != requestBody && null != requestBody.getItems()) {
      for (WithIdBody withIdBody : requestBody.getItems()) {
        getRawService().deleteOne((ID) withIdBody.getId());
      }
    }
    return Responses.noContent(SuccessResponseBody.builder().build());
  }
}
