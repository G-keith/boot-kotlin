package com.kotlin.case.muster

/**
 *@author keith
 *@date 2019/7/2
 *@version 1.0
 */
fun main() {
//    list()
//    list2()
//    list3()
//    list4()
//    list5()
//    list6()
//    list7()
//    list8()
    list9()
//    set()
//    map()

}

fun list() {
    // 只读
    listOf("one", "two", "three", "four").also {
        println("只读：$it")
    }
    //读写
    val s: MutableList<String> = mutableListOf("one", "two", "three", "four")
    s.add("five").also {
        println("读写：$s")
    }
    //基于索引初始化list
    List(3) { it * 2 }.also {
        println("初始化：$it")
    }
    //list集合复制
    val sourceList = mutableListOf(1, 2, 3)
    sourceList.toMutableList().also {
        it.add(4)
        println("Copy size: ${it.size}")
    }
    sourceList.toList().also {
        println("Read-only copy size: ${it.size}")
    }
    sourceList.add(4).also {
        println(sourceList)
    }

}

fun list2() {
    val sourceList = mutableListOf(1, 2, 3)
    val referenceList = sourceList
    referenceList.add(4)
    println("Source size: ${sourceList.size}")

    val sourceList2: MutableList<Int> = mutableListOf(1, 2, 3)//读写
    //指定了返回类型为只读
    val referenceList2: List<Int> = sourceList
    //referenceList2.add(4)            //compilation error
    sourceList2.add(4)
    println(referenceList2)

    //集合转换
    val numbers = setOf(1, 2, 3)
    println(numbers.map { it })
    //索引乘对应的值
    println(numbers.mapIndexed { idx, value -> value * idx })
    //对应值和长度
    val numbers2 = listOf("one", "two", "three", "four")
    println(numbers2.associateWith { it.length })
}
//集合筛选
fun list3() {
    //集合筛选
    val numbers = listOf("one", "two", "three", "four")
    val filterResults = mutableListOf<String>()  //destination object
    numbers.filterTo(filterResults) { it.length > 3 }
    println(filterResults)
    //实际上只用到了index，value没有用到，用下划线代替
    numbers.filterIndexedTo(filterResults) { index, _ -> index == 0 }
    println(filterResults)
}
//集合排序
fun list4(){
    /**
     * 对于某些操作，有两对函数用于执行相同的操作：一个就地应用该操作，另一个将结果作为单独的集合返回。
     * 例如，sort（）对可变集合进行排序，使其状态发生变化；
     * sorted（）创建一个新集合，其中按排序顺序包含相同的元素
     */
    val numbers = mutableListOf("one", "two", "three", "four")
    val sortedNumbers = numbers.sorted()
    println(numbers == sortedNumbers)  // false
    numbers.sort()
    println(numbers == sortedNumbers)  // true
}
//集合转换
fun list5(){
    val numbers = setOf(1, 2, 3)
    println(numbers.mapNotNull { if ( it == 2) null else it * 3 })
    println(numbers.mapIndexedNotNull { idx, value -> if (idx == 0) null else value * idx })

    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    val map= mutableMapOf<String,Int>()
    val map2= mutableListOf<Int>()
    numbersMap.mapTo(map2) {it.value}
    println(map2)
    numbersMap.mapValuesTo(map){it.value}
    println(map)
    println(numbersMap.mapKeys { it.key.toUpperCase() })
    println(numbersMap.mapValues { it.value + it.key.length })
}
//双路合并
fun list6(){
    //两个集合配对。大的集合最后元素被舍弃
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")
    println(colors zip animals)
    val twoAnimals = listOf("fox", "bear")
    println(colors.zip(twoAnimals))
    println(colors.zip(animals) { color, animal -> "The ${animal.capitalize()} is $color"})
    //解压
    val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
    println(numberPairs.unzip())
}
//关联
fun list7(){
    //使用集合元素做键
    listOf("one", "two", "three", "four").also {
        println(it.associateWith { it.length })
    }
    //使用集合元素做值
    val numbers = listOf("one", "two", "three", "four")
    //相同取最后一个元素
    println(numbers.associateBy { it.first().toUpperCase() })
    println(numbers.associateBy(keySelector = { it.first().toUpperCase() }, valueTransform = { it.length }))
    //使用集合元素做键值对
    val names = listOf("Alice Adams", "Brian Brown", "Clara Campbell")
    println(names.associate { name -> name.split(" ").let { it[0] to it[1] } })

}
//打平
fun list8(){
    //嵌套集合
    val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
    //返回嵌套集合里面所有元素
    println(numberSets.flatten())
    //flatMap --
}
//字符串表示
fun list9(){
    //jointostring（）基于提供的参数从集合元素生成单个字符串。
    val numbers = listOf("one", "two", "three", "four")
    println(numbers)
    println(numbers.joinToString())

    // jointo（）执行相同的操作，但将结果附加到给定的可附加对象
    val listString = StringBuffer("The list of numbers: ")
    numbers.joinTo(listString)
    println(listString)
    //加上开始结束和中间分割符
    val numbers2 = listOf("one", "two", "three", "four")
    println(numbers2.joinToString(separator = " | ", prefix = "start: ", postfix = ": end"))

    //最大展示数，多余的用什么代替
    val numbers3 = (1..100).toList()
    println(numbers3.joinToString(limit = 10, truncated = "<...>"))

    //元素转换
    val numbers4 = listOf("one", "two", "three", "four")
    println(numbers4.joinToString { "Element: ${it.toUpperCase()}"})
}
fun set() {
    //只读
    val numbers = setOf(1, 2, 3, 4)
    println(numbers)
    //读写
    val s = mutableSetOf<String>("a", "b", "c")
    s.add("d")
    println(s)
}

fun map() {
    //只读
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
    println(numbersMap)
    //读写
    val map = mutableMapOf("one" to 1, "two" to 2)
    // map.put("three", 3)
    map["three"] = 3
    map["one"] = 11
    println(map)
}