package com.minlia.module.data;

import com.minlia.module.data.batis.DataBatisConfiguration;
import com.minlia.module.data.jpa.DataJpaConfiguration;
import com.minlia.module.data.jpa.config.DataJpaWebConfig;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Minlia Data Auto Configuration
 */

//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.minlia.*")

//@ComponentScan
@Configuration
//@ConditionalOnClass(com.minlia.cloud.autoconfiguration.MinliaCloudAutoConfiguration.class)
//@EnableTransactionManagement
public class DataAutoConfiguration {




  @Configuration
  @MapperScan(basePackages = {".**.mapper" })
  @Import(value = DataBatisConfiguration.class)
  public static class ImportDataBatisConfiguration{
  }


  /**
   * 2.0.0 之后需要启用此注解来进行分页支持
   */
  @Configuration
  @EnableSpringDataWebSupport
  @Import(value = {DataJpaWebConfig.class, DataJpaConfiguration.class})
  @EntityScan(basePackages = { ".**.entity","org.springframework.data.jpa.convert.threeten"})
  @EnableJpaRepositories(value = {".**.repository"}, considerNestedRepositories = true, transactionManagerRef = "jpaTransactionManager")
  @EnableJpaAuditing
  public static class ImportDataJpaConfiguration  extends WebMvcConfigurationSupport {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
      // 注册Spring data jpa pageable的参数分解器
      argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }
  }
}