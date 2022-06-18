package com.work.demo.common.config;

import com.work.demo.common.aop.PerformanceInterceptor;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author 刘小真
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.work.demo.mapper")
public class MyBatisPlusConfig {

    private final static Logger logger = LoggerFactory.getLogger(MyBatisPlusConfig.class);

    /**
     * @description: 配置分页插件
     *
     * @author: gradual
     * @date: 2019/1/15 10:17
     * @param: []
     * @return: com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        logger.debug("注册分页插件");
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(-1);
        return paginationInterceptor;
    }

    /**
     * 数据源
     *
     * @return
     */
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    /**
     * 事物管理器
     *
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * @description: SQL执行效率插件
     *
     * @author: gradual
     * @date: 19-1-24 下午4:59
     * @param: []
     * @return: com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor
     */
    @Bean
    @Profile({"test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }


}
