package com.minlia.module.data.abstraction.endpoint;

import com.minlia.cloud.loggable.annotation.Loggable;
import com.minlia.cloud.stateful.Responses;
import com.minlia.cloud.stateful.body.StatefulBody;
import com.minlia.cloud.stateful.body.impl.SuccessResponseBody;
import com.minlia.module.data.body.ToggleRequestBody;
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

  public default void beforeUpdate(ENTITY entity){
    //in abstract method, there's nothing to do
    //implement this method if in demand
  }

  public default void afterUpdated(ENTITY entity){
    //in abstract method, there's nothing to do
    //implement this method if in demand
  }


  //TODO 添加权限点控制
  @Loggable
  @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Update")
  public default ResponseEntity<StatefulBody<ENTITY>> update(@RequestBody ENTITY entity) {
    beforeUpdate(entity);
    ENTITY updated = getRawService().update(entity);
    afterUpdated(entity);
    return Responses.ok(SuccessResponseBody.builder()
        .payload(updated).build());
  }


  /**
   * 开关状态
   * 入参
   * 实体ID
   * 属性名称
   * 属性值
   *
   * @param toggleRequestBody
   * @return
   */
  //TODO 添加权限点控制
  @Loggable
  @PutMapping(value = "/toggle", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "toggle")
  public default ResponseEntity<StatefulBody<ENTITY>> toggle(@RequestBody ToggleRequestBody<ID> toggleRequestBody) {
    ENTITY updated = getRawService().toggle(toggleRequestBody);
    return Responses.ok(SuccessResponseBody.builder()
        .payload(updated).build());
  }
}
