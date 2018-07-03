package com.minlia.module.data.interfaces;

import com.minlia.module.data.entity.WithIdEntity;
import java.io.Serializable;

public abstract class AbstractRawService<ENTITY extends WithIdEntity,ID extends Serializable>  implements IRawService<ENTITY,ID> {

}
