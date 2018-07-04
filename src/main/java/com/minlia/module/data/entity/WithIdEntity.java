package com.minlia.module.data.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.cloud.stateful.generator.SnowFlakeUtil;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.DEFAULT,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE,
    creatorVisibility = JsonAutoDetect.Visibility.NONE)
@MappedSuperclass
public abstract class WithIdEntity<ID extends Serializable> implements Serializable {
  private static final long serialVersionUID = 1L;

  public  abstract ID getId();

//  public String toString() {
//    return ToStringBuilder.reflectionToString(getId());
//  }

  @Override
  @Transient
  @org.springframework.data.annotation.Transient
  @JSONField(serialize = false)
  public int hashCode() {
    return 17 + (isEmpty() ? 0 : getId().hashCode() * 31);
  }


  /**
   * 判断是否为空
   *
   * @return 是否为空
   */
  @Transient
  @org.springframework.data.annotation.Transient
  @JSONField(serialize = false)
  public boolean isEmpty() {
    return (null == getId());
  }



  /**
   * 判断是否相等
   *
   * @param obj 对象
   * @return 是否相等
   */
  @Override
  @Transient
  @org.springframework.data.annotation.Transient
  @JSONField(serialize = false)
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (isEmpty() || obj == null || !getClass().isAssignableFrom(obj.getClass())) {
      return false;
    }
    AbstractEntity entity = (AbstractEntity) obj;
    if (entity.isEmpty()) {
      return false;
    }
    return getId().equals(entity.getId());
  }


  @Override
  public String toString() {
    return this.getClass().getSimpleName()+"(id=" + this.getId() + ")";
  }

}