## 使用 spring boot 构建一个小家社区

## 资料
[Spring 文档](https://spring.io/guides)

[Spring web文档](https://spring.io/guides/gs/serving-web-content/)

[es社区](https://elasticsearch.cn/explore)

[bootstrap样式](https://v3.bootcss.com/)

[GitHub OAuth 文档](https://developer.github.com/apps/building-oauth-apps/)

[菜鸟教程 MySQL](https://www.runoob.com/mysql/mysql-install.html)

[Spring boot mybatis](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)

## 工具
[Git](https://git-scm.com/)

[VP](https://www.visual-paradigm.com/cn/)

[FlyWay](https://flywaydb.org/getstarted/firststeps/maven)

## 脚本
```sql
create table USER
(
	ID INTEGER default NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_6C0B6829_AD9A_493F_9076_0094D552B944" auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(50),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
		primary key (ID)
);


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