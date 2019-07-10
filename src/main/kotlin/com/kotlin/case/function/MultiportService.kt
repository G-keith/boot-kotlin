package com.kotlin.case.function

/**
 *@author keith
 *@date 2019/7/10
 *@version 1.0
 */
class MultiportService(var url: String, var port: Int) {
    fun prepareRequest(): String = "Default request"
    fun query(request: String): String = "Result for query '$request'"
}