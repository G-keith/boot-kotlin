package com.kotlin.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.kotlin.entity.Info
import com.kotlin.mapper.InfoMapper
import com.kotlin.service.IInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
@Service
class InfoServiceImpl : ServiceImpl<InfoMapper, Info>(), IInfoService {

    @Autowired
    lateinit var infoMapper: InfoMapper

    override fun selectAll(): List<Info> {
        return infoMapper.selectList(null)
    }

    override fun selectById(id:Int):Info{
        return infoMapper.selectById(id)
    }

    override fun insertInfo(info: Info):Unit{
        infoMapper.insert(info)
    }

    override fun updateInfo(info: Info){
        infoMapper.updateById(info)
    }

    override fun deleteInfo(id:Int){
        infoMapper.deleteById(id)
    }
}