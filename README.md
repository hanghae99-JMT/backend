# **JMT backend**

## Data Structure

### ERD Diagram
![Untitled](https://user-images.githubusercontent.com/66009926/180600348-6704fc0b-152d-4e68-899a-d2fb7a107d4a.png)

---

## 테이블

<img width="729" alt="image" src="https://user-images.githubusercontent.com/66009926/180600428-ea20dd37-4852-4be5-9aca-e77a79fed3dd.png">

```sql
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `authority_list` TINYBLOB NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `enabled` BIT(1) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
```

<img width="743" alt="image" src="https://user-images.githubusercontent.com/66009926/180600453-7145bd01-23b7-46f2-81d4-0267bafa9615.png">

```sql
CREATE TABLE IF NOT EXISTS `restaurant` (
  `restaurant_id` BIGINT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `category` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `like_count` BIGINT NULL DEFAULT NULL,
  `map_x` INT NOT NULL,
  `map_y` INT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `thumbnail` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`restaurant_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
```

<img width="719" alt="image" src="https://user-images.githubusercontent.com/66009926/180600473-a04fc202-f608-4192-aa63-d72a5678e96c.png">

```sql
CREATE TABLE IF NOT EXISTS `restaurant` (
  `restaurant_id` BIGINT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `category` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `like_count` BIGINT NULL DEFAULT NULL,
  `map_x` INT NOT NULL,
  `map_y` INT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `thumbnail` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`restaurant_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
```

<img width="724" alt="image" src="https://user-images.githubusercontent.com/66009926/180600483-403b63a8-70f3-4f79-8616-10ee296f31bc.png">

```sql
CREATE TABLE IF NOT EXISTS `likes` (
  `like_id` BIGINT NOT NULL AUTO_INCREMENT,
  `restaurant_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`like_id`),
  INDEX `FKi2wo4dyk4rok7v4kak8sgkwx0` (`user_id` ASC) VISIBLE,
  INDEX `FKmamcxnitc6oyoxjdr7dwbac8x` (`restaurant_id` ASC) VISIBLE,
  CONSTRAINT `FKi2wo4dyk4rok7v4kak8sgkwx0`
    FOREIGN KEY (`user_id`)
    REFERENCES `test3`.`user` (`user_id`),
  CONSTRAINT `FKmamcxnitc6oyoxjdr7dwbac8x`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `test3`.`restaurant` (`restaurant_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
```

## How to run

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
