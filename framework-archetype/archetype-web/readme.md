1. SpringBoot中加载静态资源和在普通的web应用中不太一样。静态资源（js、css、图片等资源）默认目录位置需置于classpath下，并且符合以下目录规则：
- /static
- /public
- /resources
- /META-INF/resources

> 优先级顺序为：META-INF/resources > resources > static > public 
  建议：直接使用Spring Boot的默认配置方式。
  
  
2. springboot支持的动态模板引擎包括以下类型：
   - Thymeleaf
   - FreeMarker
   - Velocity
   - Groovy
   - Mustache
   springboot对JSP的支持不是很好，应该避免使用JSP。 
   
   如果使用上述的几种模板引擎，默认的模板配置路径为：src/main/resources/templates， templates不要拼写错误。