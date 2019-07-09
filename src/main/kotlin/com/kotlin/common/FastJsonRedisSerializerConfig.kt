package com.kotlin.common

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import org.springframework.data.redis.serializer.RedisSerializer
import java.nio.charset.Charset

/**
 *@author keith
 *@date 2019/7/9
 *@version 1.0
 */
class FastJsonRedisSerializerConfig<T>(private val clazz: Class<T>) : RedisSerializer<T> {
    private val charset = Charset.forName("utf-8")!!

    override fun serialize(t: T?): ByteArray? {
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).toByteArray(charset)
    }

    override fun deserialize(bt: ByteArray?): T? {
        if (bt == null || bt.isEmpty()) return null
        val str = String(bt, charset)
        return JSON.parseObject<T>(str, clazz)
    }
}