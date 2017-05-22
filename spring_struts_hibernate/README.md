# spring4.3.8+struts2.5.10.1+hibernate5.2.10整合
## 项目框架结构图
![](http://r.photo.store.qq.com/psb?/V11j0Ega05SCVH/ItrvsRGnPYBD7R6GBWTbyBZ3prg.R0*w7sn.h*CnAuw!/r/dG0BAAAAAAAA)
> 图为：ssh框架结构图

## 简要说明

1、 maven依赖的jar包中有版本冲突，要在pom.xml文件中排除掉冲突的jar。

2、 由于企业都会有多套环境，所以此框架也通过spring.profiles.active以区分不同的环境，在不同的环境下配置不同的属性文件。理论上，此参数应该配置在应用服务器（如tomcat）启动时的VM options中，但为演示方便，故将此参数写在了web.xml中。

3、 web.xml声明加载的配置文件是applicationContext.xml，此配置文件是入口，里面引入了各个不同模块的配置文件。

4、 表结构数据文件已上传到该项目中，详见user.sql文件

## 疑难点
### struts2.5.10.1
- 没有使用注解形式，还是使用原始的struts.xml文件配置形式。

- 一开始使用动态方法调用的配置方式，发现例如/user!add此类链接匹配不到，查看struts的官方文档，struts2.5改版，若要支持动态调用，要加上以下配置。
```
<package name="default" extends="struts-default" abstract="true">
    <!-- 2.5增加了安全性，动态方法调用并不会全部验证通过，所以此处要加上此声明允许所有方法验证通过 -->
    <global-allowed-methods>regex:.*</global-allowed-methods>
</package>
```

- struts2.5使用的日志系统是log4j2，代替了之前老版本的commons-logging，不过spring-core4.3.8用的还是commons-logging，详见下面日志系统的部分。

### spring4.3.8
- 区分不同环境下读取的属性文件
```
<beans profile="dev">
```

### hibernate5.2.10
- 没有使用XXX.cfg.xml和XXX.hbm.xml配置，传统的XXX.cfg.xml配置被spring配置sessionFactory里替代，传统的XXX.hbm.xml配置用注解形式代替。使用注解形式时，需要在配置文件里配置扫描到的文件，新版特性里可以扫描包，关键配置如下：
```
<bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <!-- 扫描注解类-->
    <property name="packagesToScan" value="lq.core.domain.po"/>

    <!-- 指定Hibernate属性 -->
    <property name="hibernateProperties">
        <props>
            <prop key="hibernate.dialect">${hibernate.dialect}</prop>
            <prop key="hibernate.show_sql">${hibernate.show_sql}</prop>
            <prop key="hibernate.format_sql">${hibernate.format_sql}</prop>
            <prop key="hibernate.hbm2ddl.auto">${hibernate.hbm2ddl.auto}</prop>
        </props>
    </property>
</bean>
```

### 日志系统
- 由于struts2.5使用的log4j2，一开始本想使用logback，此时按照网上的配置流程，导入了log4j2相关jar、slf4j相关jar、logback相关jar，再在web.xml中配置logback配置的路径文件和监听类，但启动项目总是报错（主要是说找不到log4j2.xml文件），之后研究发现少了log4j2转slf4j的jar包。目前项目用的log4j2。

- 原理及用法等详看slf4j、log4j2、logback等的官方文档，原理简单来说，slf4j就是一个门面，底层是slf4j适配成一套真正被使用的日志框架，上层将各种日志框架适配成slf4j，而应用则只需调用slf4j即可，扩展性和迁移性非常好。其原理图如下（图片源于网络）：
![](http://r.photo.store.qq.com/psb?/V11j0Ega05SCVH/uJ1Z.srktIqpPcFsHaHtNRusi*8qdMgzxp9ELCo8Mas!/r/dGwBAAAAAAAA)
> 图为：slf4j适配成底层日志框架的结构图

![](http://r.photo.store.qq.com/psb?/V11j0Ega05SCVH/qD3wgHz1.c553vxXWv0PLLczIbQrZqvwDFk4A9b0Lug!/r/dIEBAAAAAAAA)
> 图为：slf4j原理结构图

- 若要使用log4j2作为底层核心日志框架，并且用slf4j作为门面接管日志系统，则maven要引入以下依赖。主要包括log4j2的相关jar，slf4j的相关jar，以及关键的slf4j转log4j2的jar（log4j-slf4j-impl）。
```
<!-- log4j2三个核心包，struts2.5没有用commons-logging了，直接使用的log4j2 -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>${log4j2.version}</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>${log4j2.version}</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-web</artifactId>
    <version>${log4j2.version}</version>
</dependency>
<!-- 将slf4j绑定到log4j2 -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j-impl</artifactId>
    <version>${log4j2.version}</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>${slf4j.version}</version>
</dependency>
```
  - 如果log4j2的文件不打算放在resources目录下（默认的加载路径），则要在web.xml中声明log4j2的配置文件所在的路径。
```
<context-param>
    <param-name>log4jConfiguration</param-name>
    <param-value>config/dev/log4j2.xml</param-value>
</context-param>
```
  - 值得强调的是，对于servlet3.0和servlet3.0之前等不同的版本，web.xml的相关配置不一样，而且对于日志系统的加载等机制也有很大差别，详见官网文档。

- 若要使用logback作为底层核心日志框架，并且用slf4j作为门面接管日志系统，则maven要引入以下依赖。主要包括logback的相关jar，slf4j的相关jar，以及关键log4j2j转slf4j的jar（log4j-to-slf4j）。这里需要说明的是，由于logback是slf4j的原生实现，所以不需要其他jar来进行适配；此处引入log4j2转slf4j的jar，是因为struts2.5框架使用的是log4j2，如果没有将log4j2适配到slf4j上的话，则struts还是会加载log4j2，这时候就会报错。
```
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-to-slf4j</artifactId>
    <version>${log4j2.version}</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-core</artifactId>
    <version>1.2.3</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-access</artifactId>
    <version>1.2.3</version>
</dependency>
<dependency>
    <groupId>org.logback-extensions</groupId>
    <artifactId>logback-ext-spring</artifactId>
    <version>0.1.4</version>
</dependency>
```
  - 指定logback的配置文件路径和监听类。使用log4j2的话不需要声明监听类，因为servlet3.0的机制里默认会监听log4j2.
```
<context-param>
    <param-name>logbackConfigLocation</param-name>
     <param-value>classpath:config/${spring.profiles.active}/logback.xml</param-value>
</context-param>
<listener>
    <listener-class>ch.qos.logback.ext.spring.web.LogbackConfigListener</listener-class>
</listener>
```