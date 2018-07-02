package com.minlia.module.data.entity;

import java.io.Serializable;

public abstract class WithIdEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  //TODO 初始化 ID 值为分布式 ID 生成器
  private Long id;//=String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}