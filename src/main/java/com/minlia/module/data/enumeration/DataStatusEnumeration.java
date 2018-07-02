package com.minlia.module.data.enumeration;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "数据状态")
public enum DataStatusEnumeration implements WithStatusEnumeration<Integer> {

  @ApiModelProperty(value = "正常状态")
  NORMARL(1),
  @ApiModelProperty(value = "锁定状态")
  LOCKED(2),
  @ApiModelProperty(value = "删除状态")
  DELETED(3)
  ;


  private DataStatusEnumeration(  Integer status) {
    this.status = status;
  }


  private Integer status;

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Override
  public Integer getStatus() {
    return status;
  }
}
