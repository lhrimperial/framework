### 简介
framework 以springboot为基础集成常用的框架，以达到快速搭建项目，减少配置步骤等目的。目前集成了常用的jdbc、mybatis、dubbo、jpa、Redis、Web等模块。
framework-test下，有各个集成模块的测试应用，可供使用参考。根据常规项目结构生成Maven骨架，可根据骨架生成项目结构，[Archetype使用说明](./reference/archetype-readme.md)。

### 各模块说明
framework
    - framework-archetype   自定义maven骨架，里面定义了一个业务服务分层
    - framework-dependencies    统一管理第三方依赖版本
    - framework-server      封装了web开发中常用组件，权限拦截、XSS拦截、缓存等
    - framework-util        常用工具
    - framework-starter     springboot整合常用框架

### 其他模块使用说明
##### [jdbc使用说明](./reference/jdbc-readme.md)
##### [mybatis使用说明](./reference/mybatis-readme.md)
##### [dubbo使用说明](./reference/dubbo-native-readme.md)
##### [jpa使用说明](./reference/jpa-readme.md)
##### [redis使用说明](./reference/redis-readme.md)
##### [web使用说明](./reference/web-readme.md)