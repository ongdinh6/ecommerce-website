# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.5/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.5/gradle-plugin/packaging-oci-image.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.3.5/reference/using/devtools.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.3.5/reference/web/servlet.html)

### Guides
* ```cd demo```, then run ```./gradlew bootRun``` -> Start backend. Host: 8080
* At the root, run ```npm run dev``` -> Start frontend. Host: 3000
* For testing with Authentication, use this account: user@test.com, password

Additional,
* ```./gradlew updateDatabase ``` -> Trigger Liquibase Migration
* ```./gradlew generateJooq``` -> Generate Jooq Java Object
* ```./gralew cleanGenerateJooq``` -> Clean generated Jooq
* API documentation: ```http://localhost:8080/swagger-ui```

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

