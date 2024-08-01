-- -----------------------------------------------------
-- Schema lib
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS lib DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE lib ;

-- -----------------------------------------------------
-- Table lib.author
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lib.author (
  id BIGINT NOT NULL AUTO_INCREMENT,
  description VARCHAR(300) NOT NULL,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX UKor6k6jmywerxbme223c988bmg (name ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table lib.book
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lib.book (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(300) NOT NULL,
  isbn VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX UKwugryet8mf6oi28n00x2eoc4 (name ASC) VISIBLE,
  UNIQUE INDEX UKehpdfjpu1jm3hijhj4mm0hx9h (isbn ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table lib.books_authors
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lib.books_authors (
  book_id BIGINT NOT NULL,
  author_id BIGINT NOT NULL,
  PRIMARY KEY (book_id, author_id),
  INDEX FKov77rfnon6gwd8emlsah05qty (author_id ASC) VISIBLE,
  CONSTRAINT FK6ojkw2gy23xsgdkqih628favt
    FOREIGN KEY (book_id)
    REFERENCES lib.book (id),
  CONSTRAINT FKov77rfnon6gwd8emlsah05qty
    FOREIGN KEY (author_id)
    REFERENCES lib.author (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table lib.category
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lib.category (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX UK46ccwnsi9409t36lurvtyljak (name ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table lib.books_categories
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lib.books_categories (
  book_id BIGINT NOT NULL,
  category_id BIGINT NOT NULL,
  PRIMARY KEY (book_id, category_id),
  INDEX FKkksgsy7k6sy1hr55se8mpktvb (category_id ASC) VISIBLE,
  CONSTRAINT FKb9cxhvxrdqagj2np5dliseash
    FOREIGN KEY (book_id)
    REFERENCES lib.book (id),
  CONSTRAINT FKkksgsy7k6sy1hr55se8mpktvb
    FOREIGN KEY (category_id)
    REFERENCES lib.category (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table lib.publisher
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lib.publisher (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX UKh9trv4xhmh6s68vbw9ba6to70 (name ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table lib.books_publishers
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lib.books_publishers (
  book_id BIGINT NOT NULL,
  publisher_id BIGINT NOT NULL,
  PRIMARY KEY (book_id, publisher_id),
  INDEX FKbcpe8qjcevmbfkkvh87d6fun8 (publisher_id ASC) VISIBLE,
  CONSTRAINT FK7tndxhkb3q2tpcpp3g1tsp7kw
    FOREIGN KEY (book_id)
    REFERENCES lib.book (id),
  CONSTRAINT FKbcpe8qjcevmbfkkvh87d6fun8
    FOREIGN KEY (publisher_id)
    REFERENCES lib.publisher (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;