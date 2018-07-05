package com.minlia.module.data.endpoint;

import com.minlia.cloud.stateful.Responses;
import com.minlia.cloud.stateful.body.StatefulBody;
import com.minlia.cloud.stateful.body.impl.SuccessResponseBody;
import com.minlia.module.data.interfaces.IRawService;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FunctionalInterface
public interface UpdateableEndpoint<ENTITY extends Serializable, ID extends Serializable> {


  @Autowired
  public abstract IRawService<ENTITY, ID> getRawService();

  @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Update")
  public default ResponseEntity<StatefulBody<ENTITY>> update(@RequestBody ENTITY entity) {
    ENTITY updated = getRawService().update(entity);
    return Responses.ok(SuccessResponseBody.builder()
        .payload(updated).build());
  }
}
