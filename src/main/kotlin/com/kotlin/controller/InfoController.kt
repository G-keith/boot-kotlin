package com.kotlin.controller

import com.baomidou.mybatisplus.core.metadata.IPage
import com.kotlin.entity.Info
import com.kotlin.service.IInfoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import kotlin.random.Random

/**
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
@Api(tags = ["个人信息"])
@RestController
@RequestMapping("/info")
class InfoController {

    @Autowired
    lateinit var iInfoService: IInfoService

    @GetMapping("/list")
    @ApiOperation(value = "分页查询个人信息")
    @ApiImplicitParams(
        ApiImplicitParam(name = "current", value = "每页几条", dataType = "long", defaultValue = "10"),
        ApiImplicitParam(name = "size", value = "第几页", dataType = "long", defaultValue = "1")
    )
    fun listInfo(current: Long, size: Long): IPage<Info> {
        return iInfoService.selectAll(current, size)
    }

    @GetMapping("/info")
    @ApiOperation(value = "根据主键id查询个人信息")
    @ApiImplicitParam(name = "id", value = "主键id", dataType = "int", required = true)
    fun selectInfo(id: Int): Info {
        return iInfoService.selectById(id)
    }


    @PostMapping("/info")
    @ApiOperation(value = "插入个人信息")
    @ApiImplicitParams(
        ApiImplicitParam(name = "name", value = "名称", dataType = "string", required = true),
        ApiImplicitParam(name = "age", value = "年龄", dataType = "int", required = true),
        ApiImplicitParam(name = "sex", value = "性别", dataType = "string", required = true)
    )
    fun insertInfo(name: String, age: Int, sex: String?) {
        val info = Info()
        info.name = name
        info.age = age
        info.sex = sex
        return iInfoService.insertInfo(info)
    }

    @PutMapping("/info")
    @ApiOperation(value = "更新个人信息")
    fun updateInfo(info: Info) {
        return iInfoService.updateInfo(info)
    }

    @DeleteMapping("/info")
    @ApiOperation(value = "删除个人信息")
    @ApiImplicitParam(name = "主键id", value = "id", required = true, dataType = "int")
    fun deleteInfo(id: Int) {
        return iInfoService.deleteInfo(id)
    }

}