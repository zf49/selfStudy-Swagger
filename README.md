## Swagger

---

1. Web 项目
2. 倒包mvn

```xml
    <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>

```

3. helloworld

4. 配置swagger =》config

```java
@Configuration
@EnableSwagger2 //开启swagger
public class SwaggerConfig {
}
```



5. 测试运行



