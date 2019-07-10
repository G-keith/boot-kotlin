package com.kotlin.case.function

/**
 * let，run，with，apply，和also
 *@author keith
 *@date 2019/7/10
 *@version 1.0
 */
/**
 * 由于范围函数本质上非常相似，因此理解它们之间的差异非常重要。每个范围函数有两个主要区别：
 * 引用上下文对象的方式
 * 返回值。
 */
/*  run，with并apply通过关键字将上下文对象称为lambda接收器this
    let,also通过关键字将上下文对象称为lambda接收器it
    apply和 also返回上下文对象
    let, run和 with返回lambda 表达式结果.
 */
class RangeFunction {

    //上下文对象可用作参数（it）。返回值是lambda结果。
    fun letDemo() {
        val numbers = mutableListOf("one", "two", "three", "four", "five")
//        val resultList = numbers.map { it.length }.filter { it > 3 }
//        if(resultList.isNotEmpty()){
//            println(resultList)
//        }
        //可缩写成
        numbers.map { it.length }.filter { it > 3 }.let {
            // and more function calls if needed
            if (it.isNotEmpty()) {
                println(it)
            }
        }
        //如果代码块包含it作为参数的单个函数，则可以使用方法reference（::）而不是lambda：
        numbers.map { it.length }.filter { it > 3 }.let(::println)

        //let通常用于仅使用非空值执行代码块。要对非null对象执行操作，请对其使用安全调用运算符?.，并let使用其lambda中的操作进行调用
        val str: String? = "Hello"
        //processNonNullString(str)       // compilation error: str can be null
        //不为空时才会执行let代码块
        val length = str?.let {
            println("let() called on $it")
            processNonNullString(it)      // OK: 'it' is not null inside '?.let { }'
            it.length
        }
        //使用的另一种情况let是引入局限变量，以提高代码可读性。要为上下文对象定义新变量，请将其名称作为lambda参数提供，以便可以使用它而不是默认值it
        val modifiedFirstItem = numbers.first().let { firstItem ->
            println("The first item of the list is '$firstItem'")
            if (firstItem.length >= 5) firstItem else "!$firstItem!"
        }.toUpperCase()
        println("First item after modifications: '$modifiedFirstItem'")
    }

    fun processNonNullString(str: String) {}

    //非扩展函数：上下文对象作为参数传递（this），但在lambda中，它可用作receiver（this）。返回值是lambda结果
    fun withDemo() {
        //1。我们建议with在不提供lambda结果的情况下调用上下文对象上的函数。在代码中，with可以读作“ 使用此对象，执行以下操作。
        val numbers = mutableListOf("one", "two", "three")
        with(numbers) {
            println("'with' is called with argument $this")
            println("It contains $size elements")
        }
        //另一个用例with是引入一个辅助对象，其属性或函数将用于计算值。
        val firstAndLast = with(numbers) {
            "The first element is ${first()}," +
                    " the last element is ${last()}"
        }
        println(firstAndLast)
    }

    //上下文对象可用作receiver（this）。返回值是lambda结果。
    fun runDemo() {
        val service = MultiportService("https://example.kotlinlang.org", 80)

        val result = service.run {
            port = 8080
            query(prepareRequest() + " to port $port")
        }

        // the same code written with let() function:
        val letResult = service.let {
            it.port = 8080
            it.query(it.prepareRequest() + " to port ${it.port}")
        }
        println(result)
        println(letResult)

        //除了调用接收器对象上的run之外，还可以将其用作非扩展函数。非扩展运行允许您在需要表达式的地方执行由多个语句组成的块。
        val hexNumberRegex = run {
            val digits = "0-9"
            val hexDigits = "A-Fa-f"
            val sign = "+-"

            Regex("[$sign]?[$digits$hexDigits]+")
        }

        for (match in hexNumberRegex.findAll("+1234 -FFFF not-a-number")) {
            println(match.value)
        }
    }

    //
    //上下文对象可用作接收器（this）。返回值是对象本身
    fun applyDemo() {
        val user = User().apply {
            name = "apply"
            age = 20
        }
        println(user)
    }

    //上下文对象可用作参数（it）返回值是对象本身
    fun also() {
        val numbers = mutableListOf("one", "two", "three")
        numbers.also { println("The list elements before adding new one: $it") }
            .add("four")
    }

}

fun main() {
}