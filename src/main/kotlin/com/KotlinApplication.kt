package com

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 *@author keith
 *@date 2019-06-10
 *@version 1.0
 */
@SpringBootApplication
@MapperScan("com.kotlin.mapper")
class KotlinApplication

fun main(args: Array<String>) {
    runApplication<KotlinApplication>(*args)
}
