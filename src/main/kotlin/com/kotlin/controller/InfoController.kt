package com.kotlin.controller

import com.kotlin.entity.Info
import com.kotlin.service.IInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */

@RestController
@RequestMapping("/info")
class InfoController{

    @Autowired
    lateinit var iInfoService: IInfoService

    @GetMapping("/list")
    fun listInfo():List<Info>{
        return iInfoService.selectAll()
    }

    @GetMapping("/info")
    fun selectInfo(id:Int):Info{
        return iInfoService.selectById(id)
    }

    @PostMapping("/info")
    fun insertInfo(name:String,age:Int,sex:String?){
        val info=Info()
        info.name=name
        info.age=age
        info.sex=sex
        return iInfoService.insertInfo(info)
    }

    @PutMapping("/info")
    fun updateInfo(id:Int,name:String?,age:Int?,sex:String?){
        val info=Info()
        info.name= name!!
        info.age=age!!
        info.sex=sex
        info.id=id
        return iInfoService.updateInfo(info)
    }

    @DeleteMapping("/info")
    fun deleteInfo(id: Int){
        return iInfoService.deleteInfo(id)
    }

}