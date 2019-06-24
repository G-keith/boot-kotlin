package com.kotlin.mapper

import com.kotlin.entity.KotlinTest
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

/**
 *@author keith
 *@date 2019-06-10
 *@version 1.0
 */
@Repository
@Mapper
interface TestMapper{
     fun selectAll():List<KotlinTest>

     fun selectById(@Param("id")id:Int):KotlinTest

}