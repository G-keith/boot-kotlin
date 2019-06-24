package com.kotlin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 *@author keith
 *@date 2019-06-10
 *@version 1.0
 */
@RestController
@RequestMapping("/kotlin")
class HelloWorldKotlin{

    @GetMapping("/hello")
    fun hello(vararg v:String):String{
        val join=StringJoiner("-")
        for(vs in v){
            join.add(vs)
        }
        println(join)
        return "Hello KOTLIN"
    }
}