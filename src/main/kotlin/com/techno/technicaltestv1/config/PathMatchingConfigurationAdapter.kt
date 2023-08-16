package com.techno.technicaltestv1.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class PathMatchingConfigurationAdapter(
    val mainInterceptor: MainInterceptor
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(mainInterceptor).addPathPatterns("/v1/apiService/crud/brand")
        registry.addInterceptor(mainInterceptor).addPathPatterns("/v1/apiService/crud/login/token")
    }
}