package lq.common.config.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

/**
 * mybatis配置相关，包括事务控制。
 *
 * @author 刘泉 2017年05月24日 11:29
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {

    @Autowired
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSource);
            // 1、注意： 若使用@Mapper注解，则可以不用配置以下别名和mapper扫描路径。
            // 2、绑定数据持久层对象和数据库对象，配置类型别名
            bean.setTypeAliasesPackage("lq.core.domain.po");
            // 3、可以在这里配置mapper的扫描，找到所有的mapper.xml映射文件。但此项目使用了tk.mybatis的通用mapper，所以不用spring-mybatis的扫描。
            // 4、分页插件，注意5.0版本后配置和之前不一样
            PageInterceptor pageInterceptor = new PageInterceptor();
            Properties properties = new Properties();
            properties.setProperty("offsetAsPageNum", "true");
            properties.setProperty("rowBoundsWithCount", "true");
            properties.setProperty("reasonable", "true");
            properties.setProperty("supportMethodsArguments", "true");
            properties.setProperty("returnPageInfo", "check");
            properties.setProperty("params", "count=countSql");
            pageInterceptor.setProperties(properties);
            bean.setPlugins(new Interceptor[] { pageInterceptor });
            // 5、若配置了mybatis-config.xml文件，则用如下代码引入文件位置
            // ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            // bean.setConfigLocation(esolver.getResource("classpath:mybatis-config.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(
        SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

}
