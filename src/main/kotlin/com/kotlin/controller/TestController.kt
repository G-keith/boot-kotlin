package com.kotlin.controller

import com.kotlin.entity.KotlinTest
import com.kotlin.service.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *@author keith
 *@date 2019-06-10
 *@version 1.0
 */
@RestController
@RequestMapping("/kotlin")
class TestController{

    @Autowired
    lateinit var testService: TestService

    @GetMapping("/selectAll")
    fun selectAll():List<KotlinTest>{
        return  testService.selectAll()
    }

    @GetMapping("/selectById")
    fun selectById(id:Int):KotlinTest{
        return testService.selectById(id)
    }
}