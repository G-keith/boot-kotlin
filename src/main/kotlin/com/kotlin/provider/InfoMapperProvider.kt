package com.kotlin.provider

import org.apache.ibatis.annotations.Param
import org.apache.ibatis.jdbc.SQL

/**
 *@author keith
 *@date 2019/7/10
 *@version 1.0
 */
class InfoMapperProvider {
    fun findInfoByName(@Param("name")name: String?,@Param("sex") sex: String?): String {
        return object : SQL() {
            init {
                SELECT("*")
                FROM("info")
                if (name != "" && name != null) {
                    WHERE("name like concat('%',#{name},'%')")
                }
                if (sex != "" && sex != null) {
                    WHERE("sex like concat('%',#{sex},'%')")
                }
                ORDER_BY("age desc","sex asc")
            }
        }.toString().also(::println)
    }
}