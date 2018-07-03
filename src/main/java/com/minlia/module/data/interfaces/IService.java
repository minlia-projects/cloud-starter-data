package com.minlia.module.data.interfaces;

import com.minlia.module.data.entity.WithIdEntity;
import java.io.Serializable;

/**
 * @author will
 */
public interface IService<ENTITY extends Serializable, ID extends Serializable> extends
    IRawService<ENTITY, ID> {

}
