package com.jp.findhospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods(ALLOWED_METHOD_NAMES.split(","))
                .allowCredentials(true); // 쿠키전달여부 허용
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("/**")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("Authorization", "Content-Type")
//                .exposedHeaders("Custom-Header")
//                .allowCredentials(true)
//                .maxAge(3600);
//    }

}
//        1) addMapping
//        addMapping을 사용해 CORS를 적용할 URL 패턴을 정의합니다.
//        위 처럼 "/**" 와일드 카드를 사용할 수도 있고 "/somePath/**" 이렇게 적용할 수도 있습니다.
//
//        2) allowedOrigins
//        allowedOrigins를 이용해서 자원 공유를 허락할 Origin을 지정할 수 있습니다.
//
//        위 처럼 "*"로 모든 Origin을 허락할 수 있습니다.
//
//        .allowedOrigins("http://localhost:8080", "http://localhost:8081");
//        이렇게 한번에 여러 Origin을 설정할 수도 있습니다.
//
//        3) allowedMethods
//        allowedMethods를 이용해서 위 처럼 허용할 HTTP method를 지정할 수 있습니다.
//
//        이 때 "*"를 사용해 모든 method를 허용할 수도 있습니다.
//
//        4) allowedHeaders
//        allowedHeaders를 이용해 클라이언트 측의 CORS 요청에 허용되는 헤더를 지정합니다.
//
//        기본적으로 Content-Type, Accept 및 Origin과 같은 간단한 요청 헤더만 허용됩니다.
//
//        5) exposedHeaders
//        exposedHeaders를 이용해 클라이언트측 응답에서 노출되는 헤더를 지정합니다.
//
//        6) allowCredentials
//        allowCredentials를 이용해 클라이언트 측에 대한 응답에 credentials(예: 쿠키, 인증 헤더)를 포함할 수 있는지 여부를 지정합니다.
//
//        기본값은 false로 되어있습니다.
//        true로 설정하면 클라이언트가 요청에 credentials를 포함하고 응답에서 받을 수 있습니다.
//
//        credentials를 사용할 때 응답의 Access-Control-Allow-Origin 헤더가 *로 설정되지 않았는지 확인해야 합니다. 요청 원본과 명시적으로 일치해야 합니다.
//
//        7) maxAge
//        maxAge를 이용해서 원하는 시간만큼 pre-flight 리퀘스트를 캐싱 해둘 수 있습니다.
//
//
