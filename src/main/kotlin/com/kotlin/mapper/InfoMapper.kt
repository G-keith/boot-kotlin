package com.kotlin.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.kotlin.entity.Info
import com.kotlin.provider.InfoMapperProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.SelectProvider
import org.springframework.stereotype.Repository

/**
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
@Repository
interface InfoMapper : BaseMapper<Info> {

    @Select("select * from info where id=#{id}")
    fun findInfoById(@Param("id") id: Int): Info

    @SelectProvider(type = InfoMapperProvider::class, method = "findInfoByName")
    fun findInfoByName(@Param("name") name: String?, @Param("sex") sex: String?): List<Info>
}