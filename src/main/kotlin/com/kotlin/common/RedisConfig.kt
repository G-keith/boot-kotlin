package com.kotlin.common

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
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration

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
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(Any::class.java)
        val pair = RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer)
        val defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair)
        defaultCacheConfig.entryTtl(Duration.ofSeconds(30))
        //初始化RedisCacheManager
        return RedisCacheManager(redisCacheWriter, defaultCacheConfig)
    }

    @Bean
    fun redisTemplate(factory: RedisConnectionFactory): StringRedisTemplate {
        val template = StringRedisTemplate(factory)
        template.setEnableTransactionSupport(true)
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(Any::class.java)
        val om = ObjectMapper()
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
        jackson2JsonRedisSerializer.setObjectMapper(om)
        template.valueSerializer = jackson2JsonRedisSerializer
        template.afterPropertiesSet()
        return template
    }
}