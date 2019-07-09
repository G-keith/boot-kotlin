package com.kotlin.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.*
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils
import java.util.concurrent.TimeUnit
import javax.annotation.Resource

/**
 *@author keith
 *@date 2019/7/8
 *@version 1.0
 */
@Component
class RedisTemplateUtils {

    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, Any>

    //redis字符串类型数据操作
    @Autowired
    lateinit var valueOperations: ValueOperations<String, Any>

    //对hash类型的数据操作
    @Autowired
    lateinit var hashOperations: HashOperations<String, String, Any>

    //对list类型的数据操作
    @Autowired
    lateinit var listOperations: ListOperations<String, Any>

    //对无序集合类型的数据操作
    @Autowired
    lateinit var setOperations: SetOperations<String, Any>

    /**
     * 默认过期时长，单位：秒
     */
    val DEFAULT_EXPIRE = (60 * 30).toLong()

    /**
     * 不设置过期时长
     */
    val NOT_EXPIRE: Long = -1

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time(秒) 时间
     * @return true:成功，false:失败
     */
    fun expire(key: String, time: Long): Boolean {
        return try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 0或-1代表永久有效，-2代表key不存在，大于0代表缓存过期剩余秒数
     */
    fun getExpire(key: String): Long {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS)
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    fun hasKey(key: String): Boolean {
        return try {
            redisTemplate.hasKey(key)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     * @return 返回删除成功数
     */
    fun delKey(vararg key: String): Long {
        return redisTemplate.delete(listOf(*key))
    }

    /**
     * 批量删除缓存
     * @param keys 多个key
     * @return 返回删除成功数
     */
    fun delKey(keys: MutableList<String>): Long {
        return redisTemplate.delete(keys)
    }

    /**
     * 模糊查询所有key值
     * @param key 键
     * @return 所有的key值
     */
    fun findKey(key: String): Set<String>? {
        return try {
            return redisTemplate.keys("*$key*")
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 普通缓存获取
     * @param key 键,不能为null
     * @return 值
     */
    operator fun get(key: String): Any? {
        return valueOperations.get(key)
    }

    /**
     * 普通缓存放入<并设置时间>
     * @param key 键
     * @param value 值
     * @param time 秒 time要大于0 如果time小于等于0 将不限时间
     */
    fun set(key: String, value: Any, time: Long = 0): Boolean {
        return try {
            valueOperations.set(key, value)
            if (time > 0) {
                expire(key, time)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 递增
     * @param key 键
     * @param delta 要增加几，默认1
     * @return 当前在redis中的数值
     */
    fun incr(key: String, delta: Long = 1): Long {
        if (delta < 0) {
            throw RuntimeException("递增因子必须大于0")
        }
        //!! 可能报空指针异常
        return valueOperations.increment(key, delta)!!
    }

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几，默认1
     * @return 当前在redis中的数值
     */
    fun decr(key: String, delta: Long = 1): Long {
        if (delta < 0) {
            throw RuntimeException("递减因子必须大于0")
        }
        //!! 可能报空指针异常
        return valueOperations.decrement(key, delta)!!
    }

    //================================Hash=================================

    /**
     * 获取hash值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return 值,null代表不存在
     */
    fun hget(key: String, item: String): Any? {
        return hashOperations.get(key, item)
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    fun hmget(key: String): MutableMap<String, Any> {
        return hashOperations.entries(key)
    }

    /**
     * 获取hashKey缓存长度
     * @param key 键
     * @return 缓存长度，key不存在返回0
     */
    fun hGetSize(key: String): Long {
        return hashOperations.size(key)
    }

    /**
     * 添加hash数据，并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time (秒) 时间,默认不设置时间
     * @return true成功 false失败
     */
    fun hmset(key: String, map: MutableMap<String, Any>, time: Long = 0): Boolean {
        return try {
            hashOperations.putAll(key, map)
            if (time > 0) {
                expire(key, time)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time(秒) 时间 默认不设置时间  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    fun hset(key: String, item: String, value: Any, time: Long = 0): Boolean {
        return try {
            hashOperations.put(key, item, value)
            if (time > 0) {
                expire(key, time)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 删除hash表中的值
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     * @return 返回删除成功多少项
     */
    fun hdel(key: String, vararg item: Any): Long {
        return hashOperations.delete(key, *item)
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    fun hHasKey(key: String, item: String): Boolean {
        return hashOperations.hasKey(key, item)
    }

    /**
     * hash递增 如果不存在,就会创建一个value为0的项
     * @param key 键
     * @param item 项
     * @param by 要增加几(大于0),默认加1.0
     * @return 递增后的值
     */
    fun hincr(key: String, item: String, by: Double = 1.0): Double {
        return hashOperations.increment(key, item, by)
    }

    /**
     * hash递减 如果不存在,就会创建一个value为0的项
     * @param key 键
     * @param item 项
     * @param by 要减少记(大于0) 默认减1.0
     * @return 递减后的值
     */
    fun hdecr(key: String, item: String, by: Double = 1.0): Double {
        return hashOperations.increment(key, item, -by)
    }

    //============================List=============================
    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束  0 到 -1代表所有值
     * @return 获取list缓存的内容
     */
    fun lGet(key: String, start: Int=0, end: Int=-1): List<Any>? {
        return try {
            listOperations.range(key, start.toLong(), end.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return list缓存的长度
     */
    fun lGetListSize(key: String): Long {
        return try {
            listOperations.size(key)!!
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    fun lGetIndex(key: String, index: Long): Any? {
        return try {
            listOperations.index(key, index)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param values 值或者数组(一个或多个)
     * @param time 时间
     * @return true成功 false失败
     */
    fun lSet(key: String,vararg values: Any, time: Long = 0): Boolean {
        return try {
            redisTemplate.opsForList().rightPushAll(key, *values)
            if (time > 0) {
                expire(key, time)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param values 值
     * @param time 时间
     * @return true成功 false失败
     */
//    fun lSet(key: String, values:MutableList<Any> , time: Long = 0): Boolean {
//        return try {
//            listOperations.rightPushAll(key,values)
//            if (time > 0) {
//                expire(key, time)
//            }
//            true
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }
//    }

    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return true  成功，false失败
     */
    fun lUpdateIndex(key: String, index: Long, value: Any): Boolean {
        return try {
            listOperations.set(key, index, value)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    fun lRemove(key: String, count: Long, value: Any): Long {
        return try {
            return listOperations.remove(key, count, value)!!
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    //=================set===================================
    /**
     * 根据key获取Set中的所有值
     * @param key 键
     * @return
     */
    fun sGet(key: String): Set<Any>? {
        return try {
            setOperations.members(key)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }

    /**
     * 根据value从一个set中查询,是否存在
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     */
    fun sHasKey(key: String, value: Any): Boolean {
        return try {
            setOperations.isMember(key, value)!!
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    }

    /**
     * 将数据放入set缓存
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    fun sSet(key: String, vararg values: Any): Long {
        return try {
            setOperations.add(key, *values)!!
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }

    }

    /**
     * 将set数据放入缓存
     * @param key 键
     * @param time(秒) 时间
     * @param values 值 可以是多个
     * @return 成功个数
     */
    fun sSetAndTime(key: String, time: Long, vararg values: Any): Long {
        return try {
            val count = setOperations.add(key, *values)
            if (time > 0) {
                expire(key, time)
            }
            count!!
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }

    }

    /**
     * 获取set缓存的长度
     * @param key 键
     * @return
     */
    fun sGetSetSize(key: String): Long {
        return try {
            setOperations.size(key)!!
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }

    }

    /**
     * 移除值为value的
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    fun setRemove(key: String, vararg values: Any): Long {
        return try {
            return setOperations.remove(key, *values)!!
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }

    }

}