## 使用 spring boot 构建一个小家社区

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

```sql
select count(1) from QUESTION;

select * from QUESTION limit 0,5;
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