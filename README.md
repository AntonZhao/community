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
create table user
(
	id bigint auto_increment,
	account_id varchar(100),
	name varchar(50),
	token char(50),
	gmt_create bigint,
	gmt_modified bigint,
	bio varchar(256),
	avatar_url varchar(100),
	constraint user_pk
	primary key (id)
);

create table question
(
	id bigint auto_increment
	primary key,
	title varchar(50),
	description text,
	gmt_create bigint,
	gmt_modified bigint,
	creator bigint,
	comment_count integer default 0,
	view_count integer default 0,
	like_count integer default 0,
	tag varchar(256)
);

create table notification
(
	id bigint  auto_increment,
	notifier bigint not null,
	receicer bigint not null,
	outerid bigint not null,
	type integer not null,
	gmt_create bigint not null,
	status integer default 0 not null,
	notifier_name varchar(100),
	outer_title varchar(256),
	constraint notification_pk
	primary key (id)
);

create table comment
(
	id bigint  auto_increment,
	parent_id bigint not null,
	type integer not null,
	commentator bigint not null,
	gmt_create bigint not null,
	gmt_modified bigint not null,
	like_count bigint default 0,
	content varchar(1024) not null,
	comment_count integer default 0 not null,
	constraint comment_pk
	primary key (id)
);

comment on column comment.parent_id is '父类id';

comment on column comment.type is '父类类型';

comment on column comment.gmt_create is '创建时间';

comment on column comment.gmt_modified is '更新时间';

comment on column comment.like_count is '点赞数';
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
- 用 maven 启动

#### 2. IOC AOP
- Spring大法好

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

#### 21. mysql配置文件 my.cnf
- 行云流水的mysql安装指南：https://blog.csdn.net/a774630093/article/details/79270080
- 添加的属性不能放在最后面，不然不管用。。
- 修改字符编码 https://www.jianshu.com/p/155c332340db
- 一定要先修改字符集再去创建表
- https://docs.lvrui.io/2016/08/21/%E4%BF%AE%E6%94%B9MySQL%E7%9A%84%E5%AD%97%E7%AC%A6%E9%9B%86%E4%B8%BAutf8mb4/

#### 22. yum换源
- https://blog.csdn.net/qq_35530330/article/details/96363034

#### 23. 为什么使用定时器
- 每次都查影响性能
- 作为一种异步的离线任务，api的形式
- 昨天的数据