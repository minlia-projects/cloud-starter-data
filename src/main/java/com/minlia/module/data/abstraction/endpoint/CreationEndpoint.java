package com.minlia.module.data.abstraction.endpoint;


import com.minlia.cloud.loggable.annotation.Loggable;
import com.minlia.cloud.stateful.Responses;
import com.minlia.cloud.stateful.body.StatefulBody;
import com.minlia.cloud.stateful.body.impl.SuccessResponseBody;
import com.minlia.module.data.interfaces.IRawService;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FunctionalInterface
public interface CreationEndpoint<ENTITY extends Serializable, ID extends Serializable> {

  @Autowired
  public abstract IRawService<ENTITY, ID> getRawService();


  public default void beforeCreate(ENTITY entity){
    //in abstract method, there's nothing to do
    //implement this method if in demand
  }

  public default void afterCreated(ENTITY entity){
    //in abstract method, there's nothing to do
    //implement this method if in demand
  }

  //TODO 添加权限点控制
  @Loggable
  @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Create")
  public default ResponseEntity<StatefulBody<ENTITY>> create(@RequestBody ENTITY entity) {

    beforeCreate(entity);
    ENTITY created = getRawService().save(entity);
    afterCreated(created);
    return Responses.created(SuccessResponseBody.builder()
        .payload(created).build());
  }
}
