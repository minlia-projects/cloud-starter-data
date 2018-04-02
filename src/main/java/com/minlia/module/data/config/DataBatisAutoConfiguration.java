package com.minlia.module.data.config;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.plugins.SqlExplainInterceptor;
import com.minlia.module.data.scope.DataScopeInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Minlia Data Auto Configuration
 */

//@EnableAutoConfiguration
@ComponentScan(basePackages = "com.minlia.*")
@MapperScan(basePackages = {"com.minlia.**.mapper", ".**.dao"})

@Configuration
//@ConditionalOnClass(com.minlia.cloud.autoconfiguration.MinliaCloudAutoConfiguration.class)
@EnableTransactionManagement
public class DataBatisAutoConfiguration {


  /**
   * mybatis-plus SQL执行效率插件【生产环境可以关闭】
   */
  @Bean
  @ConditionalOnMissingBean
  @Profile("!production")
  public PerformanceInterceptor performanceInterceptor() {
    return new PerformanceInterceptor();
  }

  //    @Bean
//    public DimensionInterceptor dimensionInterceptor() {
//        return new DimensionInterceptor();
//    }
  @Bean
  @ConditionalOnMissingBean
  @Profile("!production")
  public SqlExplainInterceptor sqlExplainInterceptor() {
    return new SqlExplainInterceptor();
  }

  @Bean
  @ConditionalOnMissingBean
  public OptimisticLockerInterceptor optimisticLockerInterceptor() {
    return new OptimisticLockerInterceptor();
  }

  @Bean
  @ConditionalOnMissingBean
  public DataScopeInterceptor dataScopeInterceptor() {
    return new DataScopeInterceptor();
  }

  /**
   * mybatis-plus分页插件<br> 文档：http://mp.baomidou.com<br>
   */
  @Bean
  @ConditionalOnMissingBean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
//        /*
//         * 【测试多租户】 SQL 解析处理拦截器<br>
//         * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
//         */
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//        TenantSqlParser tenantSqlParser = new TenantSqlParser();
//        tenantSqlParser.setTenantHandler(new TenantHandler() {
//            @Override
//            public Expression getTenantId() {
//                return new LongValue(1L);
//            }
//
//            @Override
//            public String getTenantIdColumn() {
//                return "tenant_id";
//            }
//
//            @Override
//            public boolean doTableFilter(String tableName) {
//                // 这里可以判断是否过滤表
//                /*
//                if ("user".equals(tableName)) {
//                    return true;
//                }*/
//                return false;
//            }
//        });
//
//
//        sqlParserList.add(tenantSqlParser);
//        paginationInterceptor.setSqlParserList(sqlParserList);
//        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
//            @Override
//            public boolean doFilter(MetaObject metaObject) {
//                MappedStatement ms = PluginUtils.getMappedStatement(metaObject);
//                // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
//                if ("com.baomidou.springboot.mapper.UserDao.selectListBySQL".equals(ms.getId())) {
//                    return true;
//                }
//                return false;
//            }
//        });
    return paginationInterceptor;
  }

}