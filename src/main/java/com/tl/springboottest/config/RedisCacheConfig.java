package com.tl.springboottest.config;

import java.lang.reflect.Method;
import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis 缓存配置;
 *
 * 注意：RedisCacheConfig 这里也可以不用继承：CachingConfigurerSupport，也就是直接一个普通的
 * Class 就好了；
 *
 * 这里主要我们之后要重新实现 key 的生成策略，只要这里修改 KeyGenerator，其它位置不用修改就生效
 * 了。
 *
 * 普通使用普通类的方式的话，那么在使用@Cacheable 的时候还需要指定 KeyGenerator 的名称;这样编码
 * 的时候比较麻烦。
 *
 * @author tl
 * @version v.0.1
 */
@Configuration
@EnableCaching // 启用缓存，这个注解很重要；
public class RedisCacheConfig extends CachingConfigurerSupport {
    private Duration timeToLive = Duration.ZERO;

    public void setTimeToLive(Duration timeToLive) {
        this.timeToLive = timeToLive;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        // 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(timeToLive)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();

        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }

    /**
     * 自定义 key.
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个 key,即使@Cacheable 中的 value 属性一
     * 样，key 也会不一样。
     */
    @Override
    public KeyGenerator keyGenerator() {
        System.out.println("RedisCacheConfig.keyGenerator()");
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                // This will generate a unique key of the class name, the method name
                // and all method parameters appended.
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                // sb.append(method.getName());
                for (Object obj : objects) {
                    sb.append(obj.toString());
                }
                System.out.println("keyGenerator=" + sb.toString());
                return sb.toString();
            }
        };
    }
}
