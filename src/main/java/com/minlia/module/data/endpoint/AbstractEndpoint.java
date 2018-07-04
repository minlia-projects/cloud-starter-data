package com.minlia.module.data.endpoint;

import com.minlia.cloud.stateful.Responses;
import com.minlia.cloud.stateful.body.StatefulBody;
import com.minlia.cloud.stateful.body.WithIdBody;
import com.minlia.cloud.stateful.body.WithIdItemBody;
import com.minlia.cloud.stateful.body.WithResultBody;
import com.minlia.cloud.stateful.body.impl.SuccessResponseBody;
import com.minlia.module.data.body.AbstractQueryRequestBody;
import com.minlia.module.data.body.PageResponseBody;
import com.minlia.module.data.interfaces.IRawService;
import com.minlia.module.data.service.AbstractReadonlyService;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author will
 * @since 2.0.3
 */
public abstract class AbstractEndpoint<ENTITY extends Serializable, ID extends Serializable, QUERY extends AbstractQueryRequestBody> {

  /**
   * 获取service
   */
  @Autowired
  public abstract IRawService<ENTITY, ID> getRawService();

  @Autowired
  public abstract AbstractReadonlyService<ENTITY, QUERY> getReadonlyService();

  @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Create")
  public ResponseEntity<StatefulBody<ENTITY>> create(@RequestBody ENTITY entity) {
    ENTITY created = getRawService().save(entity);
    return Responses.ok(SuccessResponseBody.builder()
        .payload(created).build());
  }

  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Find one by id")
  public ResponseEntity<StatefulBody<ENTITY>> findOne(@PathVariable ID id) {
    ENTITY entity = getRawService().findOne(id);
    return Responses.ok(SuccessResponseBody.builder()
        .payload(entity).build());
  }

  @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Update")
  public ResponseEntity<StatefulBody<ENTITY>> update(@RequestBody ENTITY entity) {
    ENTITY updated = getRawService().update(entity);
    return Responses.ok(SuccessResponseBody.builder()
        .payload(updated).build());
  }

  @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Delete")
  public ResponseEntity<StatefulBody<ENTITY>> delete(@RequestBody WithIdItemBody requestBody) {
    if (null != requestBody && null != requestBody.getItems()) {
      for (WithIdBody withIdBody : requestBody.getItems()) {
        getRawService().deleteOne((ID) withIdBody.getId());
      }
    }
    return Responses.noContent(SuccessResponseBody.builder().build());
  }

  @DeleteMapping(value = "/delete/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Delete one")
  public ResponseEntity<StatefulBody<ENTITY>> deleteOne(@PathVariable ID id) {
    getRawService().deleteOne(id);
    return Responses.noContent(SuccessResponseBody.builder().build());
  }


  @PostMapping(value = "/count", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Count")
  public ResponseEntity<StatefulBody<WithResultBody<Long>>> count(@RequestBody QUERY requestBody) {
    WithResultBody<Long> body = new WithResultBody();
    body.setResult(getReadonlyService().count(requestBody));
    return Responses.ok(SuccessResponseBody.builder()
        .payload(body).build());
  }

  @PostMapping(value = "/exists", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Exists")
  public ResponseEntity<StatefulBody<WithResultBody<Boolean>>> exists(@RequestBody QUERY requestBody) {
    WithResultBody<Boolean> body = new WithResultBody();
    body.setResult(getReadonlyService().exists(requestBody));
    return Responses.ok(SuccessResponseBody.builder()
        .payload(body).build());
  }


  /**
   * 使用  @Pretend(value = "**,-payload.items.code") 进行结果排除，不需要此字段在前端展示
   */
  @PostMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Find all")
  public ResponseEntity<StatefulBody<PageResponseBody<ENTITY>>> findAll(@RequestBody QUERY requestBody,
      @PageableDefault Pageable pageable) {
    return Responses.ok(SuccessResponseBody.builder()
        .payload(getReadonlyService().findAll(requestBody, pageable)).build());
  }

}