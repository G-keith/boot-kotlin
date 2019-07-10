package com.kotlin.service.impl

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.kotlin.entity.Info
import com.kotlin.mapper.InfoMapper
import com.kotlin.service.IInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper



/**
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
@Service
class InfoServiceImpl : ServiceImpl<InfoMapper, Info>(), IInfoService {

    @Autowired
    lateinit var infoMapper: InfoMapper

    override fun selectAll(current: Long, size: Long,name: String?): IPage<Info> {
        val page = Page<Info>(current, size)
        val query=QueryWrapper<Info>()
        name?.let {
            query.like("name",name)
        }
        return infoMapper.selectPage(page, query)
    }

    override fun selectById(id: Int): Info {
        return infoMapper.selectById(id)
    }

    override fun insertInfo(info: Info): Int {
        return infoMapper.insert(info)
    }

    override fun updateInfo(info: Info) {
        infoMapper.updateById(info)
    }

    override fun deleteInfo(id: Int) {
        infoMapper.deleteById(id)
    }

    override fun findInfoById(id: Int): Info {
        return infoMapper.findInfoById(id)
    }

    override fun findInfoByName(name: String?, sex: String?): List<Info> {
        return infoMapper.findInfoByName(name, sex)
    }
}