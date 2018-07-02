package com.minlia.module.data.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public class DataJpaConfiguration {

  @Autowired
  LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;

  @Primary
  @Bean
  public JpaTransactionManager jpaTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager
        .setEntityManagerFactory(localContainerEntityManagerFactoryBean.getObject());
    return transactionManager;
  }
}
