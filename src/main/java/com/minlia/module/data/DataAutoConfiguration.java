package com.minlia.module.data;

import com.minlia.module.data.batis.DataBatisConfiguration;
import com.minlia.module.data.jpa.DataJpaConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Minlia Data Auto Configuration
 */

//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.minlia.*")


@Configuration
//@ConditionalOnClass(com.minlia.cloud.autoconfiguration.MinliaCloudAutoConfiguration.class)
@EnableTransactionManagement
public class DataAutoConfiguration {




  @Configuration
  @MapperScan(basePackages = {"com.minlia.**.mapper", ".**.dao"})
  @Import(value = DataBatisConfiguration.class)
  public static class ImportDataBatisConfiguration{
  }

//  @Configuration
//  @Import(value = DataJpaConfiguration.class)
//  @EntityScan(basePackages = {"com.minlia.**.entity", ".**.entity","com.minlia.**.domain", ".**.domain", "org.springframework.data.jpa.convert.threeten"})
//  @EnableJpaRepositories(value = {".**.repository"}, considerNestedRepositories = true, transactionManagerRef = "jpaTransactionManager")
//  @EnableJpaAuditing
//  public static class ImportDataJpaConfiguration{
//  }
}