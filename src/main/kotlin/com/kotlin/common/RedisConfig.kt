package com.kotlin.common

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.cache.RedisCacheWriter
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.data.redis.core.SetOperations
import org.springframework.data.redis.core.ListOperations
import org.springframework.data.redis.serializer.StringRedisSerializer


/**
 *@author keith
 *@date 2019/7/5
 *@version 1.0
 */
@Configuration
@EnableCaching
class RedisConfig: CachingConfigurerSupport(){
    /**
     * redis的key生成策略
     * @return rediskey值
     */
    @Bean
    override fun keyGenerator(): KeyGenerator? {
        return KeyGenerator { target, method, params ->
            val sb = StringBuilder()
            sb.append(target.javaClass.name)
            sb.append(method.name)
            for (obj in params) {
                sb.append(obj.toString())
            }
            sb.toString()
        }
    }

    @Bean
    internal fun cacheManager(connectionFactory: RedisConnectionFactory): CacheManager {
        //初始化一个RedisCacheWriter
        val redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory)
        //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
        //val loader = this.javaClass.classLoader
        //  JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
        val fastJsonRedisSerializer = FastJsonRedisSerializer(Any::class.java)
        val pair = RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer)
        val defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair)
        defaultCacheConfig.entryTtl(Duration.ofSeconds(30))
        //初始化RedisCacheManager
        return RedisCacheManager(redisCacheWriter, defaultCacheConfig)
    }

    @Bean
    fun redisTemplate(factory: RedisConnectionFactory): RedisTemplate<String,Any> {
        val template= RedisTemplate<String, Any>()
        // 配置连接工厂
        template.setConnectionFactory(factory)
        template.setEnableTransactionSupport(true)
        //对象序列化-使用自定义FastJsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        val fastJsonRedisSerializer = FastJsonRedisSerializer(Any::class.java)
/*        val om = ObjectMapper()
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会抛出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
        fastJsonRedisSerializer.setObjectMapper(om)*/
        template.valueSerializer = fastJsonRedisSerializer
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.keySerializer=StringRedisSerializer()
        // 设置hash key 和value序列化模式
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = fastJsonRedisSerializer
        template.afterPropertiesSet()
        return template
    }

    /**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate
     * @return ValueOperations
     */
    @Bean
    fun valueOperations(redisTemplate: RedisTemplate<String, Any>): ValueOperations<String, Any> {
        return redisTemplate.opsForValue()
    }

    /**
     * 对hash类型的数据操作
     *
     * @param redisTemplate
     * @return HashOperations
     */
    @Bean
    fun hashOperations(redisTemplate: RedisTemplate<String, Any>): HashOperations<String, String, Any> {
        return redisTemplate.opsForHash()
    }

    /**
     * 对链表类型的数据操作
     *
     * @param redisTemplate
     * @return ListOperations
     */
    @Bean
    fun listOperations(redisTemplate: RedisTemplate<String, Any>): ListOperations<String, Any> {
        return redisTemplate.opsForList()
    }

    /**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate
     * @return SetOperations
     */
    @Bean
    fun setOperations(redisTemplate: RedisTemplate<String, Any>): SetOperations<String, Any> {
        return redisTemplate.opsForSet()
    }

    /**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate
     * @return ZSetOperations
     */
    @Bean
    fun zSetOperations(redisTemplate: RedisTemplate<String, Any>): ZSetOperations<String, Any> {
        return redisTemplate.opsForZSet()
    }
}