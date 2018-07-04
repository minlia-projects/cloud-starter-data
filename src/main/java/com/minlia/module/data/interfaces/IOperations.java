package com.minlia.module.data.interfaces;

import java.io.Serializable;

public interface IOperations<ENTITY extends Serializable, ID extends Serializable> {

  // find - one

  ENTITY findOne(final ID id);

  // save

  ENTITY save(final ENTITY resource);

  // save all

  Iterable<ENTITY> saveAll(Iterable<ENTITY> resources);

  // update

  ENTITY update(final ENTITY resource);

  // delete

  Boolean deleteOne(final ID id);

  // delete all

  Boolean deleteAll(Iterable<ID> ids);

  // count

  Long count();

  Boolean exists(final ID id);


}
