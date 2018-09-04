
## dubbo使用说明

### 1. 引入dubbo依赖：

```
<dependency>
     <artifactId>framework-starter</artifactId>
     <groupId>com.github.framework</groupId>
     <version>1.0-SNAPSHOT</version>
</dependency>
```
### 2. 配置：
```
dubbo.application=appName
#注册地址
dubbo.registry.address=zookeeper://127.0.0.1:2181
#dubbo端口（每个应用都应该不同）
dubbo.protocol.port=20882
#应用负责人（可以在dubboMonitor上看到这个信息，方便dubbo服务出现问题时直接找对应负责人）
dubbo.owner=xxx
```
### 3. 使用dubbo：
服务提供方只需使用dubbo的注解@Service：
```
// 注意下面的@Service是com.alibaba.dubbo.config.annotation.Service，不是spring的注解
@Service
public class UserServiveImpl implements IUserService {

    @Override
    public UserDTO login(UserDTO userDTO) {
        // 执行具体业务
    }
}
```
服务消费者只需使用dubbo的注解@Reference:
```
@Reference
private IUserService userService;

@Override
public UserDTO someThing(UserDTO userDTO) {
    //TODO
}
```
### 4.其他功能
增加了dubbo服务引用工厂（DubboReferenceFactory），对于需要在运行阶段动态调用不同的dubbo服务（比如异步通知），可使用它动态获取dubbo服务：
```
@Autowired
private DubboReferenceFactory dubboReferenceFactory;

// 动态获取dubbo服务，入参分别是：服务的接口、服务的组名、服务的版本
dubboReferenceFactory.getReference(MessageService.class, "group", "1.0");
```