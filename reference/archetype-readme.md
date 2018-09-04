### Maven 模板
1. 根据已有项目生成Archetype模板到 target/generated-sources/archetype，命令：
```
    mvn archetype:create-from-project
 ```

2. 将archetype 考出（其为一个maven项目），修改src/main/resources/archetype-resources下的目录及pom文件，修改META-INF下的archetype-metadata.xml文件

3. 把模板安装到本地仓库（或者deploy到私服）
```
    mvn install
```

4. 根据模板创建项目

```
    mvn archetype:generate -DarchetypeGroupId=com.github.framework -DarchetypeArtifactId=framework-archetype-archetype -DarchetypeVersion=1.0-SNAPSHOT -DarchetypeCatalog=local -DgroupId=com.caiwei.demo -DartifactId=demo -Dversion=1.0-SNAPSHOT
```

>注意： 
>样例中生成模板时把framework-archetype-archetype模块去掉，这是生成的模板，这里是为了好编辑才放到这。