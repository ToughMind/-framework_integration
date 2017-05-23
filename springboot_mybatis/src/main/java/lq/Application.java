package lq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 项目启动入口。
 *
 * @author 刘泉 2017年05月23日 21:02
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    /**
     * jar形式启动。
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * tomcat war形式启动。
     */
    @Override
    protected SpringApplicationBuilder configure(
        SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
