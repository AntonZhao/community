## 使用 spring boot 构建一个小家社区  

## 依赖
- Git 把代码拉下来
- JDK 编译
- Maven 构建项目
- MySQL 生产环境的数据库  
## 部署
- yum update: 更新源
- yum install git
- yum install maven 顺便把JDK也装了
- mvn -v 查看maven的版本
- mvn clean compile package
- cp  src/main/resources/application.properties  src/main/resources/application-production.properties
- mvn package
- java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar 启动项目
- 访问项目，阿里云服务器需要配置80端口的安全组规则
- ps -aux | grep java  检查进程
- git pull 拉代码
## 资料  
[Spring 文档](https://spring.io/guides)  
[Spring web文档](https://spring.io/guides/gs/serving-web-content/)  
[es社区](https://elasticsearch.cn/explore)  
[bootstrap样式](https://v3.bootcss.com/)  
[GitHub OAuth 文档](https://developer.github.com/apps/building-oauth-apps/)  
[菜鸟教程 MySQL](https://www.runoob.com/mysql/mysql-install.html)  
[Spring boot mybatis](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)  
[lombok---需要maven引用和idea的插件下载](https://projectlombok.org/)  
[spring-boot 热部署文档](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#using-boot-devtools-restart)  
[Spring web MVC 文档 （拦截器）](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-handlermapping-interceptor)  
[thymeleaf 文档](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)  
[mybatis](http://mybatis.org/generator/quickstart.html)  
[markdown编辑器](https://pandao.github.io/editor.md/)  
[Markdown 插件](http://editor.md.ipandao.com/)   
[UFfile SDK](https://github.com/ucloud/ufile-sdk-java)  
等待  

## 工具
[Git](https://git-scm.com/)  
[优秀的画图软件---VP](https://www.visual-paradigm.com/cn/)  
[FlyWay](https://flywaydb.org/getstarted/firststeps/maven)  
[优秀的GitHub浏览插件---octotree](https://www.octotree.io/)

## 脚本
```sql
create table USER
(
	ID INTEGER default NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_D9039AC6_F2AE_40AE_833A_60ABBF71CB4A" auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(50),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	BIO VARCHAR(256),
	AVATAR_URL VARCHAR(100),
	constraint USER_PK
		primary key (ID)
);


create table QUESTION
(
	ID INTEGER default NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_0F34DFC7_AE40_4073_9EC6_B7BB0A362EC5" auto_increment
		primary key,
	TITLE VARCHAR(50),
	DESCRIPTION CLOB,
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	CREATOR INTEGER,
	COMMENT_COUNT INTEGER default 0,
	VIEW_COUNT INTEGER default 0,
	LIKE_COUNT INTEGER default 0,
	TAG VARCHAR(256)
);

create table COMMENT
(
	ID BIGINT auto_increment,
	PARENT_ID BIGINT not null,
	TYPE INTEGER not null,
	COMMENTATOR INTEGER not null,
	GMT_CREATE BIGINT not null,
	GMT_MODIFIED BIGINT not null,
	LIKE_COUNT BIGINT default 0,
	CONTENT CLOB not null,
	constraint COMMENT_PK
		primary key (ID)
);

comment on column COMMENT.PARENT_ID is '父类ID';

comment on column COMMENT.TYPE is '父类类型';

comment on column COMMENT.COMMENTATOR is '评论人ID';

comment on column COMMENT.GMT_CREATE is '创建时间';

comment on column COMMENT.GMT_MODIFIED is '更新时间';

comment on column COMMENT.LIKE_COUNT is '点赞数';


create table NOTIFICATION
(
	ID BIGINT  auto_increment,
	NOTIFIER BIGINT not null,
	RECEICER BIGINT not null,
	OUTERID BIGINT not null,
	TYPE INTEGER not null,
	GMT_CREATE BIGINT not null,
	STATUS INTEGER default 0 not null,
	constraint NOTIFICATION_PK
		primary key (ID)
);






```

```sql
select count(1) from QUESTION;

select * from QUESTION limit 0,5;
```

```bash
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```



#### 1. 如何启动 spring boot 项目
- 在ide里直接执行main方法
- 打成jar包作为服务
- 用 maven 启动 ？？？ 还不太懂 

#### 2. IOC AOP
- 懂了再来写

#### 3. @component
- 会把对象自动实例化放在一个池子里，方便使用

#### 4. 参数比较多，封装成对象

#### 5. @Autowired: 自动实例化一个类

#### 6. @Value
- 根据配置文件去获得这个值
- Spring启动的时候会把配置文件里的值放在一个map里

#### 7. maven的pom
- <scope>test</scope>
- 多了一句这个竟然就找不到h2的driver class了

#### 8. h2是大坑
- 服务端和ide不能同时连接h2数据库，记得关掉

#### 9. 表单里的name一定要写好记得写

#### 10. 解决：IDEA unable to import maven project
- 删除 .idea 文件夹
- 重新 import 项目

#### 11. mapper --- dto --- service
- mapper是数据库模型的文件
- dto是数据传输时的模型
- service用来组装多个mapper的使用

#### 12. mybatis需要配置驼峰命名法的转换
- mybatis.configuration.map-underscore-to-camel-case=true

#### 13. fastjson可以自动把下划线命名映射成驼峰命名法

#### 14.运行的时候直接自动build
- shift-command-option-? 打开registry，compiler-automake-allow-when-app-running 打勾

#### 15. th的作用是替换

#### 16. @EnableWebMvc
- 这个注解代表全面接管SpringMVC，之前的默认配置都没了

#### 17. th:href里面不能加空格哦
- th:href="@{'question/'+${question.id}}"
- 空格也是算数的

#### 18. 事务的概念
-  @Transactional
- 如果有异常或者错误，回滚前面的更新语句

#### 19. 前端页面debug---debugger

#### 20.idea抽取快捷键
- command+option+v 抽取变量
- command+option+p 抽取参数
- command+option+f 抽取方法