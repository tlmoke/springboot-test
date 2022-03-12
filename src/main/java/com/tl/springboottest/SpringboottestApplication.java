package com.tl.springboottest;

import java.util.List;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.tl.springboottest.environment.MyOtherConfig;
import com.tl.springboottest.environment.MySqlConfig;
import com.tl.springboottest.interceptors.MyInterceptor1;
import com.tl.springboottest.interceptors.MyInterceptor2;
import com.tl.springboottest.servlet.MyServlet1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ServletComponentScan // 方式二： 添加servlet 注册扫描，将自动注册添加了@WebServlet的类为serlvet
@EnableConfigurationProperties({MySqlConfig.class,MyOtherConfig.class})	// 配置加载读取自定义配置类
public class SpringboottestApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(SpringboottestApplication.class, args);
		// SpringApplication.run(SpringboottestApplication.class, new String[]{"hello,","tl"});
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

	/**
	 * 注册Servlet.不需要添加注解：@ServletComponentScan
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean regMyServlet1() {
		return new ServletRegistrationBean(new MyServlet1(), "/myServlet1/*");
	}

	/**
	 * 添加拦截器
	 * 不能拦截servlet
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 重写addInterceptors方法并为拦截器配置拦截规则
		registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
		registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**").excludePathPatterns("/freemarkerHello2");
		// 排除路径
		// registry.addInterceptor(new
		// MyInterceptor2()).addPathPatterns("/**").excludePathPatterns("/Hello");
		super.addInterceptors(registry);
	}
}
