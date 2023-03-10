package com.loyalty.pkg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
//import com.loyalty.pkg.interceptors.AppInterceptor;

@Configuration
@ComponentScan(basePackages = {"com.loyalty.pkg"})
public class AppConfig extends WebMvcConfigurationSupport {

	 @Override
	    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("css/**","images/**", "js/**")
	                .addResourceLocations("classpath:/static/css/", "classpath:/static/images/", "classpath:/static/js/");
	    }
	    // End Of Resource Handler.

	    @Bean
	    public InternalResourceViewResolver viewResolver(){
	        InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();
	        jspViewResolver.setPrefix("/WEB-INF/jsp/");
	        jspViewResolver.setSuffix(".jsp");
	        jspViewResolver.setViewClass(JstlView.class);

	        return jspViewResolver;  
	    }
	    // End Of View Resolver.


//	    @Override
//	    protected void addInterceptors(InterceptorRegistry registry) {
//	        registry.addInterceptor(new AppInterceptor()).addPathPatterns("/app/*");
//	        
//	    }
	    // End Of Interceptor Registry.

}
