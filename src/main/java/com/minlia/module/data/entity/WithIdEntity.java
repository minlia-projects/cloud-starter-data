package com.minlia.module.data.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.DEFAULT,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE,
    creatorVisibility = JsonAutoDetect.Visibility.NONE)
@MappedSuperclass
@Data

public abstract class WithIdEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty
  private Long id;//=String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());;

//  public Long getId() {
//    return id;
//  }
//
//  public void setId(Long id) {
//    this.id = id;
//  }

}