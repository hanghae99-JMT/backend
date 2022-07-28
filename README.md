# **JMT backend**

## Data Structure

### ERD Diagram
![sql](https://user-images.githubusercontent.com/66009926/181502507-8badeb8e-a044-4191-a2ca-1ca6adc766e2.png)

---

## 테이블

<img width="722" alt="image" src="https://user-images.githubusercontent.com/66009926/181502632-47823bf3-d139-4bc8-b634-d27bd9facf3a.png">

```sql
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `authority_list` TINYBLOB NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `enabled` BIT(1) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe` (`email` ASC) VISIBLE)
ENGINE = InnoDB
```

<img width="719" alt="image" src="https://user-images.githubusercontent.com/66009926/181502746-d6362cb8-0eb7-43f9-b1ec-b1ad6bd44435.png">

```sql
CREATE TABLE IF NOT EXISTS `restaurant` (
  `restaurant_id` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `category` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `like_count` BIGINT NULL DEFAULT NULL,
  `map_x` VARCHAR(255) NULL DEFAULT NULL,
  `map_y` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `thumbnail` VARCHAR(255) NULL DEFAULT NULL,
  `url` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`restaurant_id`))
ENGINE = InnoDB
```

<img width="717" alt="image" src="https://user-images.githubusercontent.com/66009926/181503002-f9eaaee7-082e-4120-9830-d2d7f3ac0134.png">

```sql
CREATE TABLE IF NOT EXISTS `review` (
  `review_id` BIGINT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(255) NULL DEFAULT NULL,
  `restaurant_id` VARCHAR(255) NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`review_id`),
  INDEX `FK70ry7cuti298yxet366rynxch` (`restaurant_id` ASC) VISIBLE,
  INDEX `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK70ry7cuti298yxet366rynxch`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `test2`.`restaurant` (`restaurant_id`),
  CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi`
    FOREIGN KEY (`user_id`)
    REFERENCES `test2`.`user` (`user_id`))
ENGINE = InnoDB
```

<img width="714" alt="image" src="https://user-images.githubusercontent.com/66009926/181503106-7cca39d5-64db-4ef1-b1cc-f370fe98097d.png">

```sql
CREATE TABLE IF NOT EXISTS `likes` (
  `like_id` BIGINT NOT NULL AUTO_INCREMENT,
  `restaurant_id` VARCHAR(255) NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`like_id`),
  UNIQUE INDEX `UniqueRestaurantUser` (`restaurant_id` ASC, `user_id` ASC) VISIBLE,
  INDEX `FKi2wo4dyk4rok7v4kak8sgkwx0` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKi2wo4dyk4rok7v4kak8sgkwx0`
    FOREIGN KEY (`user_id`)
    REFERENCES `test2`.`user` (`user_id`),
  CONSTRAINT `FKmamcxnitc6oyoxjdr7dwbac8x`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `test2`.`restaurant` (`restaurant_id`))
ENGINE = InnoDB
```

## Folder Structure

```
.
├── V1Application.java
├── config
│   ├── exception
│   │   ├── ErrorResponse.java
│   │   ├── MethodArgumentNotValidExceptionHandler.java
│   │   └── RuntimeExceptionHandler.java
│   ├── logging
│   │   ├── LogAspect.java
│   │   ├── LoggingFilter.java
│   │   ├── LoggingInterceptor.java
│   │   └── annotation
│   │       └── LogExecutionTime.java
│   ├── security
│   │   ├── JWTCheckFilter.java
│   │   ├── JWTLoginFilter.java
│   │   ├── JWTUtil.java
│   │   ├── PasswordEncoder.java
│   │   └── SecurityConfig.java
│   └── web
│       └── WebConfig.java
├── layer
│   ├── like
│   │   ├── controller
│   │   │   └── LikeController.java
│   │   ├── domain
│   │   │   ├── Likes.java
│   │   │   └── dto
│   │   │       ├── request
│   │   │       │   └── LikeAddRequestDto.java
│   │   │       └── response
│   │   │           ├── LikeAddResponseDto.java
│   │   │           └── LikeResponseDto.java
│   │   ├── infra
│   │   │   └── LikeRepository.java
│   │   └── service
│   │       └── LikeService.java
│   ├── restaurant
│   │   ├── controller
│   │   │   └── RestaurantController.java
│   │   ├── domain
│   │   │   ├── Restaurant.java
│   │   │   └── dto
│   │   │       └── response
│   │   │           ├── RestaurantRankingResponseDto.java
│   │   │           └── RestaurantSearchResponseDto.java
│   │   ├── infra
│   │   │   └── RestaurantRepository.java
│   │   └── service
│   │       └── RestaurantService.java
│   ├── review
│   │   ├── controller
│   │   │   └── ReviewController.java
│   │   ├── domain
│   │   │   ├── Review.java
│   │   │   └── dto
│   │   │       ├── request
│   │   │       │   └── ReviewRequestDto.java
│   │   │       └── response
│   │   │           ├── MyReviewResponseDto.java
│   │   │           └── ReviewResponseDto.java
│   │   ├── infra
│   │   │   └── ReviewRepository.java
│   │   └── service
│   │       └── ReviewService.java
│   └── user
│       ├── controller
│       │   └── UserController.java
│       ├── domain
│       │   ├── User.java
│       │   └── dto
│       │       ├── request
│       │       │   ├── SigninRequestDto.java
│       │       │   └── SignupRequestDto.java
│       │       └── response
│       │           ├── GetUserDataResponseDto.java
│       │           └── SigninResponseDto.java
│       ├── infra
│       │   └── UserRepository.java
│       └── service
│           └── UserService.java
└── util
    ├── SearchLocal
    │   ├── SearchLocalClient.java
    │   └── domain
    │       └── dto
    │           ├── SearchLocalRequestDto.java
    │           └── SearchLocalResponseDto.java
    └── naver
        ├── NaverClient.java
        └── domain
            └── dto
                ├── SearchImageRequestDto.java
                ├── SearchImageResponseDto.java
                ├── SearchLocalRequestDto.java
                └── SearchLocalResponseDto.java
```

## API Link
https://elderly-gruyere-ed2.notion.site/85abe3c68b49444f9e432a086fd30b13?v=98d2bb286be943418e7a46467b0e4f0a

## How to run

### Properties

```
# MYSQL 설정
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://mysql-link/serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.username= root
spring.datasource.password= password
spring.jpa.database=mysql
spring.jpa.hibernate.ddl-auto=update

# 네이버 API 설정
naver.url.search.local=https://openapi.naver.com/v1/search/local.json
naver.url.search.image=https://openapi.naver.com/v1/search/image
naver.client.id= id
naver.client.secret= key

# 카카오 API 설정
kakao.url.search.local=https://dapi.kakao.com/v2/local/search/keyword.json
kakao.restapi.key= key

# Spring Security 설정
spring.security.filter.order=10

# Spring Admin 설정
server.port=8080
spring.boot.admin.url= admin url
spring.boot.admin.client.url= admin url
spring.boot.admin.client.username=admin
spring.boot.admin.client.password=admin
spring.boot.admin.client.instance.metadata.user.name=admin
spring.boot.admin.client.instance.metadata.user.password=admin
spring.boot.admin.client.instance.service-url= client url
spring.security.user.name=admin
spring.security.user.password=admin
management.endpoints.web.exposure.include=*
management.endpoints.health.show-details=always

# Sentry 설정
sentry.dsn=https://c110b598b1f042f19e0fc6c027d0e4b5@o1336019.ingest.sentry.io/6604408
# Set traces-sample-rate to 1.0 to capture 100% of transactions for performance monitoring.
# We recommend adjusting this value in production.
sentry.traces-sample-rate=1.0
```

### Requirement

```
Spring boot : 2.6.1
Java JDK : 11
```

### Build & Run

```
$ ./gradlew bootjar
$ java -jar 프로젝트명.jar
```
