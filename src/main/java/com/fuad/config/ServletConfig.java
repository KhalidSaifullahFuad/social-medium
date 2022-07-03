package com.fuad.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@ComponentScan(basePackages = {"com.fuad.controller"})
public class ServletConfig implements WebMvcConfigurer {

    // Configuration to render VIEWS
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Register resource handler for -

        // IMAGES
        registry
                .addResourceHandler(
                "/images/**",
                // for security purpose only
                    "/status/show/images/**",
                    "/status/list/images/**",
                    "/user/show/images/**",
                    "/user/list/images/**"
                )
                .addResourceLocations(
                        "/WEB-INF/resources/images/",
                        "file:///" + Properties.WRITE_PATH + "/",
                         Properties.WRITE_PATH + "/"
                ) // Actual resource locations
                .setCachePeriod(0); // Cache period

        // CSS
        registry
                .addResourceHandler("/css/**")
                .addResourceLocations("/WEB-INF/resources/css/")
                .setCachePeriod(0);

        // JAVASCRIPT
        registry
                .addResourceHandler("/js/**")
                .addResourceLocations("/WEB-INF/resources/js/")
                .setCachePeriod(0); // Cache period

        // Other template resource vendor files
        registry
                .addResourceHandler("/vendor/**")
                .addResourceLocations("/WEB-INF/resources/vendor/")
                .setCachePeriod(0);

    }
}
