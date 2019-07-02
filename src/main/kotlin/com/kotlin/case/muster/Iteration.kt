package com.kotlin.case.muster

/**
 *@author keith
 *@date 2019/7/2
 *@version 1.0
 */
fun main() {
//    foo()
    foo2()
//    listIteration()
}

//迭代器
fun foo() {
    val numbers = listOf("one", "two", "three", "four")
    //创建迭代器
    val numbersIterator = numbers.iterator()
    while (numbersIterator.hasNext()) {
        println(numbersIterator.next())
    }
}

//可变迭代器,迭代过程中移除该元素
fun foo2(){
    val numbers = mutableListOf("one", "two", "three", "four")
    //可变迭代器
    val mutableIterator = numbers.listIterator()

    while (mutableIterator.hasNext()){
        val string=mutableIterator.next()
        if(string == "two"){
            //删除元素
            mutableIterator.remove()
            //添加
            mutableIterator.add("TWO")
        }
        if(string == "three"){
            //修改
            mutableIterator.set("THREE")
        }
    }
    println("After removal: $numbers")
}
//list迭代器
fun listIteration() {
    val numbers = listOf("one", "two", "three", "four")
    val listIterator = numbers.listIterator()
    while (listIterator.hasNext()) {
        print("Index：${listIterator.nextIndex()}")
        println(", value: ${listIterator.next()}")
    }
    while (listIterator.hasPrevious()){
        print("Index：${listIterator.previousIndex()}")
        println(", value: ${listIterator.previous()}")
    }
    println("Iterating backwards:")
}

