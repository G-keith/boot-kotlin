package com.kotlin.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
class Info : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="主键id", name="id", required=true)
    var id: Int = 0

    @ApiModelProperty(value="姓名", name="name", required=true)
    var name: String = ""

    @ApiModelProperty(value="年龄", name="age", required=true)
    var age: Int = 0

    @ApiModelProperty(value="性别", name="sex")
    var sex: String? = null

    companion object {
        private const val serialVersionUID = 1L
    }

}