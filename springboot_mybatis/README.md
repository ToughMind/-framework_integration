# springboot1.5.3+mybatis3.4.4整合

## 项目框架结构图


## 简要说明

1、 集成了swagger2（包括其ui界面），可以自动生成api文档结构，详见swagger2官方文档

2、 集成了druid连接池，并使用其监控服务等

3、 使用log4j2+slf4j日志框架

4、 支持@Scheduled注解的定时器任务执行

5、 支持@Async注解的方法异步调用

## 疑难点
### 配置相关
- 若同时有application.yml和application.properties文件，项目会优先选择yml文件。

- 此处有个大坑要注意，由于本项目引入了spring-boot-starter-parent，所以在`properties`里申明的名称会被其使用（原意是想在此pom.xml文件中使用的）。
 
  - 譬如`<spring-boot.version>1.5.3.RELEASE</spring-boot.version>`，若声明了名称，在maven编译的时候，会报如下错误`Non-resolvable import POM: Failure to find org.apache.logging.log4j:log4j-bom:pom:1.5.3.RELEASE`
  - 建议在`properties`里的命名加上特殊标识的前缀，以与默认的一些命名区分。

### spring boot应用入口
- 官方建议，入口类不要写在默认目录下（理论上实战开发中不会这么设计）。

- 入口类放在根包路径下，那么可以使用`@SpringBootApplication`来代替三个注解`@Configuration``@EnableAutoConfiguration``@ComponentScan`

- 入口类继承SpringBootServletInitializer并重写configure方法。运行主方法后，会将我们的web项目打包成war，并默认启动一个端口为8080的tomcat容器来运行我们的Web项目。

### spring boot注解相关
- @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
