package com.kotlin.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.io.Serializable

/**
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
class Info : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Int = 0

    var name: String = ""

    var age: Int = 0

    var sex: String? = null

    companion object {
        private const val serialVersionUID = 1L
    }

}