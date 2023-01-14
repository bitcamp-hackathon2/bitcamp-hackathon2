package hackathon2.backend.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

  public WebConfig() {

  }

  public void addCorsMapping(CorsRegistry registry) {
    registry.addMapping("/**")
    .allowedOrigins("http://localhost:5500", "http://127.0.0.1:5500")
    .allowedMethods("*");
  }

}