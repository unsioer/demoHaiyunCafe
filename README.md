# demoHaiyunCafe
 
Project of *Practice of Java programming design* course in sophomores' summer term in Department of Software Engineering, School of Informatics, Xiamen University.

厦门大学信息学院软件工程系大二小学期《Java程序设计实践》课程项目。

基于 Spring Boot + Maven + MySQL（全部依赖项详见`pom.xml`）。

## Start 开始

- 执行 `/sql`目录下的SQL脚本
  - 测试用非必需：`userorder.sql`
  - **注意UTF-8格式，建议：**
```
mysql -u root -p --default-character-set=utf8mb4
```
- 检查 `/src/main/java/resources/application.properties`的相应配置

## Structure 结构
### `/src/main/java/com.example.demoHaiyunCafe`下
- `Bean`：定义数据库实体等
- `Config`：MVC配置
- `Interceptor`：页面拦截（检查登录状态和用户权限）
- `Controller`：页面具体控制
- `Service`：相关服务方法
- `Mapper`：数据库直接操作相关


## Database 数据库
### Entities 实体
- User 用户
- Menu 菜单

（待续）


