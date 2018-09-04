## mybatis使用说明

### 1. 添加mybatis依赖：
```
<dependency>
    <groupId>com.github.framework</groupId>
    <artifactId>framework-starter-mybatis</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
### 2. 配置：
```
#mapper接口扫描路径
mybatis.scan=com.github.**.mapper
#mapper的xml文件路径
mybatis.mapper-locations=classpath:com/github/**/mapper/*Mapper.xml
```
### 3. 使用mybatis：
1. 分页查询
framework 已经集成分页插件，分页脚本不再需要在xml里写（比如上面的queryUser），只需要在调用mapper接口前调用PageHelper：

```
PageHelper.startPage(1, 3); //1为页数，3为每页条数
Page<user> page=userMapper.queryUser(10);  
```
2. 返回条数上限
当未通过分页插件设置分页信息时，默认添加5000条上限，防止漏加分页导致的全表扫描返回全量数据。如果需要修改上限可通过以下配置：

```
#默认上限5000条
mybatis.upper-limit=6000
```