package com.kotlin.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
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
        ApiImplicitParam(name = "current", value = "第几页", dataType = "long", defaultValue = "10"),
        ApiImplicitParam(name = "size", value = "每页几条", dataType = "long", defaultValue = "1"),
        ApiImplicitParam(name = "name", value = "姓名", dataType = "string", defaultValue = "1")
    )
    fun listInfo(current: Long, size: Long,name: String?): IPage<Info> {
        return iInfoService.selectAll(current, size,name)
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
        ApiImplicitParam(name = "sex", value = "性别", dataType = "string")
    )
    fun insertInfo(name: String, age: Int, sex: String?): Int {
        return Info().let {
            it.name = name
            it.age = age
            it.sex = sex
            iInfoService.insertInfo(it)
        }
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

    @GetMapping("/selectInfoById")
    @ApiOperation(value = "根据主键id查询个人信息-注解形式xml")
    @ApiImplicitParam(name = "id", value = "主键id", dataType = "int", required = true)
    fun selectInfoById(id: Int): Info {
        return iInfoService.findInfoById(id)
    }

    @GetMapping("/selectInfoByName")
    @ApiOperation(value = "根据姓名查询个人信息列表-注解形式xml")
    @ApiImplicitParams(
        ApiImplicitParam(name = "name", value = "名称", dataType = "string"),
        ApiImplicitParam(name = "sex", value = "性别", dataType = "string")
    )
    fun selectInfoByName(name: String?, sex: String?): List<Info> {
        return iInfoService.findInfoByName(name?.trim(), sex?.trim())
    }
}