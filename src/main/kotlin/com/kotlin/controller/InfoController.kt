package com.kotlin.controller

import com.baomidou.mybatisplus.core.metadata.IPage
import com.kotlin.entity.Info
import com.kotlin.service.IInfoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
@Api(tags = ["个人信息"])
@RestController
@RequestMapping("/info")
class InfoController{

    @Autowired
    lateinit var iInfoService: IInfoService

    @GetMapping("/list")
    fun listInfo(current:Long,size:Long): IPage<Info> {
        return iInfoService.selectAll(current,size)
    }

    @GetMapping("/info")
    @ApiOperation(value = "根据主键id查询个人信息")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "int",required = true)
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