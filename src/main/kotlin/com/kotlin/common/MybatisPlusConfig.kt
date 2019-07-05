package com.kotlin.common

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.kotlin.service.*.mapper*")
class MybatisPlusConfig{

    /**
     * 分页配置
     */
    @Bean
    fun paginationInterceptor():PaginationInterceptor{
        return PaginationInterceptor()
    }
}