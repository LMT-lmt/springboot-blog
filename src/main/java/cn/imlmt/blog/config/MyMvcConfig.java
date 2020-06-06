package cn.imlmt.blog.config;


import cn.imlmt.blog.Interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    private String[] excludePaths = {"/","/index","/index.html","/css/**","/js/**","/images/**",
            "/lib/**","/webjars/**"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加不拦截的路径，springboot2.+会拦截静态资源
        //registry.addInterceptor(new LoginHandlerInterceptor()).excludePathPatterns(excludePaths);
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
