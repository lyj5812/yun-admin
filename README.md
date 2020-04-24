# yun-admin

#### 介绍
    Yun-Admin 由两大部分组成：第一部分是前端部分，它包含由Vuetify构建的后台管理脚手架。
    第二部分是后端部分，由Spring Cloud+Mybatis Plus+Oauth2+Nacos构建而成的分布式系统。
## 简介

- [在线预览](https://yun-admin.cn)

- [gitee](https://gitee.com/lyj5812/yun-admin)

- [使用文档](https://yun-admin.cn/docs)

- [加入QQ群](https://jq.qq.com/?_wv=1027&k=5wLczSg)

<h1> Yun-Admin </h1>

![Image text](https://img.shields.io/badge/springcloud-2.1.8-green.svg)
![Image text](https://img.shields.io/badge/springboot-2.1.8-green.svg)
![Image text](https://img.shields.io/badge/MyBatis%20Plus-3.1.0-green.svg)

**项目说明** 
- 该项目致力于简洁高效率生产微服务脚手架
- 项目不断维护完善中。
- 整合swagger2接口文档，微服务聚合文档，无需繁琐配置
- 整合加强版微服务聚合文档，简化开发编写文档
- 动态数据源代码生成，不光生成实体,
- 引入了lombok 大量简化了代码
- 整合阿里nacos,简化配置文件,在线管理配置文件,修改即时生效
- 使用MyBatis Plus 大量简化了SQL
- 前后端分离，前端代码单独部署
- 使用oauth2,微服务鉴权，整合微信小程序登录,更多登录方式正在整合中...
- 账号密码：admin/123456

 
**版本信息** 
- 核心框架：Spring Boot 2.1.8、Spring Cloud
- 安全框架：Spring Security 
- 视图框架：Spring MVC 5.1.x
- 持久层框架：MyBatis Plus 3.1.0
- 页面交互：Vue2.x 


**环境** 
- jdk 1.8
- mysql 5.7+
- redis
- nacos
- nginx


**项目结构** 
```
Yun-Admin
├─docker-compose  
│  ├─docker-compose.yml docker-compose部署配置文件
├─mysql  
│  ├─Dockerfile
│  ├─my.cnf mysql配置文件(已有mysql的无需关注)
│  ├─nacos.sql nacos sql文件
│  ├─privileges.sql mysql权限sql文件(已有mysql的无需关注)
│  ├─yun-admin.sql yun-admin sql文件
├─nacos nacos 加入wait-for-it.sh,自己安装nacos的无需关注
├─yun-admin 后台管理模块
│  ├─admin-client 后台feign和数据模型
│  ├─admin-dao 后台dao和实体类
│  ├─admin-server 后台服务
├─yun-auth 鉴权模块
│  ├─auth-server 鉴权服务中心
├─yun-common 通用模块
│  ├─common-cache-data 数据缓存模块
│  ├─common-core 通用核心模块
│  ├─common-framework framework模块
│  ├─common-upload 文件上传模块
│  ├─common-wx-ma 微信小程序模块
│  ├─common-wx-mp 微信公众模块
├─yun-gateway 网关模块
│  ├─gateway-server 网关服务
├─yun-gen 代码生成模块
│  ├─gen-server 代码生成服务

```
**开发环境** 
- 工具和环境
 ```
   1、 mysql5.7
   
   2 、jdk1.8
   
   3、 nacos1.14
   
   3、 redis
   
   4、 node(前端需要)
 ```
 - 后台启动
  ```
    1、 部署好MySQL、redis,导入sql
    
    2 、启动nacos
    
    3、 依次启动admin-server、auth-server、gateway-server,其他服务随意
    
    4、 启动前端
  ```
**系统截图**
![Image text](https://lyj5812.oss-cn-beijing.aliyuncs.com/page-1.png)
![Image text](https://lyj5812.oss-cn-beijing.aliyuncs.com/page-2.png)
![Image text](https://lyj5812.oss-cn-beijing.aliyuncs.com/page-3.png)
![Image text](https://lyj5812.oss-cn-beijing.aliyuncs.com/page-4.png)
![Image text](https://lyj5812.oss-cn-beijing.aliyuncs.com/page-5.png)

 **最后**

- 如果项目对您有帮助请star fork 谢谢您的关注 Yun-admin会持续更新