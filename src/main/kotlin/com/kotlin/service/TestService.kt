package com.kotlin.service

import com.kotlin.entity.KotlinTest
import com.kotlin.mapper.TestMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *@author keith
 *@date 2019-06-10
 *@version 1.0
 */
@Service
class TestService {

    @Autowired
    lateinit var testMapper: TestMapper


    fun selectAll(): List<KotlinTest> {
        println("进入接口》》》》》")
        return testMapper.selectAll()
    }

    fun selectById(id: Int): KotlinTest {
        return testMapper.selectById(id)
    }
}