package lq.common.config.mybatis;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * mybatis扫描映射文件。 如果是用@mapper注解，就没必要扫描mapper的xml文件了。
 *
 * @author 刘泉 2017年05月24日 14:36
 */
@Configuration
// 因为这个对象的扫描，需要在MyBatisConfig的后面注入，所以加上下面的注解
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

    /**
     * 注意：导入的是tk.mybatis包里的MapperScannerConfigurer。如果不使用tk.mybatis的通用mapper，则不需要声明TypeAliasesPackage和BasePackage，直接使用@Mapper注解即可。
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //获取之前注入的beanName为sqlSessionFactory的对象
        mapperScannerConfigurer
            .setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("lq.core.mapper");
        //Properties properties = new Properties();
        //properties.setProperty("mappers", Mapper.class.getName());
        //properties.setProperty("notEmpty", "false");
        //properties.setProperty("IDENTITY", "MYSQL");
        //mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}
