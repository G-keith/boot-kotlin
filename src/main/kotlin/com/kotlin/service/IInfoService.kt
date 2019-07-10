package com.kotlin.service

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.service.IService
import com.kotlin.entity.Info

/**
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
interface IInfoService : IService<Info> {

    fun selectAll(current: Long, size: Long,name: String?): IPage<Info>

    fun selectById(id: Int): Info

    fun updateInfo(info: Info)

    fun deleteInfo(id: Int)

    fun insertInfo(info: Info): Int

    fun findInfoById(id: Int): Info

    fun findInfoByName(name: String?, sex: String?): List<Info>
}