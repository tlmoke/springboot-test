package com.tl.springboottest;

import java.util.List;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SpringboottestApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(SpringboottestApplication.class, args);
	}

	// 第一种方式，重写configureMessageConverters，并将FastJsonConverter设置到系统中
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		converter.setFeatures(SerializerFeature.PrettyFormat);
		converters.add(converter);
		super.configureMessageConverters(converters);
	}

	// 第二种方法：注入beanHttpMessageConverters
	// @Bean
	// public HttpMessageConverters faMessageConverters() {
	// return new HttpMessageConverters(new FastJsonHttpMessageConverter());
	// }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");
		registry.addResourceHandler("/otherres/**").addResourceLocations("file:E:/图片/壁纸/");
		super.addResourceHandlers(registry);
	}
}
