package com.kotlin.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.kotlin.entity.Info
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

/**
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
@Repository
interface InfoMapper:BaseMapper<Info> {
}